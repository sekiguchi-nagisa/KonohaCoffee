package org.KonohaScript.Peg.MiniKonoha;

import org.KonohaScript.Konoha;
import org.KonohaScript.KonohaBuilder;
import org.KonohaScript.KonohaConst;
import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaType;
import org.KonohaScript.KLib.TokenList;
import org.KonohaScript.MiniKonoha.KonohaInt;
import org.KonohaScript.Parser.KonohaGrammar;
import org.KonohaScript.Parser.KonohaParser;
import org.KonohaScript.Parser.KonohaSyntax;
import org.KonohaScript.Parser.KonohaToken;
import org.KonohaScript.Parser.TypeEnv;
import org.KonohaScript.Parser.UntypedNode;
import org.KonohaScript.PegParser.KonohaIntegerSyntax;
import org.KonohaScript.PegParser.KonohaSingleSymbolSyntax;
import org.KonohaScript.PegParser.SyntaxModule;
import org.KonohaScript.SyntaxTree.LocalNode;
import org.KonohaScript.SyntaxTree.TypedNode;

final class KonohaTypeSyntax extends KonohaGrammar implements KonohaConst {
	public int ParseType(UntypedNode node, TokenList tokens, int BeginIdx, int OpIdx, int EndIdx) {
		return EndIdx;
	}

	@Override
	public void LoadDefaultSyntax(KonohaNameSpace NameSpace) {
		NameSpace.DefineSymbol("void", NameSpace.KonohaContext.VoidType);
		NameSpace.DefineSymbol("boolean", NameSpace.KonohaContext.BooleanType);
		NameSpace.DefineSymbol("int", NameSpace.KonohaContext.IntType);
		NameSpace.DefineSymbol("String", NameSpace.KonohaContext.StringType);
		NameSpace.DefineSyntax("$Type", KonohaConst.Term, this, "Type");
	}
}

public class SyntaxModuleParserTest extends KonohaGrammar implements KonohaConst {
	public int WhiteSpaceToken(KonohaNameSpace ns, String SourceText, int pos, TokenList ParsedTokenList) {
		for(; pos < SourceText.length(); pos++) {
			char ch = SourceText.charAt(pos);
			if(!Character.isWhitespace(ch)) {
				break;
			}
		}
		return pos;
	}

	public int IndentToken(KonohaNameSpace ns, String SourceText, int pos, TokenList ParsedTokenList) {
		int LineStart = pos + 1;
		pos = pos + 1;
		for(; pos < SourceText.length(); pos++) {
			char ch = SourceText.charAt(pos);
			if(!Character.isWhitespace(ch)) {
				break;
			}
		}
		String Text = "";
		if(LineStart < pos) {
			Text = SourceText.substring(LineStart, pos);
		}
		KonohaToken Token = new KonohaToken(Text);
		Token.ResolvedSyntax = KonohaSyntax.IndentSyntax;
		ParsedTokenList.add(Token);
		return pos;
	}

	public int SymbolToken(KonohaNameSpace ns, String SourceText, int pos, TokenList ParsedTokenList) {
		int start = pos;
		for(; pos < SourceText.length(); pos++) {
			char ch = SourceText.charAt(pos);
			if(!Character.isLetter(ch) && !Character.isDigit(ch) && ch != '_') {
				break;
			}
		}
		KonohaToken Token = new KonohaToken(SourceText.substring(start, pos));
		ParsedTokenList.add(Token);
		return pos;
	}

	public int StringLiteralToken(KonohaNameSpace ns, String SourceText, int pos, TokenList ParsedTokenList) {
		int start = pos + 1;
		char prev = '"';
		pos = start;
		while(pos < SourceText.length()) {
			char ch = SourceText.charAt(pos);
			if(ch == '"' && prev != '\\') {
				KonohaToken token = new KonohaToken(SourceText.substring(start, pos - start));
				token.ResolvedSyntax = ns.GetSyntax("$StringLiteral");
				ParsedTokenList.add(token);
				return pos + 1;
			}
			if(ch == '\n') {
				KonohaToken token = new KonohaToken(SourceText.substring(start, pos - start));
				ns.Message(KonohaConst.Error, token, "expected \" to close the string literal");
				ParsedTokenList.add(token);
				return pos;
			}
			pos = pos + 1;
			prev = ch;
		}
		return pos;
	}

