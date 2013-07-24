package org.KonohaScript.Tester;

import org.KonohaScript.CodeGen.ASTInterpreterTest;
import org.KonohaScript.CodeGen.JVM.JVMCodeGenTest;
import org.KonohaScript.CodeGen.LeafJSCodeGenTest;
import org.KonohaScript.CodeGen.ShellTest;
import org.KonohaScript.Grammar.KonohaProcessTest;
import org.KonohaScript.Peg.KonohaClass.KonohaClassGrammarTest;
import org.KonohaScript.Peg.MiniKonoha.MiniKonohaGrammerTest;
import org.KonohaScript.CodeGen.LLVMCodeGenTest;
public class KTestRunner extends KTestRunnerBase {
	public static void main(String[] args) {
		KTestRunner Runner = new KTestRunner();
		Runner.Run(new ASTInterpreterTest());
		Runner.Run(new JVMCodeGenTest());
		Runner.Run(new LeafJSCodeGenTest());
		Runner.Run(new ShellTest());
		Runner.Run(new KonohaProcessTest());
		Runner.Run(new KonohaClassGrammarTest());
		Runner.Run(new MiniKonohaGrammerTest());
		Runner.Run(new LLVMCodeGenTest());
	}
}
