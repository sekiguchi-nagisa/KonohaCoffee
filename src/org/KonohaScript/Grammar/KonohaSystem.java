package org.KonohaScript.Grammar;

import org.KonohaScript.*;
import org.KonohaScript.JUtils.KonohaConst;

public class KonohaSystem extends KonohaDef implements KonohaConst {

	@Override
	public void MakeDefinition(KonohaNameSpace ns) {
		KonohaType BaseClass = ns.LookupHostLangType(KonohaSystem.class);
		KonohaParam param = KonohaParam.ParseOf(ns, "void int x");
		BaseClass.DefineMethod(ConstMethod|StaticMethod, "p", param, this, "p");
	}

	public static void p(int x) {
		System.out.println(x);
	}

}
