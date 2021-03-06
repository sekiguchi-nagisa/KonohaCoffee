package org.KonohaScript.CommandTool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.KonohaScript.Konoha;
import org.KonohaScript.Grammar.MiniKonohaGrammar;
import org.KonohaScript.JUtils.HostLang;
import org.KonohaScript.KLib.KonohaArray;
import org.KonohaScript.Parser.KonohaGrammar;

@HostLang
class KConsole {
	public final InputStream	stdin	= System.in;
	public final PrintStream	stdout	= System.out;
	public final PrintStream	stderr	= System.err;

	public KConsole() {
	}

	// FIXME support println(int), println(float)...
	void print(String s) {
		this.stdout.print(s);
	}

	void println(String s) {
		this.stdout.println(s);
	}
}

public class KonohaShell {

	Konoha	ShellContext;
	String BuilderName = "org.KonohaScript.CodeGen.JVM.JVMCodeGenerator";
	String GrammarName = "org.KonohaScript.Grammar.MiniKonohaGrammar";
	boolean	IsInteractiveMode;
	Object	LastEvaled;

	public KonohaShell() {
		//this.ShellContext = new Konoha(new MiniKonohaGrammar(), DefaultBuilder);
		this.IsInteractiveMode = false;
		this.LastEvaled = null;
	}

	boolean ProcessSource(String Source) {
		//		if(!Source.endsWith("}") && !Source.endsWith(";")) {
		//			Source = Source + ";";
		//		}
		this.LastEvaled = this.ShellContext.Eval(Source, 0);
		return true;
	}

	boolean ProcessFile(String FileName) {
		File f = new File(FileName);
		byte[] b = new byte[(int) f.length()];
		FileInputStream fi;
		try {
			fi = new FileInputStream(f);
			fi.read(b);
			fi.close();
			String source = new String(b);
			return this.ProcessSource(source);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	void printHelp() {
		/* FIXME */
	}

	String[] ProcessOptions(String[] origArgs) {
		KonohaArray Args = new KonohaArray();
		KonohaArray ImportedArgs = new KonohaArray();

		this.IsInteractiveMode = true;
		for(int i = 0; i < origArgs.length; i++) {
			String arg = origArgs[i];
			if(arg.equals("-h")) {
				this.printHelp();
				return null;
			}
			if(arg.equals("-i")) {
				this.IsInteractiveMode = true;
			} else if(arg.startsWith("-arch=")) {
				this.BuilderName = arg.substring("-arch=".length());
				//this.ShellContext.DefaultNameSpace.LoadBuilder(BuilderName);
			} else if(arg.startsWith("-I")) {
				String importFileName = arg.substring("-I".length());
				ImportedArgs.add(new File(importFileName).getAbsolutePath());
			} else if(arg.startsWith("-syntax=")) {
				this.GrammarName = arg.substring("-syntax=".length());
			} else {
				this.IsInteractiveMode = false;
				Args.add(arg);
			}
		}

		int argsSize = Args.size();
		int imprtedArgsSize = ImportedArgs.size();

		String[] newArgs = new String[argsSize + imprtedArgsSize];
		for(int i = 0; i < imprtedArgsSize; i++) {
			newArgs[i] = (String) ImportedArgs.get(i);
		}

		for(int i = 0; i < argsSize; i++) {
			newArgs[i + imprtedArgsSize] = (String) Args.get(i);
		}
		return newArgs;
	}

	public void initContext() {
		try {
			Class<?> ClassInfo = Class.forName(this.GrammarName);
			this.ShellContext = new Konoha((KonohaGrammar) ClassInfo.newInstance(), BuilderName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] origArgs) {
		//String DefaultBuilder = "org.KonohaScript.CodeGen.ASTInterpreter";
//		String DefaultBuilder = "org.KonohaScript.CodeGen.JVM.JVMCodeGenerator";
		KonohaShell shell = new KonohaShell();
		String[] args = shell.ProcessOptions(origArgs);
		shell.initContext();
		if(args == null) {
			return;
		}

		for(int i = 0; i < args.length; i++) {
			shell.ProcessFile(args[i]);
		}
		if(shell.IsInteractiveMode) {
			shell.ProcessConsole();

		}
	}

	static int Count(String begin, String terminator, String source) {
		int level = 0;
		int length = source.length();
		int Begin = begin.charAt(0);
		int End = terminator.charAt(0);
		for(int i = 0; i < length; i++) {
			int ch = source.charAt(i);
			if(ch == Begin) {
				level = level + 1;
			}
			if(ch == End) {
				level = level - 1;
			}
		}
		return level;
	}

	private void ProcessConsole() {
		KConsole console = new KConsole();
		console.println("Konoha version 3.0");
		Scanner s = new Scanner(console.stdin);
		statement: while(true) {
			System.out.flush();
			System.err.flush();
			console.print(">>> ");
			String source = "";
			int level = 0;

			while(true) {
				String line = s.nextLine();
				if(line.trim().isEmpty()) {
					continue statement;
				}
				source = source + line + "\n";
				level = level + KonohaShell.Count("(", ")", line);
				level = level + KonohaShell.Count("{", "}", line);
				if(level == 0) {
					break;
				}
				console.println("b:" + source);
			}
			if(this.ProcessSource(source) == false) {
				break;
			}
			if(this.LastEvaled != null) {
				console.println(this.LastEvaled.toString());
			}
		}
	}
}
