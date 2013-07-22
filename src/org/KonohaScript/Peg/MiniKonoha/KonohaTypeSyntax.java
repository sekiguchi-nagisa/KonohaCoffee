package org.KonohaScript.Peg.MiniKonoha;

import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaType;
import org.KonohaScript.JUtils.KonohaConst;
import org.KonohaScript.KLib.TokenList;
import org.KonohaScript.Parser.KonohaGrammar;
import org.KonohaScript.Parser.TypeEnv;
import org.KonohaScript.Parser.UntypedNode;
import org.KonohaScript.SyntaxTree.NullNode;
import org.KonohaScript.SyntaxTree.TypedNode;

final public class KonohaTypeSyntax extends KonohaGrammar implements KonohaConst {
	public int ParseType(UntypedNode node, TokenList tokens, int BeginIdx, int EndIdx, int ParseOption) {
		return EndIdx;
	}

	public TypedNode TypeType(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		return new NullNode((KonohaType) UNode.KeyToken.ResolvedObject);
	}

	@Override
	public void LoadDefaultSyntax(KonohaNameSpace NameSpace) {
		//		NameSpace.DefineSymbol("void", NameSpace.KonohaContext.VoidType);
		//		NameSpace.DefineSymbol("boolean", NameSpace.KonohaContext.BooleanType);
		//		NameSpace.DefineSymbol("int", NameSpace.KonohaContext.IntType);
		//		NameSpace.DefineSymbol("String", NameSpace.KonohaContext.StringType);
		//		NameSpace.DefineSyntax("$Type", KonohaConst.Term, this, "Type");
	}
}