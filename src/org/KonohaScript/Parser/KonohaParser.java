package org.KonohaScript.Parser;

import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.JUtils.KonohaConst;
import org.KonohaScript.JUtils.KonohaDebug;
import org.KonohaScript.KLib.TokenList;

public class KonohaParser implements KonohaConst {
	public void AddSyntax(KonohaNameSpace NameSpace, KonohaGrammar ParentGrammar, KonohaGrammar Grammar, boolean TopLevel) {
		// FIXME
	}

	public UntypedNode ParseNewNode(KonohaNameSpace ns, UntypedNode PrevNode, TokenList TokenList, int BeginIdx, int EndIdx,
			int ParseOption) {
		UntypedNode LeftNode = null;
		// KToken.DumpTokenList(0, "ParseNewNode", TokenList, BeginIdx, EndIdx);
		while(BeginIdx < EndIdx) {
			int NextIdx = BeginIdx;
			KonohaToken KeyToken = TokenList.get(NextIdx);
			KonohaSyntax Syntax = KeyToken.ResolvedSyntax;
			// KonohaDebug.P("nextIdx="+NextIdx+",Syntax="+Syntax);
			if(Syntax.IsDelim()) { // A ; B
				BeginIdx++;
				continue;
			}
			if(LeftNode == null) {
				LeftNode = new UntypedNode(ns, KeyToken);
				NextIdx = LeftNode.ParseByKeyToken(KeyToken, TokenList, NextIdx, EndIdx);
			} else {
				if(Syntax.IsBinaryOperator()) { // A + B
					UntypedNode RightNode = this.ParseNewNode(ns, null, TokenList, NextIdx + 1, EndIdx, 0);
					LeftNode = UntypedNode.BinaryNode(ns, LeftNode, KeyToken, RightNode);
					break;
				} else if(Syntax.IsSuffixOperator()) { // A []
					NextIdx = LeftNode.ParseByKeyToken(KeyToken, TokenList, NextIdx, EndIdx);
				} else {
					this.ParseNewNode(ns, LeftNode, TokenList, NextIdx, EndIdx, AllowEmpty);
					break;
				}
			}
			if(BeginIdx >= NextIdx) {
				KonohaDebug.P("Panic ** " + Syntax + " BeginIdx=" + BeginIdx + ", NextIdx=" + NextIdx);
				break;
			}
			BeginIdx = NextIdx;
		}
		if(PrevNode != null && LeftNode != null) {
			PrevNode.LinkNode(LeftNode);
		}
		return LeftNode;
	}
}
