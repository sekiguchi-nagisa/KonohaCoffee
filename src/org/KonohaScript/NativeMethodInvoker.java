package org.KonohaScript;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class NativeMethodInvoker extends KonohaMethodInvoker {

	public NativeMethodInvoker(KonohaParam Param, Method MethodRef) {
		super(Param, MethodRef);
	}

	public Method GetMethodRef() {
		return (Method) this.CompiledCode;
	}

	boolean IsStaticInvocation() {
		return Modifier.isStatic(this.GetMethodRef().getModifiers());
	}

	@Override
	public Object Invoke(Object[] Args) {
		int ParamSize = this.Param != null ? this.Param.GetParamSize() : 0;
		try {
			Method MethodRef = this.GetMethodRef();
			if(this.IsStaticInvocation()) {
				switch (ParamSize) {
				case 0:
					return MethodRef.invoke(null, Args[0]);
				case 1:
					return MethodRef.invoke(null, Args[0], Args[1]);
				case 2:
					return MethodRef.invoke(null, Args[0], Args[0], Args[2]);
				case 3:
					return MethodRef.invoke(null, Args[0], Args[0], Args[2], Args[3]);
				case 4:
					return MethodRef.invoke(null, Args[0], Args[1], Args[2], Args[3], Args[4]);
				case 5:
					return MethodRef.invoke(null, Args[0], Args[1], Args[2], Args[3], Args[4], Args[5]);
				default:
					return MethodRef.invoke(null, Args); // FIXME
				}
			} else {
				switch (ParamSize) {
				case 0:
					return MethodRef.invoke(Args[0]);
				case 1:
					return MethodRef.invoke(Args[0], Args[1]);
				case 2:
					return MethodRef.invoke(Args[0], Args[0], Args[2]);
				case 3:
					return MethodRef.invoke(Args[0], Args[0], Args[2], Args[3]);
				case 4:
					return MethodRef.invoke(Args[0], Args[1], Args[2], Args[3], Args[4]);
				case 5:
					return MethodRef.invoke(Args[0], Args[1], Args[2], Args[3], Args[4], Args[5]);
				default:
					return MethodRef.invoke(Args[0], Args); // FIXME
				}
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}