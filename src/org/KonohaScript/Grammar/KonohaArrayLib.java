package org.KonohaScript.Grammar;

import org.KonohaScript.JUtils.KonohaConst;
import org.KonohaScript.KLib.KonohaArray;
import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaParam;
import org.KonohaScript.KonohaType;
import org.KonohaScript.KonohaDef;

public class KonohaArrayLib extends KonohaDef implements KonohaConst {
    @Override
    public void MakeDefinition(KonohaNameSpace ns) {
        KonohaType BaseClass = ns.LookupHostLangType(KonohaArray.class);
        KonohaParam BinaryParam = KonohaParam.ParseOf(ns, "int int i");

        BaseClass.DefineMethod(ImmutableMethod | ConstMethod, "[]", BinaryParam, this, "ArrayGetter");

//        KonohaParam RelationParam = KonohaParam.ParseOf(ns, "boolean String x");
//        BaseClass.DefineMethod(ImmutableMethod | ConstMethod, "==", RelationParam, this, "StringEqString");
//        BaseClass.DefineMethod(ImmutableMethod | ConstMethod, "!=", RelationParam, this, "StringNeString");
    }

    public static int ArrayGetter(int[] a, int x) {
        return a[x];
    }

}
