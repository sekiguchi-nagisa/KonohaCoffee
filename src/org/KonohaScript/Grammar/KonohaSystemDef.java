package org.KonohaScript.Grammar;

import org.KonohaScript.KonohaDef;
import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaParam;
import org.KonohaScript.KonohaType;
import org.KonohaScript.JUtils.KonohaConst;
import org.KonohaScript.Parser.KonohaGrammar;

public class KonohaSystemDef extends KonohaDef implements KonohaConst {
	@Override
	public void MakeDefinition(KonohaNameSpace NameSpace) {
		KonohaType BaseClass = NameSpace.LookupHostLangType(KonohaSystemDef.class);
		NameSpace.DefineSymbol("System", BaseClass);

		KonohaParam param1 = KonohaParam.ParseOf(NameSpace, "void Object x");
		BaseClass.DefineMethod(StaticMethod, "p", param1, this, "p");

		KonohaParam param2 = KonohaParam.ParseOf(NameSpace, "void String x");
		BaseClass.DefineMethod(StaticMethod, "ImportSyntax", param2, this, "ImportSyntax");
	}

	public static void p(Object self, Object x) {
		System.out.println(x);
	}

	static private Object LoadClass(String ClassName) {
		try {
			Class<?> ClassInfo = Class.forName(ClassName);
			return ClassInfo.newInstance();
		}
		catch (ClassNotFoundException e) {
		}
		catch (InstantiationException e) {
		}
		catch (IllegalAccessException e) {
		}
		return null;
	}

	public static void ImportSyntax(Object self, String LibName) {
		String DefaultPath1 = "org.KonohaScript.Grammar.";
		String DefaultPath2 = "org.KonohaScript.Peg.";
		String ClassName = DefaultPath1 + LibName;
		Object Obj = LoadClass(ClassName);
		if (Obj == null) {
			ClassName = DefaultPath2 + LibName + "." + LibName + "PegGrammar";
			Obj = LoadClass(ClassName);
		}
		if (Obj == null) {
			throw new RuntimeException("Syntax Package " + LibName + "is not found.");
		}
		KonohaGrammar Grammar = (KonohaGrammar) Obj;
		p(null, Grammar.getClass().getName());
		//Grammar.LoadDefaultSyntax(null);
	}
}
