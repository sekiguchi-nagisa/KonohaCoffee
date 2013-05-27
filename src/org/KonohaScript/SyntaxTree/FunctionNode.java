package org.KonohaScript.SyntaxTree;

import java.util.ArrayList;

import org.KonohaScript.KonohaType;
import org.KonohaScript.KonohaMethod;
import org.KonohaScript.CodeGen.ASTVisitor;

public class FunctionNode extends TypedNode implements CallableNode {
	/* [Method, DefaultObject, [Env1, Env2, ...., EnvN] */
	/*
	 * void f() { int Env1, Env2; return function (int Param1, int Param2) int {
	 * return Env1 + Env2 + Param1 + Param2; } (10, 20); } }
	 */
	public ArrayList<TypedNode>	EnvList;
	public KonohaMethod				Mtd;

	public FunctionNode(KonohaType TypeInfo, KonohaMethod Mtd) {
		super(TypeInfo);
		this.Mtd = Mtd;
		this.EnvList = new ArrayList<TypedNode>();
	}

	@Override
	public void Append(TypedNode Expr) {
		this.EnvList.add(Expr);
	}

	@Override
	public boolean Evaluate(ASTVisitor Visitor) {
		Visitor.EnterFunction(this);
		return Visitor.ExitFunction(this);
	}
}
