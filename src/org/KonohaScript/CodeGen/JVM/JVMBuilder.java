package org.KonohaScript.CodeGen.JVM;

import java.util.HashMap;

import org.KonohaScript.KonohaClass;
import org.KonohaScript.KonohaMethod;
import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaType;
import org.KonohaScript.CodeGen.CodeGenerator;
import org.KonohaScript.CodeGen.Local;
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

class JVMBuilder extends CodeGenerator implements Opcodes {

	MethodVisitor					methodVisitor;
	Stack							stack;
	HashMap<String, BinaryOperator>	binaryOperatorMap;
	HashMap<String, String>			methodDescriptorMap;
	KonohaNameSpace					NameSpace;

	public JVMBuilder(KonohaMethod mi, MethodVisitor mv) {
		super(mi);
		this.methodVisitor = mv;
		this.initBinaryOpcodeMap();
		this.stack = new Stack();
		this.methodDescriptorMap = new HashMap<String, String>();
	}

	static abstract class BinaryOperator {
		abstract void codeGen();
	}

	static class Stack {
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

	private void initBinaryOpcodeMap() {
		BinaryOperator opADD = new BinaryOperator() {
			@Override
			void codeGen() {
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.push();
				JVMBuilder.this.methodVisitor.visitInsn(IADD);
			}
		};

		BinaryOperator opSUB = new BinaryOperator() {
			@Override
			void codeGen() {
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.push();
				JVMBuilder.this.methodVisitor.visitInsn(ISUB);
			}
		};

		BinaryOperator opMUL = new BinaryOperator() {
			@Override
			void codeGen() {
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.push();
				JVMBuilder.this.methodVisitor.visitInsn(IMUL);
			}
		};

		BinaryOperator opDIV = new BinaryOperator() {
			@Override
			void codeGen() {
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.push();
				JVMBuilder.this.methodVisitor.visitInsn(IDIV);
			}
		};

		BinaryOperator opREM = new BinaryOperator() {
			@Override
			void codeGen() {
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.push();
				JVMBuilder.this.methodVisitor.visitInsn(IREM);
			}
		};

		BinaryOperator opLT = new BinaryOperator() {
			@Override
			void codeGen() {
				Label FALSE = new Label();
				Label END = new Label();
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.push();
				JVMBuilder.this.methodVisitor.visitJumpInsn(IF_ICMPGE, FALSE); // condition
				JVMBuilder.this.methodVisitor.visitInsn(ICONST_1); // true
				JVMBuilder.this.methodVisitor.visitJumpInsn(GOTO, END);
				JVMBuilder.this.methodVisitor.visitLabel(FALSE);
				JVMBuilder.this.methodVisitor.visitInsn(ICONST_0); // false
				JVMBuilder.this.methodVisitor.visitLabel(END);
			}
		};

		BinaryOperator opLE = new BinaryOperator() {
			@Override
			void codeGen() {
				Label FALSE = new Label();
				Label END = new Label();
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.push();
				JVMBuilder.this.methodVisitor.visitJumpInsn(IF_ICMPGT, FALSE); // condition
				JVMBuilder.this.methodVisitor.visitInsn(ICONST_1); // true
				JVMBuilder.this.methodVisitor.visitJumpInsn(GOTO, END);
				JVMBuilder.this.methodVisitor.visitLabel(FALSE);
				JVMBuilder.this.methodVisitor.visitInsn(ICONST_0); // false
				JVMBuilder.this.methodVisitor.visitLabel(END);
			}
		};

		BinaryOperator opGT = new BinaryOperator() {
			@Override
			void codeGen() {
				Label FALSE = new Label();
				Label END = new Label();
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.push();
				JVMBuilder.this.methodVisitor.visitJumpInsn(IF_ICMPLE, FALSE); // condition
				JVMBuilder.this.methodVisitor.visitInsn(ICONST_1); // true
				JVMBuilder.this.methodVisitor.visitJumpInsn(GOTO, END);
				JVMBuilder.this.methodVisitor.visitLabel(FALSE);
				JVMBuilder.this.methodVisitor.visitInsn(ICONST_0); // false
				JVMBuilder.this.methodVisitor.visitLabel(END);
			}
		};

		BinaryOperator opGE = new BinaryOperator() {
			@Override
			void codeGen() {
				Label FALSE = new Label();
				Label END = new Label();
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.push();
				JVMBuilder.this.methodVisitor.visitJumpInsn(IF_ICMPLT, FALSE); // condition
				JVMBuilder.this.methodVisitor.visitInsn(ICONST_1); // true
				JVMBuilder.this.methodVisitor.visitJumpInsn(GOTO, END);
				JVMBuilder.this.methodVisitor.visitLabel(FALSE);
				JVMBuilder.this.methodVisitor.visitInsn(ICONST_0); // false
				JVMBuilder.this.methodVisitor.visitLabel(END);
			}
		};

		BinaryOperator opEQ = new BinaryOperator() {
			@Override
			void codeGen() {
				Label FALSE = new Label();
				Label END = new Label();
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.push();
				JVMBuilder.this.methodVisitor.visitJumpInsn(IF_ICMPNE, FALSE); // condition
				JVMBuilder.this.methodVisitor.visitInsn(ICONST_1); // true
				JVMBuilder.this.methodVisitor.visitJumpInsn(GOTO, END);
				JVMBuilder.this.methodVisitor.visitLabel(FALSE);
				JVMBuilder.this.methodVisitor.visitInsn(ICONST_0); // false
				JVMBuilder.this.methodVisitor.visitLabel(END);
			}
		};

		BinaryOperator opNE = new BinaryOperator() {
			@Override
			void codeGen() {
				Label FALSE = new Label();
				Label END = new Label();
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.pop();
				JVMBuilder.this.stack.push();
				JVMBuilder.this.methodVisitor.visitJumpInsn(IF_ICMPEQ, FALSE); // condition
				JVMBuilder.this.methodVisitor.visitInsn(ICONST_1); // true
				JVMBuilder.this.methodVisitor.visitJumpInsn(GOTO, END);
				JVMBuilder.this.methodVisitor.visitLabel(FALSE);
				JVMBuilder.this.methodVisitor.visitInsn(ICONST_0); // false
				JVMBuilder.this.methodVisitor.visitLabel(END);
			}
		};

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
		// add other binary operator
	}

	String getMethodDescriptor(String className, String methodName) {
		return this.methodDescriptorMap.get(className + "." + methodName);
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
		this.binaryOperatorMap.get(opName).codeGen();
	}

	void Call(int opcode, String className, String methodName) {
		String owner = "org/KonohaScript/CodeGen/" + className;
		String methodDescriptor = this.getMethodDescriptor(className, methodName);
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
			int opcode = INVOKESTATIC; // support other opcode
			String methodName = Node.Method.MethodName;
			this.Call(opcode, "Script", methodName); // TODO support other class method
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
		// TODO Auto-generated method stub
		Node.CondExpr.Evaluate(this);
		Node.IterationExpr.Evaluate(this);
		this.VisitList(Node.LoopBody);
		return true;
	}

	@Override
	public boolean VisitReturn(ReturnNode Node) {
		//FIXME check Node.Expr null check
		Node.Expr.Evaluate(this);

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean VisitJump(JumpNode Node) {
		// TODO Auto-generated method stub
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