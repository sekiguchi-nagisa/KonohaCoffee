package org.KonohaScript.CodeGen.JVM;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Stack;

import org.KonohaScript.KonohaClass;
import org.KonohaScript.KonohaMethod;
import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaType;
import org.KonohaScript.NativeMethodInvoker;
import org.KonohaScript.CodeGen.CodeGenerator;
import org.KonohaScript.CodeGen.Local;
import org.KonohaScript.JUtils.KonohaConst;
import org.KonohaScript.KLib.KonohaArray;
import org.KonohaScript.SyntaxTree.AndNode;
import org.KonohaScript.SyntaxTree.ApplyNode;
import org.KonohaScript.SyntaxTree.AssignNode;
import org.KonohaScript.SyntaxTree.ConstNode;
import org.KonohaScript.SyntaxTree.DefineNode;
import org.KonohaScript.SyntaxTree.ErrorNode;
import org.KonohaScript.SyntaxTree.FunctionNode;
import org.KonohaScript.SyntaxTree.GetterNode;
import org.KonohaScript.SyntaxTree.IfNode;
import org.KonohaScript.SyntaxTree.JumpNode;
import org.KonohaScript.SyntaxTree.LabelNode;
import org.KonohaScript.SyntaxTree.LetNode;
import org.KonohaScript.SyntaxTree.LocalNode;
import org.KonohaScript.SyntaxTree.LoopNode;
import org.KonohaScript.SyntaxTree.NewNode;
import org.KonohaScript.SyntaxTree.NullNode;
import org.KonohaScript.SyntaxTree.OrNode;
import org.KonohaScript.SyntaxTree.ReturnNode;
import org.KonohaScript.SyntaxTree.SwitchNode;
import org.KonohaScript.SyntaxTree.ThrowNode;
import org.KonohaScript.SyntaxTree.TryNode;
import org.KonohaScript.SyntaxTree.TypedNode;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

class LabelStack {
	KonohaArray	LabelNames;
	KonohaArray	Labels;

	LabelStack() {
		this.LabelNames = new KonohaArray();
		this.Labels = new KonohaArray();
	}

	void AddLabel(String Name, Label Label) {
		this.LabelNames.add(Name);
		this.Labels.add(Label);
	}

	Label FindLabel(String Name) {
		for(int i = this.LabelNames.size() - 1; i >= 0; i--) {
			String LName = (String) this.LabelNames.get(i);
			if(LName.equals(Name)) {
				return (Label) this.Labels.get(i);
			}
		}
		return null;
	}

	void RemoveLabel(String Name) {
		for(int i = this.LabelNames.size() - 1; i >= 0; i--) {
			String LName = (String) this.LabelNames.get(i);
			if(LName.equals(Name)) {
				this.LabelNames.remove(i);
				this.Labels.remove(i);
			}
		}
	}
}

class JVMBuilder extends CodeGenerator implements Opcodes {

	MethodVisitor					methodVisitor;
	Stack<Type>						typeStack;
	LabelStack						LabelStack;
	KonohaNameSpace					NameSpace;
	TypeResolver					TypeResolver;

	public JVMBuilder(KonohaMethod method, MethodVisitor mv, TypeResolver TypeResolver) {
		super(method);
		this.methodVisitor = mv;
		this.typeStack = new Stack<Type>();
		this.LabelStack = new LabelStack();
		this.TypeResolver = TypeResolver;
	}

	void LoadLocal(Local local) {
		KonohaType ktype = local.TypeInfo;
		Type type = TypeResolver.GetAsmType(ktype);
		this.typeStack.push(type);
		this.methodVisitor.visitVarInsn(type.getOpcode(ILOAD), local.Index);
	}

	void StoreLocal(Local local) {
		KonohaType ktype = local.TypeInfo;
		Type type = TypeResolver.GetAsmType(ktype);
		this.typeStack.pop(); //TODO: check cast
		this.methodVisitor.visitVarInsn(type.getOpcode(ISTORE), local.Index);
	}

	void LoadConst(Object o) {
		Type type;
		if(o instanceof Integer) {
			type = Type.INT_TYPE;
		} else if(o instanceof Boolean) {
			type = Type.BOOLEAN_TYPE;
		} else {
			type = TypeResolver.GetAsmType(o.getClass());
		}
		this.typeStack.push(type);
		this.methodVisitor.visitLdcInsn(o);
	}

