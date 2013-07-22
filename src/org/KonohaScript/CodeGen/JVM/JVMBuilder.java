package org.KonohaScript.CodeGen.JVM;

import java.util.HashMap;

import org.KonohaScript.KonohaClass;
import org.KonohaScript.KonohaMethod;
import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaType;
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

class Stack {
	private int	stackTop		= 0;
	private int	maxStackSize	= 0;

	public int getStackTop() {
		return this.stackTop;
	}

	public void setStackTop(int stackTop) {
		this.stackTop = stackTop;
	}

	public int getMaxStackSize() {
		return this.maxStackSize;
	}

	public void setMaxStackSize(int maxStackSize) {
		this.maxStackSize = maxStackSize;
	}

	public void push() {
		int st = this.getStackTop() + 1;
		this.setStackTop(st);
		if(st > this.getMaxStackSize()) {
			this.setMaxStackSize(st);
		}
	}

	public void pop() {
		this.setStackTop(this.getStackTop() - 1);
	}
}

class BinaryOperator {
	int	OpCode;

	public BinaryOperator(int OpCode) {
		this.OpCode = OpCode;
	}

	void CodeGen(Stack stack, MethodVisitor visitor) {
		stack.pop();
		stack.pop();
		stack.push();
		visitor.visitInsn(this.OpCode);
	}
}

class RelationalBinaryOperator extends BinaryOperator implements Opcodes {

	public RelationalBinaryOperator(int OpCode) {
		super(OpCode);
	}

	@Override
	void CodeGen(Stack stack, MethodVisitor visitor) {
		stack.pop();
		stack.pop();
		stack.push();
		Label FALSE = new Label();
		Label END = new Label();
		visitor.visitJumpInsn(this.OpCode, FALSE); // condition
		visitor.visitInsn(ICONST_1); // true
		visitor.visitJumpInsn(GOTO, END);
		visitor.visitLabel(FALSE);
		visitor.visitInsn(ICONST_0); // false
		visitor.visitLabel(END);
	}
}

class JVMBuilder extends CodeGenerator implements Opcodes {

	MethodVisitor					methodVisitor;
	Stack							stack;
	LabelStack						LabelStack;
	HashMap<String, BinaryOperator>	binaryOperatorMap;
	KonohaNameSpace					NameSpace;
	TypeResolver					TypeResolver;

	public JVMBuilder(KonohaMethod method, MethodVisitor mv, TypeResolver TypeResolver) {
		super(method);
		this.methodVisitor = mv;
		this.initBinaryOpcodeMap();
		this.stack = new Stack();
		this.LabelStack = new LabelStack();
		this.TypeResolver = TypeResolver;
	}

	private void initBinaryOpcodeMap() {
		BinaryOperator opADD = new BinaryOperator(IADD);
		BinaryOperator opSUB = new BinaryOperator(ISUB);
		BinaryOperator opMUL = new BinaryOperator(IMUL);
		BinaryOperator opDIV = new BinaryOperator(IDIV);
		BinaryOperator opREM = new BinaryOperator(IREM);

		BinaryOperator opLT = new RelationalBinaryOperator(IF_ICMPGE);
		BinaryOperator opLE = new RelationalBinaryOperator(IF_ICMPGT);
		BinaryOperator opGT = new RelationalBinaryOperator(IF_ICMPLE);
		BinaryOperator opGE = new RelationalBinaryOperator(IF_ICMPLT);
		BinaryOperator opEQ = new RelationalBinaryOperator(IF_ICMPNE);
		BinaryOperator opNE = new RelationalBinaryOperator(IF_ICMPEQ);

		this.binaryOperatorMap = new HashMap<String, BinaryOperator>();
		this.binaryOperatorMap.put("+", opADD);
		this.binaryOperatorMap.put("-", opSUB);
		this.binaryOperatorMap.put("*", opMUL);
		this.binaryOperatorMap.put("/", opDIV);
		this.binaryOperatorMap.put("%", opREM);
		this.binaryOperatorMap.put("<", opLT);
		this.binaryOperatorMap.put("<=", opLE);
		this.binaryOperatorMap.put(">", opGT);
		this.binaryOperatorMap.put(">=", opGE);
		this.binaryOperatorMap.put("==", opEQ);
		this.binaryOperatorMap.put("!=", opNE);
		// FIXME add other binary operator
	}