	public int ParseSymbol(UntypedNode Node, TokenList TokenList, int BeginIdx, int EndIdx, int ParseOption) {
		return BeginIdx + 1;
	}

	public TypedNode TypeSymbol(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		// case: Symbol is LocalVariable
		TypeInfo = Gamma.GetLocalType(UNode.KeyToken.ParsedText);
		if(TypeInfo != null) {
			return new LocalNode(TypeInfo, UNode.KeyToken, UNode.KeyToken.ParsedText);
		}
		// case: Symbol is undefined name
		return Gamma.NewErrorNode(UNode.KeyToken, "undefined name: " + UNode.KeyToken.ParsedText);
	}

	class PegParser extends KonohaParser {
		SyntaxModule	Module;

		public PegParser(SyntaxModule Module) {
			this.Module = Module;
		}

		@Override
		public UntypedNode ParseNewNode(KonohaNameSpace ns, UntypedNode PrevNode, TokenList TokenList, int BeginIdx,
				int EndIdx, int ParseOption) {
			UntypedNode UNode = Module.Parse(TokenList, BeginIdx, EndIdx);
			return UNode;
		}
	}

	@Override
	public void LoadDefaultSyntax(KonohaNameSpace NameSpace) {

		// Load Syntax
		new KonohaTypeSyntax().LoadDefaultSyntax(NameSpace);

		new KonohaSingleSymbolSyntax().LoadDefaultSyntax(NameSpace);
		NameSpace.AddTokenFunc(" \t\n", this, "WhiteSpaceToken");
		NameSpace.AddTokenFunc("Aa", this, "SymbolToken");
		NameSpace.AddTokenFunc("\"", this, "StringLiteralToken");
		NameSpace.DefineSyntax("$Symbol", KonohaConst.Term, this, "Symbol");

		new KonohaIntegerSyntax().LoadDefaultSyntax(NameSpace);

		new KonohaInt().DefineMethod(NameSpace);

		// Load Syntax Module
		SyntaxModule Mod = new SyntaxModule(NameSpace);
		Mod.SetRootSyntax(new SourceCodeSyntax());
		KonohaParser PegParser = new PegParser(Mod);
		NameSpace.LoadParser(PegParser);
	}

	static void Test(KonohaNameSpace NameSpace, String Source) {
		TokenList BufferList = NameSpace.Tokenize(Source, 0);
		TokenList TokenList = new TokenList();
		NameSpace.PreProcess(BufferList, 0, BufferList.size(), TokenList);
		KonohaToken.DumpTokenList(0, "Dump::", TokenList, 0, TokenList.size());
		UntypedNode UNode = NameSpace.Parser.ParseNewNode(NameSpace, null, TokenList, 0, TokenList.size(), AllowEmpty);

		System.out.println("untyped tree: " + UNode);
		TypeEnv Gamma = new TypeEnv(NameSpace, null);
		TypedNode TNode = TypeEnv.TypeCheckEachNode(Gamma, UNode, Gamma.VoidType, KonohaConst.DefaultTypeCheckPolicy);
		KonohaBuilder Builder = NameSpace.GetBuilder();
		Object ResultValue = Builder.EvalAtTopLevel(TNode);
		System.out.println(ResultValue);
	}

	public static void main(String[] args) {

		Konoha konoha = new Konoha(new SyntaxModuleParserTest(), "org.KonohaScript.CodeGen.LeafJSCodeGen");
		KonohaNameSpace NameSpace = konoha.DefaultNameSpace;

		//Test(Mod, NameSpace, "void fibo(int a) {\n  if(a < 3) {    return 1;\n  } \n  return fibo(a-1)+fibo(a-2);\n}");
		//Test(Mod, NameSpace, "f(b * c);");
		//Test(Mod, NameSpace, "int sub(int a) {  return (a - 100);}");
		//Test(Mod, NameSpace, "int f(int a) {  return g(a);}");
		Test(NameSpace, "int add(int x) { return x + 1; }");
		Test(NameSpace, "add(10);");
		//Test(Mod, NameSpace, "(10);");
		//Test(Mod, NameSpace, "20;");
		//Test(Mod, NameSpace, "10 + 20;");
		//source = "10 + 20;";
		//source = "if(a < b) { f(); } else { return 1; };";
		//source = "if(a < b) { return 1; };";
	}
}