	void Call(int opcode, KonohaMethod Method) {
		if(Method.MethodInvoker instanceof NativeMethodInvoker) {
			NativeMethodInvoker i = (NativeMethodInvoker) Method.MethodInvoker;
			Method m = i.GetMethodRef();
			String owner = TypeResolver.GetAsmType(m.getDeclaringClass()).getInternalName();
			String methodName = m.getName();
			String methodDescriptor = Type.getMethodDescriptor(m);
			if(Modifier.isStatic(m.getModifiers())) opcode = INVOKESTATIC;
			this.methodVisitor.visitMethodInsn(opcode, owner, methodName, methodDescriptor);
			this.typeStack.push(TypeResolver.GetAsmType(m.getReturnType()));
		} else {
//			Class<?> OwnerClass = Method.ClassInfo.HostedClassInfo;
//			if(OwnerClass == null) {
//				OwnerClass = Method.ClassInfo.DefaultNullValue.getClass();
//			}
//			String owner = OwnerClass.getName().replace(".", "/");
			String owner = "global";//FIXME
			String methodName = Method.MethodName;
			String methodDescriptor = TypeResolver.GetJavaMethodDescriptor(Method);
			this.methodVisitor.visitMethodInsn(opcode, owner, methodName, methodDescriptor);
			this.typeStack.push(TypeResolver.GetAsmType(Method.GetReturnType(null)));
		}
	}

	@Override
	public boolean VisitDefine(DefineNode Node) {
		if(Node.DefInfo instanceof KonohaClass) {
			KonohaClass c = (KonohaClass) Node.DefInfo;
			c.MakeDefinition(this.NameSpace);
		} else if(Node.DefInfo instanceof KonohaMethod) {
			KonohaMethod m = (KonohaMethod) Node.DefInfo;
			m.DoCompilation();
		}
		return true;
	}

	@Override
	public boolean VisitConst(ConstNode Node) {
		Object constValue = Node.ConstValue;
		Type type = TypeResolver.GetAsmType(constValue.getClass());
		this.typeStack.push(type);
		this.LoadConst(constValue);
		return true;
	}

	@Override
	public boolean VisitNew(NewNode Node) {
		// TODO Auto-generated method stub
		for(int i = 0; i < Node.Params.size(); i++) {
			TypedNode Param = (TypedNode) Node.Params.get(i);
			Param.Evaluate(this);
		}
		return false;
	}

	@Override
	public boolean VisitNull(NullNode Node) {
		this.typeStack.push(TypeResolver.GetAsmType(Object.class));
		methodVisitor.visitInsn(ACONST_NULL);
		return true;
	}

	@Override
	public boolean VisitLocal(LocalNode Node) {
		String FieldName = Node.FieldName;
		Local local;
		if((local = this.FindLocalVariable(FieldName)) == null) {
			throw new CodeGenException("local variable not found:" + FieldName);
		}
		this.LoadLocal(local);
		return true;
	}

	@Override
	public boolean VisitGetter(GetterNode Node) {
		// TODO Auto-generated method stub
		Node.BaseNode.Evaluate(this);
		return true;
	}

	@Override
	public boolean VisitApply(ApplyNode Node) {
		for(int i = 0; i < Node.Params.size(); i++) {
			TypedNode Param = (TypedNode) Node.Params.get(i);
			Param.Evaluate(this);
			Type requireType = TypeResolver.GetAsmType(Node.Method.Param.Types[i]);
			Type foundType = this.typeStack.pop();
			if(requireType.equals(Type.getType(Object.class)) && !foundType.getDescriptor().startsWith("L")) {
				// needs boxing
				methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
			}
		}
		int opcode = INVOKEVIRTUAL;
//		if(Node.Method.Is(KonohaConst.StaticMethod)) {
			opcode = INVOKESTATIC;
//		}
//		String methodName = Node.Method.MethodName;
		this.Call(opcode, Node.Method);
		return true;
	}

	@Override
	public boolean VisitAnd(AndNode Node) {
		// TODO Auto-generated method stub
		Node.LeftNode.Evaluate(this);
		Node.RightNode.Evaluate(this);
		return false;
	}

	@Override
	public boolean VisitOr(OrNode Node) {
		// TODO Auto-generated method stub
		Node.LeftNode.Evaluate(this);
		Node.RightNode.Evaluate(this);

		return false;
	}

	@Override
	public boolean VisitAssign(AssignNode Node) {
		// TODO Auto-generated method stub
		Node.LeftNode.Evaluate(this);
		Node.RightNode.Evaluate(this);
		return true;
	}

	@Override
	public boolean VisitLet(LetNode Node) {
		// TODO Auto-generated method stub
		Node.ValueNode.Evaluate(this);
		this.VisitList(Node.BlockNode);

		return true;
	}

	@Override
	public boolean VisitIf(IfNode Node) {
		Label ELSE = new Label();
		Label END = new Label();
		MethodVisitor mv = this.methodVisitor;
		Node.CondExpr.Evaluate(this);
		typeStack.pop(); //TODO: check cast
		mv.visitJumpInsn(IFEQ, ELSE);

		// Then
		if(Node.ThenNode != null) {
			Node.ThenNode.Evaluate(this);
		}
		mv.visitJumpInsn(GOTO, END);

		// Else
		mv.visitLabel(ELSE);
		if(Node.ElseNode != null) {
			Node.ElseNode.Evaluate(this);
		}

		// End
		mv.visitLabel(END);
		return true;
	}

