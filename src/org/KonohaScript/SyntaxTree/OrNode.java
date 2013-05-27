package org.KonohaScript.SyntaxTree;

import org.KonohaScript.KonohaToken;
import org.KonohaScript.KonohaType;
import org.KonohaScript.SyntaxTree.NodeVisitor.OrNodeAcceptor;

class DefaultOrNodeAcceptor implements OrNodeAcceptor {
	@Override
	public boolean Eval(OrNode Node, NodeVisitor Visitor) {
		Visitor.EnterOr(Node);
		Visitor.Visit(Node.LeftNode);
		Visitor.Visit(Node.RightNode);
		return Visitor.ExitOr(Node);
	}
}

public class OrNode extends BinaryNode {

	public OrNode(KonohaType TypeInfo, KonohaToken KeyToken, TypedNode Left,
			TypedNode Right) {
		super(TypeInfo, KeyToken, Left, Right);
	}

	@Override
	public boolean Evaluate(NodeVisitor Visitor) {
		return Visitor.OrNodeAcceptor.Eval(this, Visitor);
	}

}
