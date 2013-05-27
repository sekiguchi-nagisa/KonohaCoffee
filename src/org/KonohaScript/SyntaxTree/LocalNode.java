package org.KonohaScript.SyntaxTree;

import org.KonohaScript.KonohaType;
import org.KonohaScript.KonohaToken;

public class LocalNode extends TypedNode {
	/* TermToken->text */
	public String FieldName;

	public LocalNode(KonohaType TypeInfo, KonohaToken SourceToken, String FieldName) {
		super(TypeInfo, SourceToken);
		this.FieldName = FieldName;
	}

	@Override
	public boolean Evaluate(NodeVisitor Visitor) {
		Visitor.EnterLocal(this);
		return Visitor.ExitLocal(this);
	}
}
