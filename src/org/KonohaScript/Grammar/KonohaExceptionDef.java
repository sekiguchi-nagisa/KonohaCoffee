package org.KonohaScript.Grammar;

import org.KonohaScript.KonohaDef;
import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaParam;
import org.KonohaScript.KonohaType;
import org.KonohaScript.JUtils.KonohaConst;

public class KonohaExceptionDef extends KonohaDef implements KonohaConst {
	@Override
	public void MakeDefinition(KonohaNameSpace ns) {
		KonohaType ExceptionClass = ns.LookupHostLangType(Exception.class);
		ns.DefineSymbol("Exception", ExceptionClass);

		String MN_New = "New";
		KonohaParam Exception_String_Param = KonohaParam.ParseOf(ns, "Process String x");
		ExceptionClass.DefineMethod(0, MN_New, Exception_String_Param, this, MN_New);
	}

	public static Exception New(Exception self, String ErrorMessage) {
		return new Exception(ErrorMessage);
	}
}