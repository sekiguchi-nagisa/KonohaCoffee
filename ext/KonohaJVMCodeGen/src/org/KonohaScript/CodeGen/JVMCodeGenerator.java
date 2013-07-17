package org.KonohaScript.CodeGen;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

import org.KonohaScript.KonohaBuilder;
import org.KonohaScript.KonohaMethod;
import org.KonohaScript.KonohaMethodInvoker;
import org.KonohaScript.KonohaParam;
import org.KonohaScript.KonohaType;
import org.KonohaScript.NativeMethodInvoker;
import org.KonohaScript.KLib.KonohaArray;
import org.KonohaScript.ObjectModel.KonohaObject;
import org.KonohaScript.SyntaxTree.*;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.MethodNode;

public class JVMCodeGenerator implements KonohaBuilder, Opcodes {

	private final Map<String, KClassNode> classMap = new HashMap<String, KClassNode>();
	private final Map<String, String> typeDescriptorMap = new HashMap<String, String>();
	private int evalCount = 0;

	static class KClassNode {
		final String name;
		final Map<String, MethodNode> methods = new HashMap<String, MethodNode>();

		public KClassNode(String name) {
			this.name = name;
		}

		public void accept(ClassVisitor cv) {
			cv.visit(V1_6, ACC_PUBLIC, name, null, "java/lang/Object", null);
			for(MethodNode m : methods.values()) {
				m.accept(cv);
			}
		}

		public MethodNode getMethodNode(String name) {
			return methods.get(name);
		}
	}

	class KonohaClassLoader extends ClassLoader {
		@Override public Class<?> findClass(String name) {
			byte[] b = generateBytecode(name);
			return defineClass(name, b, 0, b.length);
		}
	}
	
	public JVMCodeGenerator() {
		this.initTypeDescriptorMap();
	}

	private void initTypeDescriptorMap() {
		this.typeDescriptorMap.put("Void", Type.getType(void.class).getDescriptor());
		this.typeDescriptorMap.put("Boolean", Type.getType(boolean.class).getDescriptor());
		this.typeDescriptorMap.put("Integer", Type.getType(int.class).getDescriptor());
		this.typeDescriptorMap.put("Object", Type.getType(Object.class).getDescriptor());
		// TODO: other class	
	}

	private String getTypeDescriptor(KonohaType type) {
		String descriptor;
		if((descriptor = this.typeDescriptorMap.get(type.ShortClassName)) != null) {
			return descriptor;
		}
		return "L" + type.ShortClassName + ";"; // FIXME
	}

	String getMethodDescriptor(KonohaMethod method) {
		KonohaType returnType = method.GetReturnType(null);
		ArrayList<KonohaType> paramTypes = new ArrayList<KonohaType>(Arrays.asList(method.Param.Types));
		paramTypes.remove(0);
		StringBuilder signature = new StringBuilder();
		signature.append("(");
		for(KonohaType param : paramTypes) {
			signature.append(this.getTypeDescriptor(param));
		}
		signature.append(")");
		signature.append(this.getTypeDescriptor(returnType));
		return signature.toString();
	}

	public void OutputClassFile(String className, String dir) throws IOException {
		byte[] ba = this.generateBytecode(className);
		File file = new File(dir, className + ".class");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(ba);
		} finally {
			if(fos != null) {
				fos.close();
			}
		}
	}

	private byte[] generateBytecode(String className) {
		ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		this.classMap.get(className).accept(classWriter);
		return classWriter.toByteArray();
	}

	public KonohaMethodInvoker Compile(TypedNode Block, KonohaMethod MethodInfo) {
		return Compile(Block, MethodInfo, null);
	}

	public KonohaMethodInvoker Compile(TypedNode Block, KonohaMethod MethodInfo, KonohaArray params) {
		String className;
		String methodName;
		String methodDescriptor;
		KonohaParam param;
		boolean is_eval = false;
		if(MethodInfo != null && MethodInfo.MethodName.length() > 0) {
//			className = MethodInfo.ClassInfo.ShortClassName;
			className = "Script";
			methodName = MethodInfo.MethodName;
			methodDescriptor = this.getMethodDescriptor(MethodInfo);
			param = MethodInfo.Param;
		} else {
			className = "Script";
			methodName = "__eval" + (evalCount++);
			methodDescriptor = "()Ljava/lang/Object;";
			is_eval = true;
			param = null;
		}
		
		KClassNode cn = classMap.get(className);
		if(cn == null) {
			cn = new KClassNode(className);
			classMap.put(cn.name, cn);
		}
		
		MethodNode mn = null;
		for(MethodNode m : cn.methods.values()) {
			if(m.name.equals(methodName) && m.desc.equals(methodDescriptor)) {
				mn = m;
				break;
			}
		}
		if(mn == null) {
			mn = new MethodNode(ACC_PUBLIC | ACC_STATIC, methodName, methodDescriptor, null, null);
		} else {
			mn.instructions.clear();
		}
		mn.visitCode();
		
		JVMBuilder b = new JVMBuilder(null, mn);
		if(params != null) {
			for(int i=0; i<params.size(); i++) {
				Local local = (Local)params.get(i);
				b.AddLocal(local.TypeInfo, local.Name);
			}
		}
		b.VisitList(Block.GetHeadNode());
		if(is_eval) {
			//FIXME
			if(b.stack.getStackTop() == 0) {
				mn.visitInsn(ACONST_NULL);
			} else {
				mn.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
			}
			mn.visitInsn(ARETURN);
		}
		
		cn.methods.put(methodName, mn);
		
//		try { this.OutputClassFile("Script", "."); }
//		catch(Exception e) { e.printStackTrace(); }
		
		Class<?> c = new KonohaClassLoader().findClass(className);
		for(Method m : c.getMethods()) {
			if(m.getName().equals(methodName)) {
				KonohaMethodInvoker mtd = new NativeMethodInvoker(param, m);
				return mtd;
			}
		}
		return null;
	}

	@Override
	public Object EvalAtTopLevel(TypedNode Node, KonohaObject GlobalObject) {
		KonohaMethodInvoker Invoker = this.Compile(Node, null);
		return Invoker.Invoke(null);
	}

	@Override
	public KonohaMethodInvoker Build(TypedNode Node, KonohaMethod Method) {
		return this.Compile(Node, Method);
	}

}
