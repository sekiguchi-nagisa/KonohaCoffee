/****************************************************************************
 * Copyright (c) 2013, the Konoha project authors. All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ***************************************************************************/

package org.KonohaScript;

import java.util.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.KonohaScript.SyntaxTree.TypedNode;

public class KonohaMethod extends KonohaDef implements KonohaConst {

	public KonohaType ClassInfo;
	public String MethodName;
	// int MethodSymbol;
	// int CanonicalSymbol;
	public KonohaParam Param;
	Method MethodRef;

	// String Source;
	// long uline;
	// KNameSpace LazyCompileNameSpace;

	public KonohaMethod(int MethodFlag, KonohaType ClassInfo, String MethodName, KonohaParam Param, Method MethodRef) {
		this.MethodFlag = MethodFlag;
		this.ClassInfo = ClassInfo;
		this.MethodName = MethodName;
		this.Param = Param;
		this.MethodRef = MethodRef;
	}

	public int MethodFlag;
	public boolean Is(int Flag) {
		return ((MethodFlag & Flag) == Flag);
	}

	public final KonohaType GetReturnType(KonohaType BaseType) {
		KonohaType ReturnType = Param.Types[0];
		return ReturnType;
	}

	public final KonohaType GetParamType(KonohaType BaseType, int ParamIdx) {
		KonohaType ParamType = Param.Types[ParamIdx + Param.ReturnSize];
		return ParamType;
	}

	public final boolean Match(KonohaMethod Other) {
		return (MethodName.equals(Other.MethodName) && Param.Match(Other.Param));
	}

	public boolean Match(String MethodName, int ParamSize /*, int DataSize, KClass[] ParamData */) {
		if(MethodName.equals(this.MethodName)) {
			if(Param.GetParamSize() == ParamSize) {
				return true;
			}
		}
		return false;
	}

	boolean IsStaticInvocation() {
		return Modifier.isStatic(MethodRef.getModifiers());
	}

	public Object Eval(Object[] ParamData) {
		int ParamSize = Param.GetParamSize();
		try {
			if(IsStaticInvocation()) {
				switch (ParamSize) {
				case 0:
					return MethodRef.invoke(null, ParamData[0]);
				case 1:
					return MethodRef.invoke(null, ParamData[0], ParamData[1]);
				default:
					return MethodRef.invoke(null, ParamData); // FIXME
				}
			}
			else {
				switch (ParamSize) {
				case 0:
					return MethodRef.invoke(ParamData[0]);
				case 1:
					return MethodRef.invoke(ParamData[0], ParamData[1]);
				default:
					return MethodRef.invoke(ParamData[0], ParamData); // FIXME
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
		KonohaDebug.P("ParamSize: " + ParamSize);
		return null;
	}


	// DoLazyComilation();

	KonohaNameSpace LazyNameSpace;
	ArrayList<KonohaToken> SourceList;

	public KonohaMethod(int MethodFlag, KonohaType ClassInfo, String MethodName, KonohaParam Param, KonohaNameSpace LazyNameSpace, ArrayList<KonohaToken> SourceList) {
		this(MethodFlag, ClassInfo, MethodName, Param, null);
		this.LazyNameSpace = LazyNameSpace;
		this.SourceList = SourceList;
	}

	public KonohaMethod DoCompilation() {
		if(MethodRef == null) {
			ArrayList<KonohaToken> BufferList = new ArrayList<KonohaToken>();
			LazyNameSpace.PreProcess(SourceList, 0, SourceList.size(), BufferList);
			UntypedNode UNode = UntypedNode.ParseNewNode(LazyNameSpace, null, BufferList, 0, BufferList.size(), AllowEmpty);
			System.out.println("untyped tree: " + UNode);
			TypeEnv Gamma = new TypeEnv(LazyNameSpace, this);
			TypedNode TNode = TypeEnv.TypeCheck(Gamma, UNode, Gamma.VoidType, DefaultTypeCheckPolicy);
			KonohaBuilder Builder = LazyNameSpace.GetBuilder();
			return Builder.Build(TNode, this);
		}
		return this;
	}

}
