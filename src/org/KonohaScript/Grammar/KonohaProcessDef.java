package org.KonohaScript.Grammar;

import org.KonohaScript.KonohaDef;
import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaParam;
import org.KonohaScript.KonohaType;
import org.KonohaScript.JUtils.KonohaConst;

public class KonohaProcessDef extends KonohaDef implements KonohaConst {

	@Override
	public void MakeDefinition(KonohaNameSpace ns) {
		KonohaType ProcessType = ns.LookupHostLangType(KonohaProcess.class);
		ns.DefineSymbol("Process", ProcessType);

		// define Constructor
		String MN_constructor = "New";
		
		KonohaParam Process_Param = KonohaParam.ParseOf(ns, "Process");
		ProcessType.DefineMethod(0, MN_constructor, Process_Param, this, MN_constructor);		
		
		KonohaParam Process_String_Param = KonohaParam.ParseOf(ns, "Process String x");
		ProcessType.DefineMethod(0, MN_constructor, Process_String_Param, this, MN_constructor);

		KonohaParam Process_String_boolean_Param = KonohaParam.ParseOf(ns, "Process String x boolean y");
		ProcessType.DefineMethod(0, MN_constructor, Process_String_boolean_Param, this, MN_constructor);

		// define SetArgument()
		String MN_SetArgument = "SetArgument";
		KonohaParam void_Strings_Param = KonohaParam.ParseOf(ns, "void String[] x");
		ProcessType.DefineMethod(0, MN_SetArgument, void_Strings_Param, this, MN_SetArgument);

		KonohaParam void_String_Param = KonohaParam.ParseOf(ns, "void String x");
		ProcessType.DefineMethod(0, MN_SetArgument, void_String_Param, this, MN_SetArgument);

		// define Start()
		String MN_Start = "Start";
		KonohaParam void_Param = KonohaParam.ParseOf(ns, "void");
		ProcessType.DefineMethod(0, MN_Start, void_Param, this, MN_Start);

		// define Pipe()
		String MN_Pipe = "Pipe";
		KonohaParam void_Process_Param = KonohaParam.ParseOf(ns, "void KonohaProcess x");
		ProcessType.DefineMethod(0, MN_Pipe, void_Process_Param, this, MN_Pipe);

		// define SetInputFileName()
		String MN_SetInputFileName = "SetInputFileName";
		ProcessType.DefineMethod(0, MN_SetInputFileName, void_String_Param, this, MN_SetInputFileName);
		
		// define SetOutputFileName()
		String MN_SetOutputFileName = "SetOutputFileName";
		ProcessType.DefineMethod(0, MN_SetOutputFileName, void_String_Param, this, MN_SetOutputFileName);

		// define GetOut()
		String MN_GetOut = "GetOut";
		KonohaParam String_Param = KonohaParam.ParseOf(ns, "String");
		ProcessType.DefineMethod(0, MN_GetOut, String_Param, this, MN_GetOut);

		// define GetError()
		String MN_GetError = "GetError";
		ProcessType.DefineMethod(0, MN_GetError, String_Param, this, MN_GetError);

		// define WaitFor()
		String MN_WaitFor = "WaitFor";
		KonohaParam void_int_Param = KonohaParam.ParseOf(ns, "void int x");
		ProcessType.DefineMethod(0, MN_WaitFor, void_int_Param, this, MN_WaitFor);

		// define GetRetValue()
		String MN_GetRetValue = "GetRetValue";
		KonohaParam int_Param = KonohaParam.ParseOf(ns, "int");
		ProcessType.DefineMethod(0, MN_GetRetValue, int_Param, this, MN_GetRetValue);
		
		// define WaitResult()
		String MN_WaitResult = "WaitResult";
		ProcessType.DefineMethod(0, MN_WaitResult, void_Param, this, MN_WaitResult);
	}

	public static KonohaProcess New() {
		return new KonohaProcess();
	}

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

	public static void SetInputFileName(KonohaProcess Process, String fileName) {
		Process.readFromFile(fileName);
	}

	public static void SetOutputFileName(KonohaProcess Process, String fileName) {
		Process.writeToFile(fileName);
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

	public static void WaitResult(KonohaProcess Process) {
		Process.waitResult();
	}
}
