package org.KonohaScript.Grammar;

import org.KonohaScript.KonohaDef;
import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaParam;
import org.KonohaScript.KonohaType;
import org.KonohaScript.JUtils.KonohaConst;

public class KonohaSystemDef extends KonohaDef implements KonohaConst {

	@Override
	public void MakeDefinition(KonohaNameSpace NameSpace) {
		KonohaType BaseClass = NameSpace.LookupHostLangType(KonohaSystemDef.class);
		NameSpace.DefineSymbol("System", BaseClass);

		KonohaParam param1 = KonohaParam.ParseOf(NameSpace, "void int x");
		BaseClass.DefineMethod(StaticMethod, "p", param1, this, "p");
		KonohaParam param2 = KonohaParam.ParseOf(NameSpace, "void String x");
		BaseClass.DefineMethod(StaticMethod, "p", param2, this, "p");
		KonohaParam param3 = KonohaParam.ParseOf(NameSpace, "void boolean x");
		BaseClass.DefineMethod(StaticMethod, "p", param3, this, "p");
	}

	public static void p(int x) {
		System.out.println(x);
	}

}
