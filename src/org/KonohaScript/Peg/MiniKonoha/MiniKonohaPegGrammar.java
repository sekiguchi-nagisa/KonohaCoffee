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
import org.KonohaScript.PegParser.PegParser;
import org.KonohaScript.SyntaxTree.LocalNode;
import org.KonohaScript.SyntaxTree.TypedNode;

final class SourceCodeGrammar extends KonohaGrammar {
	static boolean	Inited	= false;

	@Override
	public void LoadDefaultSyntax(KonohaNameSpace NameSpace) {
		if(!Inited) {
			Inited = true;
			PegParser Parser = NameSpace.PegParser;
			Parser.AddSyntax(new additiveOperatorSyntax(), false);
			Parser.AddSyntax(new logicalOrExpressionSyntax(), false);
			Parser.AddSyntax(new EmptyStatementSyntax(), false);
			Parser.AddSyntax(new stringLiteralSyntax(), false);
			Parser.AddSyntax(new returnStatementSyntax(), false);
			Parser.AddSyntax(new whileStatementSyntax(), false);
			Parser.AddSyntax(new SymbolSyntax(), false);
			Parser.AddSyntax(new continueStatementSyntax(), false);
			Parser.AddSyntax(new identifierSyntax(), false);
			Parser.AddSyntax(new ParameterListSyntax(), false);
			Parser.AddSyntax(new typeSyntax(), false);
			Parser.AddSyntax(new ParamDeclSyntax(), false);
			Parser.AddSyntax(new breakStatementSyntax(), false);
			Parser.AddSyntax(new statementsSyntax(), false);
			Parser.AddSyntax(new ParameterSyntax(), false);
			Parser.AddSyntax(new relationExpressionSyntax(), false);
			Parser.AddSyntax(new ParametersSyntax(), false);
			Parser.AddSyntax(new primarySyntax(), false);
			Parser.AddSyntax(new functionDefinitionSyntax(), false);
			Parser.AddSyntax(new logicalAndExpressionSyntax(), false);
			Parser.AddSyntax(new TypeTokenSyntax(), false);
			Parser.AddSyntax(new multiplicativeOperatorSyntax(), false);
			Parser.AddSyntax(new functionSignatureSyntax(), false);
			Parser.AddSyntax(new expressionStatementSyntax(), false);
			Parser.AddSyntax(new ifStatementSyntax(), false);
			Parser.AddSyntax(new literalSyntax(), false);
			Parser.AddSyntax(new variableSyntax(), false);
			Parser.AddSyntax(new unaryExpressionSyntax(), false);
			Parser.AddSyntax(new newExpressionSyntax(), false);
			Parser.AddSyntax(new TopLevelDefinitionSyntax(), false);
			Parser.AddSyntax(new ParamDeclListSyntax(), false);
			Parser.AddSyntax(new selectorSyntax(), false);
			Parser.AddSyntax(new variableDeclarationSyntax(), false);
			Parser.AddSyntax(new callExpressionSyntax(), false);
			Parser.AddSyntax(new expressionSyntax(), false);
			Parser.AddSyntax(new memberExpressionSyntax(), false);
			Parser.AddSyntax(new ParamDeclsSyntax(), false);
			Parser.AddSyntax(new blockSyntax(), false);
			Parser.AddSyntax(new multiplicativeExpressionSyntax(), false);
			Parser.AddSyntax(new additiveExpressionSyntax(), false);
			Parser.AddSyntax(new leftHandSideExpressionSyntax(), false);
			Parser.AddSyntax(new statementSyntax(), false);
			Parser.AddSyntax(new relationOperatorSyntax(), false);
			Parser.AddSyntax(new EQSyntax(), false);
			Parser.AddSyntax(new intLiteralSyntax(), false);
			Parser.AddSyntax(new functionBodySyntax(), false);
			NameSpace.AddPatternSyntax(null, new SourceCodeSyntax(), true);

		}

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

		new SourceCodeGrammar().LoadDefaultSyntax(NameSpace);
	}

}