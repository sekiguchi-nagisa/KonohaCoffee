package org.KonohaScript.Grammar;

import org.KonohaScript.KonohaDef;
import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaParam;
import org.KonohaScript.KonohaType;
import org.KonohaScript.JUtils.KonohaConst;

public class KonohaString extends KonohaDef implements KonohaConst {

	@Override
	public void MakeDefinition(KonohaNameSpace ns) {
		KonohaType BaseClass = ns.LookupHostLangType(String.class);
		KonohaParam BinaryParam = KonohaParam.ParseOf(ns, "String String x");

		BaseClass.DefineMethod(ImmutableMethod | ConstMethod, "+", BinaryParam, this, "StringAddString");
		BaseClass.DefineMethod(ImmutableMethod | ConstMethod, "+", BinaryParam, this, "StringAddString");

		KonohaParam RelationParam = KonohaParam.ParseOf(ns, "boolean String x");
		BaseClass.DefineMethod(ImmutableMethod | ConstMethod, "==", RelationParam, this, "StringEqString");
		BaseClass.DefineMethod(ImmutableMethod | ConstMethod, "!=", RelationParam, this, "StringNeString");

		KonohaParam indexOfParam = KonohaParam.ParseOf(ns, "int String x");
		BaseClass.DefineMethod(ImmutableMethod | ConstMethod, "indexOf", indexOfParam, this, "StringIndexOf");

		KonohaParam lengthParam = KonohaParam.ParseOf(ns, "int");
		BaseClass.DefineMethod(ImmutableMethod | ConstMethod, "length", lengthParam, this, "StringLength");
	}

	public static String StringAddString(String x, String y) {
		return x + y;
	}

	public static boolean StringEqString(String x, String y) {
		return x.equals(y);
	}

	public static boolean StringNeString(String x, String y) {
		return !x.equals(y);
	}

	public static int StringIndexOf(String self, String str) {
		return self.indexOf(str);
	}

	public static int StringLength(String self) {
		return self.length();
	}
}
