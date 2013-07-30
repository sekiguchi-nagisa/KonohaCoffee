package org.KonohaScript.CodeGen.JVM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.KonohaScript.KonohaMethod;
import org.KonohaScript.KonohaType;
import org.KonohaScript.ObjectModel.KonohaObject;
import org.objectweb.asm.Type;

public class TypeResolver {
	private final Map<String, KClassNode>	classMap			= new HashMap<String, KClassNode>();
	private final Map<String, String>		typeDescriptorMap	= new HashMap<String, String>();

	// FIXME
	String globalType = Type.getType(KonohaObject.class).getDescriptor();

	public TypeResolver() {
		this.typeDescriptorMap.put("global", Type.getType(KonohaObject.class).getDescriptor());
		this.typeDescriptorMap.put("Void", Type.getType(void.class).getDescriptor());
		this.typeDescriptorMap.put("Boolean", Type.getType(boolean.class).getDescriptor());
		this.typeDescriptorMap.put("Integer", Type.getType(int.class).getDescriptor());
		this.typeDescriptorMap.put("Object", Type.getType(Object.class).getDescriptor());
		// TODO: other class
	}

	public String GetJavaTypeDescriptor(KonohaType type) {
		String descriptor = this.typeDescriptorMap.get(type.ShortClassName);
		if(descriptor != null) {
			return descriptor;
		}
		if(type.HostedClassInfo != null) {
			return Type.getDescriptor(type.HostedClassInfo);
		} else {
			return "L" + type.ShortClassName + ";";//FIXME
		}
	}

	public String GetJavaMethodDescriptor(KonohaMethod method) {
		KonohaType returnType = method.GetReturnType(null);
		ArrayList<KonohaType> paramTypes = new ArrayList<KonohaType>(Arrays.asList(method.Param.Types));
		paramTypes.remove(0);
		StringBuilder signature = new StringBuilder();
		signature.append("(");
		if(method.ClassInfo.ShortClassName.equals("global")) {
			signature.append(globalType);
		}
		for(int i = 0; i < paramTypes.size(); i++) {
			KonohaType ParamType = paramTypes.get(i);
			signature.append(this.GetJavaTypeDescriptor(ParamType));
		}
		signature.append(")");
		if(method.MethodName.equals("New")) {
			signature.append(Type.VOID_TYPE.getDescriptor());
		} else {
			signature.append(this.GetJavaTypeDescriptor(returnType));
		}
		return signature.toString();
	}

	public KClassNode FindClassNode(String className) {
		return this.classMap.get(className);
	}

	public void StoreClassNode(KClassNode cn) {
		this.classMap.put(cn.name, cn);
	}

	public Type GetAsmType(Class<?> klass) {
		return Type.getType(klass);
	}

	public Type GetAsmType(KonohaType type) {
		return Type.getType(GetJavaTypeDescriptor(type));
	}
}
