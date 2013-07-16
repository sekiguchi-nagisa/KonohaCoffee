package org.KonohaScript.SyntaxTree;

import org.KonohaScript.KonohaType;

public class LoopNode extends TypedNode {

	/* while CondExpr then { LoopBlock; IterationExpr } */
	public TypedNode	CondExpr;
	public TypedNode	LoopBody;
	public TypedNode	IterationExpr;

	public LoopNode(KonohaType TypeInfo, TypedNode CondExpr, TypedNode LoopBody, TypedNode IterationExpr) {
		super(TypeInfo, null/* fixme */);
		this.CondExpr = CondExpr;
		this.LoopBody = LoopBody;
		this.IterationExpr = IterationExpr;
	}

	@Override
	public boolean Evaluate(NodeVisitor Visitor) {
		return Visitor.VisitLoop(this);
	}

}
