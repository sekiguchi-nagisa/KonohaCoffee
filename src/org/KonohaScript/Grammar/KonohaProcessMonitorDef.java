package org.KonohaScript.Grammar;

import org.KonohaScript.KonohaDef;
import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaParam;
import org.KonohaScript.KonohaType;
import org.KonohaScript.JUtils.KonohaConst;


public class KonohaProcessMonitorDef extends KonohaDef implements KonohaConst {

	@Override
	public void MakeDefinition(KonohaNameSpace ns) {
		KonohaType ProcessMonitorType = ns.LookupHostLangType(KonohaProcessMonitor.class);
		ns.DefineSymbol("ProcessMonitor", ProcessMonitorType);
		
		// define Constructor
		String MN_constructor = "New";
		KonohaParam ProcessMonitor_Param = KonohaParam.ParseOf(ns, "ProcessMonitor");
		ProcessMonitorType.DefineMethod(0, MN_constructor, ProcessMonitor_Param, this, MN_constructor);

		// define SetProcess
		String MN_SetProcess = "SetProcess";
		KonohaParam void_Process_Param = KonohaParam.ParseOf(ns, "void Process x");
		ProcessMonitorType.DefineMethod(0, MN_SetProcess, void_Process_Param, this, MN_SetProcess);

		// define ThrowException
		String MN_ThrowException = "ThrowException";
		KonohaParam void_Param = KonohaParam.ParseOf(ns, "void");
		ProcessMonitorType.DefineMethod(0, MN_ThrowException, void_Param, this, MN_ThrowException);		
	}

	public static KonohaProcessMonitor New() {
		return new KonohaProcessMonitor();
	}

	public static void SetProcess(KonohaProcessMonitor Monitor, KonohaProcess targetProc) {
		Monitor.setProcess(targetProc);
	}

	public static void ThrowException(KonohaProcessMonitor Monitor) throws Exception {
		Monitor.throwException();
	}
}
