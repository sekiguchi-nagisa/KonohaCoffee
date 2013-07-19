package org.KonohaScript.CodeGen;

import org.KonohaScript.KonohaMethod;
import org.KonohaScript.KonohaMethodInvoker;
import org.KonohaScript.KonohaType;
import org.KonohaScript.KLib.KonohaArray;
import org.KonohaScript.SyntaxTree.NodeVisitor;
import org.KonohaScript.SyntaxTree.TypedNode;

public abstract class CodeGenerator extends NodeVisitor {
	protected KonohaArray	LocalVals;
	KonohaMethod	MethodInfo;

	public CodeGenerator(KonohaMethod MethodInfo) {
		this.LocalVals = new KonohaArray();
		this.MethodInfo = MethodInfo;
	}

	KonohaMethodInvoker Compile(TypedNode Block) {
		return null;
	}

	protected Local FindLocalVariable(String Name) {
		for(int i = 0; i < this.LocalVals.size(); i++) {
			Local l = (Local) this.LocalVals.get(i);
			if(l.Name.compareTo(Name) == 0) {
				return l;
			}
		}
		return null;
	}

	Local GetLocalVariableByIndex(int Index) {
		if(this.LocalVals.size() > Index) {
			return (Local) this.LocalVals.get(Index);
		}
		return null;
	}

	public Local AddLocal(KonohaType Type, String Name) {
		Local local = new Local(this.LocalVals.size(), Type, Name);
		this.LocalVals.add(local);
		return local;
	}

	Local AddLocalVarIfNotDefined(KonohaType Type, String Name) {
		Local local = this.FindLocalVariable(Name);
		if(local != null) {
			return local;
		}
		return this.AddLocal(Type, Name);
	}

	public void Prepare(KonohaMethod Method) {
		this.LocalVals.clear();
		this.MethodInfo = Method;
		this.AddLocal(Method.ClassInfo, "this");
	}

	public void Prepare(KonohaMethod Method, KonohaArray params) {
		this.Prepare(Method);
		for(int i = 0; i < params.size(); i++) {
			Local local = (Local) params.get(i);
			this.AddLocal(local.TypeInfo, local.Name);
		}
	}

}