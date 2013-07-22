package org.KonohaScript.CodeGen.JVM;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.KonohaScript.KonohaBuilder;
import org.KonohaScript.KonohaMethod;
import org.KonohaScript.KonohaMethodInvoker;
import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaParam;
import org.KonohaScript.KonohaType;
import org.KonohaScript.NativeMethodInvoker;
import org.KonohaScript.CodeGen.Local;
import org.KonohaScript.KLib.KonohaArray;
import org.KonohaScript.ObjectModel.KonohaObject;
import org.KonohaScript.SyntaxTree.TypedNode;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.MethodNode;

class KClassNode implements Opcodes {
	final String					name;
	final Map<String, MethodNode>	methods	= new HashMap<String, MethodNode>();

	public KClassNode(String name) {
		this.name = name;
	}

	public void accept(ClassVisitor cv) {
		cv.visit(V1_6, ACC_PUBLIC, this.name, null, "java/lang/Object", null);
		for(MethodNode m : this.methods.values()) {
			m.accept(cv);
		}
	}

	public MethodNode getMethodNode(String name) {
		return this.methods.get(name);
	}
}

public class JVMCodeGenerator implements KonohaBuilder, Opcodes {

	private final Map<String, KClassNode>	classMap			= new HashMap<String, KClassNode>();
	private final Map<String, String>		typeDescriptorMap	= new HashMap<String, String>();
	private int								evalCount			= 0;

	class KonohaClassLoader extends ClassLoader {
		@Override
		public Class<?> findClass(String name) {
			byte[] b = JVMCodeGenerator.this.generateBytecode(name);
			return this.defineClass(name, b, 0, b.length);
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
		for(int i = 0; i < paramTypes.size(); i++) {
			KonohaType ParamType = paramTypes.get(i);
			signature.append(this.getTypeDescriptor(ParamType));
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

	public KonohaMethodInvoker Compile(KonohaNameSpace NameSpace, TypedNode Block, KonohaMethod MethodInfo) {
		return this.Compile(NameSpace, Block, MethodInfo, null);
	}

	public KonohaMethodInvoker Compile(KonohaNameSpace NameSpace, TypedNode Block, KonohaMethod MethodInfo, KonohaArray params) {
		String className;
		String methodName;
		String methodDescriptor;
		KonohaParam param;
		boolean is_eval = false;
		if(MethodInfo != null && MethodInfo.MethodName.length() > 0) {
			className = MethodInfo.ClassInfo.ShortClassName;
			//className = "Script";
			methodName = MethodInfo.MethodName;
			methodDescriptor = this.getMethodDescriptor(MethodInfo);
			param = MethodInfo.Param;
		} else {
			className = "Script";
			methodName = "__eval" + (this.evalCount++);
			methodDescriptor = "()Ljava/lang/Object;";
			is_eval = true;
			param = null;
		}

		KClassNode cn = this.classMap.get(className);
		if(cn == null) {
			cn = new KClassNode(className);
			this.classMap.put(cn.name, cn);
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

		JVMBuilder b = new JVMBuilder(MethodInfo, mn);
		if(params != null) {
			for(int i = 0; i < params.size(); i++) {
				Local local = (Local) params.get(i);
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
		Method[] MethodList = c.getMethods();
		for(int i = 0; i < MethodList.length; i++) {
			Method m = MethodList[i];
			if(m.getName().equals(methodName)) {
				KonohaMethodInvoker mtd = new NativeMethodInvoker(param, m);
				return mtd;
			}
		}
		return null;
	}

	@Override
	public Object EvalAtTopLevel(KonohaNameSpace NameSpace, TypedNode Node, KonohaObject GlobalObject) {
		KonohaMethodInvoker Invoker = this.Compile(NameSpace, Node, null);
		return Invoker.Invoke(null);
	}

	@Override
	public KonohaMethodInvoker Build(KonohaNameSpace NameSpace, TypedNode Node, KonohaMethod Method) {
		KonohaArray Param = null;
		if(Method != null) {
			Param = new KonohaArray();
			KonohaParam P = Method.Param;
			Param.add(new Local(0, Method.ClassInfo, "this"));
			for(int i = 0; i < P.GetParamSize(); i++) {
				KonohaType Type = P.Types[i + 1];
				String Arg = P.ArgNames[i];
				Local l = new Local(i + 1, Type, Arg);
				Param.add(l);
			}
		}
		return this.Compile(NameSpace, Node, Method, Param);
	}

}
