package org.KonohaScript.JUtils;

public final class KonohaDebug {
	static final public boolean	UseBuiltInTest	= true;
	static final public boolean	DebugPrint		= false;

	public static void P(String msg) {
		System.out.println("DEBUG: " + msg);
		//		Exception e = new Exception();
		//		e.printStackTrace();
	}

	public static void TODO(String msg) {
		System.out.println("TODO: " + msg);
	}

	public static void Indent(int Level, String Tab) {
		for(int i = 0; i < Level; i++) {
			System.out.print(Tab);
		}
	}
}
