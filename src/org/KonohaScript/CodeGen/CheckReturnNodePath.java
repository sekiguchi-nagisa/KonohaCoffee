package org.KonohaScript.CodeGen;

import org.KonohaScript.Konoha;
import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaType;
import org.KonohaScript.SyntaxTree.NullNode;
import org.KonohaScript.SyntaxTree.ReturnNode;
import org.KonohaScript.SyntaxTree.TypedNode;

class MethodPath {
	TypedNode Run(KonohaNameSpace NameSpace, KonohaType ReturnType, TypedNode Block) {
		return null;
	}
}

public class CheckReturnNodePath extends MethodPath {
	@Override
	TypedNode Run(KonohaNameSpace NameSpace, KonohaType ReturnType, TypedNode Block) {
		TypedNode TailNode = null;
		if(Block != null) {
			TailNode = Block.GetTailNode();
			if(TailNode instanceof ReturnNode) {
				// Block has ReturnInst
				return Block;
			}
		}
		TypedNode ReturnNode = null;
		Konoha Context = NameSpace.KonohaContext;
		if(ReturnType.equals(Context.VoidType)) {
			ReturnNode = new ReturnNode(ReturnType, null);
		} else {
			ReturnNode = new ReturnNode(ReturnType, new NullNode(ReturnType));
		}
		if(Block == null) {
			return ReturnNode;
		}
		Block.Next(ReturnNode);
		return Block;
	}
}
