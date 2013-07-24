package org.KonohaScript.CodeGen.JVM;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
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

class KonohaClassLoader extends ClassLoader {
	JVMCodeGenerator Gen;

	public KonohaClassLoader(JVMCodeGenerator Gen) {
		this.Gen = Gen;
	}

	@Override
	protected Class<?> findClass(String name) {
		byte[] b = Gen.generateBytecode(name);
		return this.defineClass(name, b, 0, b.length);
	}
}

public class JVMCodeGenerator implements KonohaBuilder, Opcodes {
	private final TypeResolver	TypeResolver;

	public JVMCodeGenerator() {
		this.TypeResolver = new TypeResolver();
	}

	private String getTypeDescriptor(KonohaType type) {
		return this.TypeResolver.GetJavaTypeDescriptor(type);
	}

	String getMethodDescriptor(KonohaMethod Method) {
		return this.TypeResolver.GetJavaMethodDescriptor(Method);
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

	public byte[] generateBytecode(String className) {
		ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		KClassNode CNode = this.TypeResolver.FindClassNode(className);
		CNode.accept(classWriter);
		classWriter.visitEnd();
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
			KonohaType GlobalType = NameSpace.GetGlobalObject().TypeInfo;
			className = "global";
			methodName = "__eval";
			methodDescriptor = "(" + this.getTypeDescriptor(GlobalType) + ")Ljava/lang/Object;";
			is_eval = true;
			KonohaType[] ParamData = new KonohaType[2];
			String[] ArgNames = new String[1];
			ParamData[0] = NameSpace.KonohaContext.ObjectType;
			ParamData[1] = GlobalType;
			ArgNames[0] = "this";
			param = new KonohaParam(1, ParamData, ArgNames);
			params = new KonohaArray();
			params.add(new Local(0, GlobalType, "this"));
		}

		KClassNode cn = this.TypeResolver.FindClassNode(className);
		if(cn == null) {
			cn = new KClassNode(className);
			this.TypeResolver.StoreClassNode(cn);
		}

		MethodNode mn = null;
		for(MethodNode m : cn.methods.values()) {
			if(m.name.equals(methodName) && m.desc.equals(methodDescriptor)) {
				mn = m;
				break;
			}
		}
		if(mn == null) {
			int MethodAttr = ACC_PUBLIC;
//			if(methodName.equals("__eval")) {
				MethodAttr = MethodAttr | ACC_STATIC;
//			}
			mn = new MethodNode(MethodAttr, methodName, methodDescriptor, null, null);
		} else {
			mn.instructions.clear();
		}
		mn.visitCode();

		JVMBuilder b = new JVMBuilder(MethodInfo, mn, this.TypeResolver);
		if(params != null) {
			for(int i = 0; i < params.size(); i++) {
				Local local = (Local) params.get(i);
				b.AddLocal(local.TypeInfo, local.Name);
			}
		}
		b.VisitList(Block.GetHeadNode());
		if(is_eval) {
			b.visitBoxingAndReturn();
		}

		mn.visitEnd();
		cn.methods.put(methodName, mn);

		try {
			this.OutputClassFile("global", ".");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Class<?> c = new KonohaClassLoader(this).findClass(className);
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
		Object[] Args = new Object[1];
		Args[0] = GlobalObject;
		return Invoker.Invoke(Args);
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
