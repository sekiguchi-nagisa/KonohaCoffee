package org.KonohaScript.Grammar;

import org.KonohaScript.KonohaDef;
import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaParam;
import org.KonohaScript.KonohaType;
import org.KonohaScript.JUtils.KonohaConst;


public class KonohaProcessDef extends KonohaDef implements KonohaConst {

	@Override
	public void MakeDefinition(KonohaNameSpace ns) {
		KonohaType baseClass_Process = ns.LookupHostLangType(KonohaProcess.class);

		// define Constructor
		String MN_constructor = "New";
		KonohaParam Process_String_Param = KonohaParam.ParseOf(ns, "Process String x");
		baseClass_Process.DefineMethod(0, MN_constructor, Process_String_Param, this, MN_constructor); 
		
		KonohaParam Process_String_boolean_Param = KonohaParam.ParseOf(ns, "Process String x boolean y");
		baseClass_Process.DefineMethod(0, MN_constructor, Process_String_boolean_Param, this, MN_constructor);

		// define SetArgument()
		String MN_SetArgument = "SetArgument";
		KonohaParam void_Strings_Param = KonohaParam.ParseOf(ns, "void String[] x");
		baseClass_Process.DefineMethod(0, MN_SetArgument, void_Strings_Param, this, MN_SetArgument);

		KonohaParam void_String_Param = KonohaParam.ParseOf(ns, "void String x");
		baseClass_Process.DefineMethod(0, MN_SetArgument, void_String_Param, this, MN_SetArgument);

		// define Start()
		String MN_Start = "Start";
		KonohaParam void_Param = KonohaParam.ParseOf(ns, "void");
		baseClass_Process.DefineMethod(0, MN_Start, void_Param, this, MN_Start);

		// define Pipe()
		String MN_Pipe = "Pipe";
		KonohaParam void_Process_Param = KonohaParam.ParseOf(ns, "void KonohaProcess x");
		baseClass_Process.DefineMethod(0, MN_Pipe, void_Process_Param, this, MN_Pipe);

		// define ReadFromFile()
		String MN_ReadFromFile = "ReadFromFile";
		baseClass_Process.DefineMethod(0, MN_ReadFromFile, void_String_Param, this, MN_ReadFromFile);

		// define GetOut()
		String MN_GetOut = "GetOut";
		KonohaParam String_Param = KonohaParam.ParseOf(ns, "String");
		baseClass_Process.DefineMethod(0, MN_GetOut, String_Param, this, MN_GetOut);

		// define GetError()
		String MN_GetError = "GetError";
		baseClass_Process.DefineMethod(0, MN_GetError, String_Param, this, MN_GetError);

		// define WaitFor()
		String MN_WaitFor = "WaitFor";
		KonohaParam void_int_Param = KonohaParam.ParseOf(ns, "void int x");
		baseClass_Process.DefineMethod(0, MN_WaitFor, void_int_Param, this, MN_WaitFor);

		// define GetRetValue()
		String MN_GetRetValue = "GetRetValue";
		KonohaParam int_Param = KonohaParam.ParseOf(ns, "int");
		baseClass_Process.DefineMethod(0, MN_GetRetValue, int_Param, this, MN_GetRetValue);
		
		
		KonohaType baseClass_ProcessMonitor = ns.LookupHostLangType(KonohaProcessMonitor.class);
		
		// define Constructor
		KonohaParam ProcessMonitor_Param = KonohaParam.ParseOf(ns, "ProcessMonitor");
		baseClass_ProcessMonitor.DefineMethod(0, MN_constructor, ProcessMonitor_Param, this, MN_constructor);
	
		// define SetProcess
		String MN_SetProcess = "SetProcess";
		baseClass_ProcessMonitor.DefineMethod(0, MN_SetProcess, void_Process_Param, this, MN_SetProcess);
		
		// define ThrowException
		String MN_ThrowException = "ThrowException";
		baseClass_ProcessMonitor.DefineMethod(0, MN_ThrowException, void_Param, this, MN_ThrowException);
	}

	// KonohaProcess Binding
	public static KonohaProcess New(String Command) {
		return new KonohaProcess(Command);
	}
	
	public static KonohaProcess New(String Command, boolean enableSyscallTrace) {
		return new KonohaProcess(Command, enableSyscallTrace);
	}

	public static void SetArgument(KonohaProcess Process, String[] Args) {
		Process.setArgument(Args);
	}

	public static void SetArgument(KonohaProcess Process, String Arg) {
		Process.setArgument(Arg);
	}

	public static void Start(KonohaProcess Process) {
		Process.start();
	}

	public static void Pipe(KonohaProcess Process, KonohaProcess dest) {
		Process.pipe(dest);
	}

	public static void ReadFromFile(KonohaProcess Process, String fileName) {
		Process.readFromFile(fileName);
	}

	public static int GetStatus() {
		//TODO(sekiguchi)
		return 0;
	}

	public static String GetOut(KonohaProcess Process) {
		return Process.getStdout();
	}

	public static String GetError(KonohaProcess Process) {
		return Process.getStderr();
	}

	public static void WaitFor(KonohaProcess Process, int time) {
		Process.waitFor(time);
	}

	public static int GetRetValue(KonohaProcess Process) {
		return Process.getRet();
	}

	// KonohaProcessMonitor Binding
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
