package org.KonohaScript.Grammar;

import org.KonohaScript.JUtils.KonohaConst;
import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaParam;
import org.KonohaScript.KonohaType;
import org.KonohaScript.KonohaDef;

public class KonohaArrayLib extends KonohaDef implements KonohaConst {
    @Override
    public void MakeDefinition(KonohaNameSpace ns) {
        //FIXME int[] only
        KonohaType BaseClass = ns.LookupHostLangType(int[].class);
        KonohaParam GetterParam = KonohaParam.ParseOf(ns, "int int i");
        BaseClass.DefineMethod(ImmutableMethod, "get", GetterParam, this, "ArrayGetter");
        KonohaParam SetterParam = KonohaParam.ParseOf(ns, "void int i int v");
        BaseClass.DefineMethod(0, "set", SetterParam, this, "ArraySetter");
        KonohaParam GetSizeParam = KonohaParam.ParseOf(ns, "int");
        BaseClass.DefineMethod(ImmutableMethod | ConstMethod, "getSize", GetSizeParam, this, "ArrayGetSize");
    }

    public static int ArrayGetter(int[] a, int i) {
        return a[i];
    }

    public static void ArraySetter(int[] a, int i, int v) {
        a[i] = v;
    }

    public static int ArrayGetSize(int[] a) {
        return a.length;
    }
}
