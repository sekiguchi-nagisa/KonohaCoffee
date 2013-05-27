package org.KonohaScript.SyntaxTree;

import org.KonohaScript.KonohaToken;
import org.KonohaScript.KonohaType;
import org.KonohaScript.SyntaxTree.NodeVisitor.LetNodeAcceptor;

class DefaultLetNodeAcceptor implements LetNodeAcceptor {
	@Override
	public boolean Eval(LetNode Node, NodeVisitor Visitor) {
		Visitor.EnterLet(Node);
		Visitor.Visit(Node.ValueNode);
		Visitor.Visit(Node.BlockNode);
		return Visitor.ExitLet(Node);
	}
}

public class LetNode extends TypedNode {
	public KonohaToken	VarToken;
	public TypedNode	ValueNode;
	public TypedNode	BlockNode;

	/* let frame[Index] = Right in Block end */
	public LetNode(KonohaType TypeInfo, KonohaToken VarToken, TypedNode Right,
			TypedNode Block) {
		super(TypeInfo, VarToken);
		this.VarToken = VarToken;
		this.ValueNode = Right;
		this.BlockNode = Block;
	}

	@Override
	public boolean Evaluate(NodeVisitor Visitor) {
		return Visitor.LetNodeAcceptor.Eval(this, Visitor);
	}

}
