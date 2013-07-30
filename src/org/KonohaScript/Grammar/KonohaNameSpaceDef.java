package org.KonohaScript.Grammar;

import java.io.File;

import org.KonohaScript.KonohaDef;
import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaParam;
import org.KonohaScript.KonohaType;
import org.KonohaScript.JUtils.KonohaConst;
import org.KonohaScript.JUtils.KonohaDebug;
import org.KonohaScript.Parser.KonohaGrammar;

public class KonohaNameSpaceDef extends KonohaDef implements KonohaConst {
	@Override
	public void MakeDefinition(KonohaNameSpace NameSpace) {
		KonohaType BaseClass = NameSpace.LookupHostLangType(KonohaNameSpace.class);
		NameSpace.DefineSymbol("NameSpace", BaseClass);
		KonohaParam param1 = KonohaParam.ParseOf(NameSpace, "void String x");
		BaseClass.DefineMethod(StaticMethod, "Load", param1, this, "Load");

		KonohaParam param2 = KonohaParam.ParseOf(NameSpace, "void String x");
		BaseClass.DefineMethod(StaticMethod, "ImportSyntax", param2, this, "ImportSyntax");

		KonohaParam param3 = KonohaParam.ParseOf(NameSpace, "void String x");
		BaseClass.DefineMethod(StaticMethod, "ImportLibrary", param3, this, "ImportLibrary");
	}

	public static void Load(KonohaNameSpace NameSpace, String FileName) {
		FileName = new File(FileName).getAbsolutePath();
		KonohaDebug.P("LoadScript:" + FileName);
		NameSpace.Eval(FileName, 0/*FIXME*/);
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

	public static void ImportLibrary(KonohaNameSpace NameSpace, String LibName) {
		String DefaultPath1 = "org.KonohaScript.KLib.";
		String DefaultPath2 = "org.KonohaScript.Grammar.";
		String ClassName = DefaultPath1 + LibName;
		Object Obj = LoadClass(ClassName);
		if (Obj == null) {
			ClassName = DefaultPath2 + LibName + "." + LibName + "Def";
			Obj = LoadClass(ClassName);
		}
		if (Obj == null) {
			throw new RuntimeException("Syntax Package " + LibName + "is not found.");
		}
		KonohaDef Def = (KonohaDef) Obj;
		Def.MakeDefinition(NameSpace);
		KonohaDebug.P("LoadLibrary:" + LibName);
	}

	public static void ImportSyntax(KonohaNameSpace NameSpace, String LibName) {
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
		Grammar.LoadDefaultSyntax(NameSpace);
		KonohaDebug.P("LoadSyntax:" + LibName);
	}
}
