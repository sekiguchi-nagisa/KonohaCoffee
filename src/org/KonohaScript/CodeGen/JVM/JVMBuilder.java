package org.KonohaScript.CodeGen.JVM;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.KonohaScript.KonohaClass;
import org.KonohaScript.KonohaMethod;
import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaType;
import org.KonohaScript.NativeMethodInvoker;
import org.KonohaScript.CodeGen.CodeGenerator;
import org.KonohaScript.CodeGen.Local;
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

public class JVMBuilder extends CodeGenerator implements Opcodes {

	MethodVisitor					methodVisitor;
	Stack<Type>						typeStack;
	LabelStack						LabelStack;
	KonohaNameSpace					NameSpace;
	TypeResolver					TypeResolver;

	public JVMBuilder(KonohaMethod method, MethodVisitor mv, TypeResolver TypeResolver, KonohaNameSpace NameSpace) {
		super(method);
		this.methodVisitor = mv;
		this.typeStack = new Stack<Type>();
		this.LabelStack = new LabelStack();
		this.TypeResolver = TypeResolver;
		this.NameSpace = NameSpace;
	}

	void LoadLocal(Local local) {
		KonohaType ktype = local.TypeInfo;
		Type type = this.TypeResolver.GetAsmType(ktype);
		this.typeStack.push(type);
		this.methodVisitor.visitVarInsn(type.getOpcode(ILOAD), local.Index);
	}

	void StoreLocal(Local local) {
		KonohaType ktype = local.TypeInfo;
		Type type = this.TypeResolver.GetAsmType(ktype);
		this.typeStack.pop(); //TODO: check cast
		this.methodVisitor.visitVarInsn(type.getOpcode(ISTORE), local.Index);
	}

	void LoadConst(Object o) {
		Type type;
		boolean unsupportType = false;
		if(o instanceof Integer) {
			type = Type.INT_TYPE;
		} else if(o instanceof Boolean) {
			type = Type.BOOLEAN_TYPE;
		} else if(o instanceof String) {
			type = this.TypeResolver.GetAsmType(o.getClass());
		} else {
			unsupportType = true;
			type = this.TypeResolver.GetAsmType(o.getClass());
		}
		this.typeStack.push(type);
		if(unsupportType) {
			int id = addConstValue(o);
			String owner = Type.getInternalName(this.getClass());
			String methodName = "getConstValue";
			String methodDesc = "(I)Ljava/lang/Object;";
			this.methodVisitor.visitLdcInsn(id);
			this.methodVisitor.visitMethodInsn(INVOKESTATIC, owner, methodName, methodDesc);
			this.methodVisitor.visitTypeInsn(CHECKCAST, Type.getInternalName(o.getClass()));
		} else {
			this.methodVisitor.visitLdcInsn(o);
		}
	}

	static List<Object> constValues = new ArrayList<Object>();
	static int addConstValue(Object o) {
		int id = constValues.indexOf(o);
		if(id != -1) {
			return id;
		} else {
			constValues.add(o);
			return constValues.size() - 1;
		}
	}

	public static Object getConstValue(int id) {
		return constValues.get(id);
	}

