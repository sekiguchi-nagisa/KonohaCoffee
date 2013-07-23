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

		KonohaParam param1 = KonohaParam.ParseOf(NameSpace, "void Object x");
		BaseClass.DefineMethod(StaticMethod, "p", param1, this, "p");
	}

	public static void p(int x) {
		System.out.println(x);
	}

}
