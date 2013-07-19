package org.KonohaScript.Peg.MiniKonoha;

import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaType;
import org.KonohaScript.Grammar.KonohaInt;
import org.KonohaScript.JUtils.KonohaConst;
import org.KonohaScript.KLib.TokenList;
import org.KonohaScript.Parser.KonohaGrammar;
import org.KonohaScript.Parser.KonohaSyntax;
import org.KonohaScript.Parser.KonohaToken;
import org.KonohaScript.Parser.LexicalConverter;
import org.KonohaScript.Parser.TypeEnv;
import org.KonohaScript.Parser.UntypedNode;
import org.KonohaScript.PegParser.KonohaIntegerSyntax;
import org.KonohaScript.PegParser.KonohaSingleSymbolSyntax;
import org.KonohaScript.PegParser.KonohaStringSyntax;
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

public class MiniKonohaPegGrammar extends KonohaGrammar implements KonohaConst {
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

	public int FlattenParenthesisMacro(LexicalConverter Lexer, TokenList SourceList, int BeginIdx, int EndIdx,
			TokenList BufferList) {
		KonohaToken Token = SourceList.get(BeginIdx);
		if(Token.EqualsText("( ... )")) {
			this.FlattenParensis(Token, BufferList);
		}
		return BreakPreProcess;
	}

	private void FlattenParensis(KonohaToken GroupToken, TokenList BufferList) {
		TokenList List = GroupToken.GetGroupList();
		KonohaToken Left = new KonohaToken("$LBrace");
		KonohaToken Right = new KonohaToken("$RBrace");
		BufferList.add(Left);
		for(int i = 0; i < List.size(); i++) {
			KonohaToken Token = List.get(i);
			if(Token.EqualsText("( ... )")) {
				this.FlattenParensis(Token, BufferList);
			} else {
				BufferList.add(Token);
			}
		}
		BufferList.add(Right);
	}

	@Override
	public void LoadDefaultSyntax(KonohaNameSpace NameSpace) {
		NameSpace.DefineMacro("( ... )", this, "FlattenParenthesisMacro");

		// Load Syntax
		new KonohaTypeSyntax().LoadDefaultSyntax(NameSpace);

		new KonohaSingleSymbolSyntax().LoadDefaultSyntax(NameSpace);
		NameSpace.AddTokenFunc(" \t\n", this, "WhiteSpaceToken");
		NameSpace.AddTokenFunc("Aa", this, "SymbolToken");
		//NameSpace.DefineSyntax("$Symbol", KonohaConst.Term, this, "Symbol");

		new KonohaIntegerSyntax().LoadDefaultSyntax(NameSpace);
		new KonohaStringSyntax().LoadDefaultSyntax(NameSpace);

		new KonohaInt().MakeDefinition(NameSpace);

		NameSpace.AddPatternSyntax(null, new SourceCodeSyntax(), true);
	}

}