	String getMethodDescriptor(KonohaMethod Method) {
		return this.TypeResolver.GetJavaMethodDescriptor(Method);
	}

	void LoadLocal(Local local) {
		KonohaType type = local.TypeInfo;
		//TODO check KonohaType -> Type

		this.stack.push();
		this.methodVisitor.visitVarInsn(ILOAD, local.Index);
		// this.asmctx.getMethodVisitor().visitVarInsn(type.getOpcode(ILOAD), local.Index);
	}

	void StoreLocal(Local local) {
		KonohaType type = local.TypeInfo;
		//TODO check KonohaType -> Type

		this.stack.pop();
		this.methodVisitor.visitVarInsn(ISTORE, local.Index);
		// this.asmctx.getMethodVisitor().visitVarInsn(type.getOpcode(ISTORE), local.Index);
	}

	void LoadConst(Object o) {
		this.stack.push();
		this.methodVisitor.visitLdcInsn(o);
	}

	private boolean isMethodBinaryOperator(ApplyNode Node) {
		String methodName = Node.Method.MethodName;
		for(String op : this.binaryOperatorMap.keySet()) {
			if(op.equals(methodName)) {
				return true;
			}
		}
		return false;
	}

	void BinaryOp(String opName) {
		this.binaryOperatorMap.get(opName).CodeGen(this.stack, this.methodVisitor);
	}

	void Call(int opcode, KonohaMethod Method) {
		//String className = Method.ClassInfo.ShortClassName;
		String methodName = Method.MethodName;
		Class<?> OwnerClass = Method.ClassInfo.HostedClassInfo;
		if(OwnerClass == null) {
			OwnerClass = Method.ClassInfo.DefaultNullValue.getClass();
		}
		String owner = OwnerClass.getName();
		String methodDescriptor = this.getMethodDescriptor(Method);
		this.methodVisitor.visitMethodInsn(opcode, owner, methodName, methodDescriptor);
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
		return false;
	}

	@Override
	public boolean VisitNull(NullNode Node) {
		// TODO Auto-generated method stub
		return false;
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
		}
		if(this.isMethodBinaryOperator(Node)) {
			String opName = Node.Method.MethodName;
			this.BinaryOp(opName);
		} else {
			int opcode = INVOKEVIRTUAL;
			if(Node.Method.Is(KonohaConst.StaticMethod)) {
				opcode = INVOKESTATIC;
			}
			String methodName = Node.Method.MethodName;
			this.Call(opcode, Node.Method);
		}
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
		this.stack.pop();
		this.stack.pop();
		this.stack.push();
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
		}

		assert (Node.TypeInfo != null);

		//FIXME (ide) check return type
		MethodVisitor mv = this.methodVisitor;
		if(Node.TypeInfo.ShortClassName.equals("Void")) {
			mv.visitInsn(RETURN);
		} else if(Node.TypeInfo.ShortClassName.equals("Integer")) {
			mv.visitInsn(IRETURN);
		} else {
			mv.visitInsn(ARETURN);
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
		// TODO Auto-generated method stub
		Node.Expr.Evaluate(this);
		return false;
	}

	@Override
	public boolean VisitFunction(FunctionNode Node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean VisitError(ErrorNode Node) {
		// TODO Auto-generated method stub
		return false;
	}

	public void VisitEnd() {
		int maxStack = this.stack.getMaxStackSize();
		int maxLocal = this.LocalVals.size();
		this.methodVisitor.visitMaxs(maxStack, maxLocal);
		this.methodVisitor.visitEnd();
	}

}