	@Override
	public boolean VisitSwitch(SwitchNode Node) {
		// TODO Auto-generated method stub
		Node.CondExpr.Evaluate(this);
		for(int i = 0; i < Node.Blocks.size(); i++) {
			TypedNode Block = (TypedNode) Node.Blocks.get(i);
			this.VisitList(Block);
		}
		return true;
	}

	@Override
	public boolean VisitLoop(LoopNode Node) {
		MethodVisitor mv = this.methodVisitor;
		Label HEAD = new Label();
		Label END = new Label();

		this.LabelStack.AddLabel("break", END);
		this.LabelStack.AddLabel("continue", HEAD);

		mv.visitLabel(HEAD);

		Node.CondExpr.Evaluate(this);

		mv.visitInsn(ICONST_1); // true
		mv.visitJumpInsn(IF_ICMPNE, END); // condition
//		this.stack.pop();
//		this.stack.pop();
//		this.stack.push();
		this.VisitList(Node.LoopBody);
		if(Node.IterationExpr != null) {
			Node.IterationExpr.Evaluate(this);
		}
		mv.visitJumpInsn(GOTO, HEAD);
		mv.visitLabel(END);
		this.LabelStack.RemoveLabel("break");
		this.LabelStack.RemoveLabel("continue");
		return true;
	}

	@Override
	public boolean VisitReturn(ReturnNode Node) {
		if(Node.Expr != null) {
			Node.Expr.Evaluate(this);
			Type type = this.typeStack.pop();
			methodVisitor.visitInsn(type.getOpcode(IRETURN));
		} else {
			methodVisitor.visitInsn(RETURN);
		}
		return true;
	}

	@Override
	public boolean VisitLabel(LabelNode Node) {
		String LabelName = Node.Label;
		Label Label = new Label();
		this.LabelStack.AddLabel(LabelName, Label);
		return true;
	}

	@Override
	public boolean VisitJump(JumpNode Node) {
		String LabelName = Node.Label;
		Label label = this.LabelStack.FindLabel(LabelName);
		if(label == null) {
			throw new RuntimeException("Cannot find " + LabelName + " label.");
		}
		this.methodVisitor.visitJumpInsn(GOTO, label);
		return false;
	}

	@Override
	public boolean VisitTry(TryNode Node) {
		int catchSize = Node.CatchBlock.size();
		MethodVisitor mv = this.methodVisitor;
		Label beginTryLabel = new Label();
		Label endTryLabel = new Label();
		Label finallyLabel = new Label();
		Label catchLabel[] = new Label[catchSize];

		// prepare
		for(int i = 0; i < catchSize; i++) { //TODO: add exception class name
			catchLabel[i] = new Label();
			mv.visitTryCatchBlock(beginTryLabel, endTryLabel, catchLabel[i], null);
		}

		// try block
		mv.visitLabel(beginTryLabel);
		this.VisitList(Node.TryBlock);
		mv.visitLabel(endTryLabel);
		mv.visitJumpInsn(GOTO, finallyLabel);

		// catch block
		for(int i = 0; i < catchSize; i++) { //TODO: add exception class name
			TypedNode Block = (TypedNode) Node.CatchBlock.get(i);
			TypedNode Exception = (TypedNode) Node.TargetException.get(i);
			mv.visitLabel(catchLabel[i]);
			this.VisitList(Block);
			mv.visitJumpInsn(GOTO, finallyLabel);
		}

		// finally block
		mv.visitLabel(finallyLabel);
		this.VisitList(Node.FinallyBlock);

		return true;
	}

	@Override
	public boolean VisitThrow(ThrowNode Node) {
		Node.Expr.Evaluate(this);
		typeStack.pop();
		methodVisitor.visitInsn(ATHROW);
		return true;
	}

	@Override
	public boolean VisitFunction(FunctionNode Node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean VisitError(ErrorNode Node) {
		String ps = Type.getDescriptor(System.err.getClass());
		methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "err", ps);
		methodVisitor.visitLdcInsn(Node.ErrorMessage);
		methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
		return true;
	}

	public void VisitEnd() {
		this.methodVisitor.visitEnd();
	}

	public void visitBoxingAndReturn() {
		if(typeStack.empty()) {
			methodVisitor.visitInsn(ACONST_NULL);
		} else {
			Type type = typeStack.pop();
			if(type.equals(Type.INT_TYPE)) {
				methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
			} else if(type.equals(Type.BOOLEAN_TYPE)) {
				methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
			} else if(type.equals(Type.VOID_TYPE)) {
				methodVisitor.visitInsn(ACONST_NULL);//FIXME: return void
			} else {
				System.out.println("error: " + type);
			}
		}
		methodVisitor.visitInsn(ARETURN);
	}
}