	void Call(int opcode, KonohaMethod Method) {
		if(Method.MethodInvoker instanceof NativeMethodInvoker) {
			NativeMethodInvoker i = (NativeMethodInvoker) Method.MethodInvoker;
			Method m = i.GetMethodRef();
			String owner = this.TypeResolver.GetAsmType(m.getDeclaringClass()).getInternalName();
			String methodName = m.getName();
			String methodDescriptor = Type.getMethodDescriptor(m);
			if(Modifier.isStatic(m.getModifiers())) {
				opcode = INVOKESTATIC;
			}
			this.methodVisitor.visitMethodInsn(opcode, owner, methodName, methodDescriptor);
			this.typeStack.push(this.TypeResolver.GetAsmType(m.getReturnType()));
		} else {
			//			Class<?> OwnerClass = Method.ClassInfo.HostedClassInfo;
			//			if(OwnerClass == null) {
			//				OwnerClass = Method.ClassInfo.DefaultNullValue.getClass();
			//			}
			//			String owner = OwnerClass.getName().replace(".", "/");
			String owner = "global";//FIXME
			String methodName = Method.MethodName;
			String methodDescriptor = this.TypeResolver.GetJavaMethodDescriptor(Method);
			this.methodVisitor.visitMethodInsn(opcode, owner, methodName, methodDescriptor);
			this.typeStack.push(this.TypeResolver.GetAsmType(Method.GetReturnType(null)));
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
		return true;
	}

	@Override
	public boolean VisitNull(NullNode Node) {
		KonohaType TypeInfo = Node.TypeInfo;
		if(TypeInfo.DefaultNullValue != null) {
			this.typeStack.push(this.TypeResolver.GetAsmType(TypeInfo.DefaultNullValue.getClass()));
			this.LoadConst(TypeInfo.DefaultNullValue);
		} else {
			// FIXME support primitive type (e.g. int)
			this.typeStack.push(this.TypeResolver.GetAsmType(Object.class));
			this.methodVisitor.visitInsn(ACONST_NULL);
		}
		return true;
	}

	@Override
	public boolean VisitLocal(LocalNode Node) {
		String FieldName = Node.FieldName;
		Local local;
		if((local = this.FindLocalVariable(FieldName)) == null) {
			throw new RuntimeException("local variable not found:" + FieldName);
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

	boolean isPrimitiveType(Type type) {
		return !type.getDescriptor().startsWith("L");
	}

	void box() {
		Type type = this.typeStack.pop();
		if(type.equals(Type.INT_TYPE)) {
			this.methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
			this.typeStack.push(Type.getType(Integer.class));
		} else if(type.equals(Type.DOUBLE_TYPE)) {
			this.methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
			this.typeStack.push(Type.getType(Double.class));
		} else if(type.equals(Type.BOOLEAN_TYPE)) {
			this.methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
			this.typeStack.push(Type.getType(Boolean.class));
		} else if(type.equals(Type.VOID_TYPE)) {
			this.methodVisitor.visitInsn(ACONST_NULL);//FIXME: return void
			this.typeStack.push(Type.getType(Void.class));
		} else {
			this.typeStack.push(type);
		}
	}

	@Override
	public boolean VisitApply(ApplyNode Node) {
		KonohaMethod Method = Node.Method;
		for(int i = 0; i < Node.Params.size(); i++) {
			TypedNode Param = (TypedNode) Node.Params.get(i);
			Param.Evaluate(this);
			Type requireType = this.TypeResolver.GetAsmType(Method.Param.Types[i]);
			Type foundType = this.typeStack.peek();
			if(requireType.equals(Type.getType(Object.class)) && this.isPrimitiveType(foundType)) {
				// boxing
				this.box();
			} else {
				this.typeStack.pop();
			}
		}
		int opcode = INVOKEVIRTUAL;
		//if(Node.Method.Is(KonohaConst.StaticMethod)) {
		opcode = INVOKESTATIC;
		//}
		this.Call(opcode, Method);
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
		Node.RightNode.Evaluate(this);
		if(Node.LeftNode instanceof GetterNode) {
			GetterNode Left = (GetterNode) Node.LeftNode;
			//Left.BaseNode.Evaluate(this);
			//Object Base = this.Pop();
			//assert (Base instanceof KonohaObject);
			//KonohaObject Obj = (KonohaObject) Base;
			//Obj.SetField(KonohaSymbol.GetSymbolId(Left.FieldName), Val);
			//this.push(Val);
		} else {
			assert (Node.LeftNode instanceof LocalNode);
			LocalNode Left = (LocalNode) Node.LeftNode;
			String Name = Left.SourceToken.ParsedText;
			Local local = this.FindLocalVariable(Name);
			if(local == null) {
				throw new RuntimeException("local variable " + Name + " is not found in this context");
			}
			this.StoreLocal(local);
		}
		return true;
	}

	@Override
	public boolean VisitLet(LetNode Node) {
		Local local = this.AddLocal(Node.TypeInfo, Node.SourceToken.ParsedText);
		Node.ValueNode.Evaluate(this);
		this.StoreLocal(local);
		this.VisitList(Node.BlockNode);
		return true;
	}

	@Override
	public boolean VisitIf(IfNode Node) {
		Label ELSE = new Label();
		Label END = new Label();
		MethodVisitor mv = this.methodVisitor;
		Node.CondExpr.Evaluate(this);
		this.typeStack.pop(); //TODO: check cast
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
			mv.visitJumpInsn(GOTO, END);
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
		this.typeStack.pop();
		mv.visitInsn(ICONST_1); // true
		mv.visitJumpInsn(IF_ICMPNE, END); // condition
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
			this.methodVisitor.visitInsn(type.getOpcode(IRETURN));
		} else {
			this.methodVisitor.visitInsn(RETURN);
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
		this.typeStack.pop();
		this.methodVisitor.visitInsn(ATHROW);
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
		this.methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "err", ps);
		this.methodVisitor.visitLdcInsn(Node.ErrorMessage);
		this.methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
		return true;
	}

	public void VisitEnd() {
		//this.methodVisitor.visitInsn(RETURN);//FIXME
		this.methodVisitor.visitEnd();
	}

	public void visitBoxingAndReturn() {
		if(this.typeStack.empty()) {
			this.methodVisitor.visitInsn(ACONST_NULL);
		} else {
			this.box();
		}
		this.methodVisitor.visitInsn(ARETURN);
	}
}
