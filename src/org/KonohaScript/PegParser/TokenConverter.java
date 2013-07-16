package org.KonohaScript.PegParser;

import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KLib.TokenList;
import org.KonohaScript.Parser.KonohaToken;

public class TokenConverter {

	static public TokenList Flatten(KonohaNameSpace NameSpace, TokenList TokenList, int BeginIdx, int EndIdx) {
		TokenList BufferList;
		BufferList = new TokenList();
		TokenList = TokenConverter.FlattenParenthesis(NameSpace, TokenList, BeginIdx, EndIdx, BufferList, "( ... )", "Brace");

		BufferList = new TokenList();
		BeginIdx = 0;
		EndIdx = TokenList.size();
		TokenList = TokenConverter.FlattenParenthesis(NameSpace, TokenList, BeginIdx, EndIdx, BufferList, "{ ... }", "CBrace");
		return BufferList;
	}

	private static TokenList FlattenParenthesis(KonohaNameSpace NameSpace, TokenList SourceList, int BeginIdx, int EndIdx,
			TokenList BufferList, String MatchSymbol, String Symbol) {
		for(int i = BeginIdx; i < EndIdx; i++) {
			KonohaToken Token = SourceList.get(i);
			if(Token.EqualsText(MatchSymbol)) {
				TokenConverter.FlattenParenthesis(NameSpace, Token, BufferList, MatchSymbol, Symbol);
			} else {
				BufferList.add(Token);
			}
		}
		return BufferList;
	}

	private static void FlattenParenthesis(KonohaNameSpace NameSpace, KonohaToken GroupToken, TokenList BufferList,
			String MatchSymbol, String Symbol) {
		TokenList List = GroupToken.GetGroupList();
		KonohaToken Left = new KonohaToken("$L" + Symbol);
		KonohaToken Right = new KonohaToken("$R" + Symbol);
		Left.ResolvedSyntax = NameSpace.GetSyntax("$Symbol");
		Right.ResolvedSyntax = NameSpace.GetSyntax("$Symbol");
		BufferList.add(Left);
		for(int i = 1; i < List.size() - 1; i++) {
			KonohaToken Token = List.get(i);
			if(Token.EqualsText(MatchSymbol)) {
				TokenConverter.FlattenParenthesis(NameSpace, Token, BufferList, MatchSymbol, Symbol);
			} else {
				BufferList.add(Token);
			}
		}
		BufferList.add(Right);
	}

	public static int GetComposedListIndex(TokenList TokenList, int BeginIdx, int EndIdx, int FlattenIdx) {
		int i;
		for(i = BeginIdx; i < EndIdx; i++) {
			KonohaToken Token = TokenList.get(i);
			if(Token.EqualsText("( ... )")) {
				TokenList List = Token.GetGroupList();
				FlattenIdx -= TokenConverter.GetComposedListIndex(List, 0, List.size(), FlattenIdx);
			}
			FlattenIdx -= 1;
		}
		return i;
	}

	//	public int OpenParenthesisMacro(LexicalConverter Lexer, TokenList SourceList, int BeginIdx, int EndIdx, TokenList BufferList) {
	//		TokenList GroupList = new TokenList();
	//		KonohaToken BeginToken = SourceList.get(BeginIdx);
	//		GroupList.add(BeginToken);
	//		int nextIdx = Lexer.Do(SourceList, BeginIdx + 1, EndIdx, GroupList);
	//		KonohaToken LastToken = GroupList.get(GroupList.size() - 1);
	//		if(!LastToken.EqualsText(")")) { // ERROR
	//			LastToken.SetErrorMessage("must close )");
	//		} else {
	//			KonohaToken GroupToken = new KonohaToken("( ... )", BeginToken.uline);
	//			GroupToken.SetGroup(Lexer.GetSyntax("()"), GroupList);
	//			BufferList.add(GroupToken);
	//		}
	//		return nextIdx;
	//	}
	//
	//	public int CloseParenthesisMacro(LexicalConverter Lexer, TokenList SourceList, int BeginIdx, int EndIdx,
	//			TokenList BufferList) {
	//		KonohaToken Token = SourceList.get(BeginIdx);
	//		if(BufferList.size() == 0 || !BufferList.get(0).EqualsText("(")) {
	//			Token.SetErrorMessage("mismatched )");
	//		}
	//		BufferList.add(Token);
	//		return BreakPreProcess;
	//	}
	//
	//	public TokenList ComposeParenthesis(TokenList SourceList, int BeginIdx, int EndIdx, TokenList BufferList) {
	//		KonohaArray Stack = new KonohaArray();
	//		for(int i = BeginIdx; i < EndIdx; i++) {
	//			KonohaToken Token = SourceList.get(i);
	//			if(Token.EqualsText("$LBrace")) {
	//				Stack.add(i);
	//			}
	//			if(Token.EqualsText("$RBrace")) {
	//				int start = (Integer) Stack.remove(Stack.size() - 1);
	//				TokenList GroupList = new TokenList();
	//				for(int j = start; j < i; j++) {
	//					GroupList.add(SourceList.get(j));
	//				}
	//			}
	//		}
	//		return BufferList;
	//	}
	//
	//	private void ComposeParenthesis2(TokenList SourceList, int BeginIdx, int EndIdx, TokenList BufferList) {
	//		TokenList List = GroupToken.GetGroupList();
	//		KonohaToken Left = new KonohaToken("$LBrace");
	//		KonohaToken Right = new KonohaToken("$RBrace");
	//		BufferList.add(Left);
	//		for(int i = 0; i < List.size(); i++) {
	//			KonohaToken Token = List.get(i);
	//			if(Token.EqualsText("( ... )")) {
	//				this.FlattenParenthesis(Token, BufferList);
	//			} else {
	//				BufferList.add(Token);
	//			}
	//		}
	//		BufferList.add(Right);
	//	}

	//	static public TokenList Compose(TokenList TokenList, int BeginIdx, int EndIdx) {
	//		TokenList BufferList = new TokenList();
	//		return this.ComposeParenthesis(TokenList, BeginIdx, EndIdx, BufferList);
	//	}
}
