package org.KonohaScript.Grammar;

import java.util.ArrayList;

import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaType;
import org.KonohaScript.JUtils.KonohaConst;
import org.KonohaScript.JUtils.KonohaDebug;
import org.KonohaScript.KLib.TokenList;
import org.KonohaScript.Parser.KonohaGrammar;
import org.KonohaScript.Parser.KonohaSyntax;
import org.KonohaScript.Parser.KonohaToken;
import org.KonohaScript.Parser.TypeEnv;
import org.KonohaScript.Parser.UntypedNode;
import org.KonohaScript.SyntaxTree.TypedNode;


public final class ShellGrammar extends KonohaGrammar implements KonohaConst {

	MiniKonohaGrammar			MiniKonoha			= new MiniKonohaGrammar();

	public static final String	ProcessClassName	= "Process";
	public static final String  MonitorClassName    = "ProcessMonitor";

	/*
	 * $(ls -la | grep .txt) 	--> It is a Shell Expression. Return shell command's standard output as String Object.
	 * 
	 * Currently, Shell Expression is desugared at ShellToken. 
	 * In the future, it will be desugared at ParseShell.
	 * 
	 */
	public int ShellToken(KonohaNameSpace ns, String SourceText, int pos, TokenList ParsedTokenList) {
		int start = pos;
		int level = 0;
		pos++;
		if(SourceText.charAt(pos) != '(') {
			return -1;
		}
		for(; pos < SourceText.length(); pos++) {
			char ch = SourceText.charAt(pos);
			if(ch == '\\') {
				pos++;
				continue;
			}
			if(ch == '(') {
				level++;
			} else if(ch == ')') {
				level--;
				if(level == 0) {
					pos++;
					break;
				}
			}
		}
//		KonohaToken Token = new KonohaToken(SourceText.substring(start, pos));
//		Token.ResolvedSyntax = ns.GetSyntax("$Shell");
//		ParsedTokenList.add(Token);	
		
		TokenList BufferList = ShellGrammar.ParseShellCommandLine(ns, SourceText.substring(start + 2, pos - 1), 0, true);
		int size = BufferList.size();
		for(int i = 0; i < size; i++) {
			KonohaToken Token = BufferList.get(i);
			ParsedTokenList.add(Token);			
		}
		
		pos++;
		return pos;
	}
	
	/*
	 * ls -la | grep .txt	--> It is a Shell Statement.
	 * 
	 * Currently, Shell Statement is desugared at UnixCommandToken. 
	 * In the future, Shell Statement will be desugared at ParseShell.
	 * ParseShell will be called by $Symbol.
	 * 
	 */
	
	// future may be removed
	public int UnixCommandToken(KonohaNameSpace ns, String SourceText, int pos, TokenList ParsedTokenList) {	
		char ch = SourceText.charAt(pos);
		if(ch == '/') {	// forbidden character
			return -1;
		}
		
		if(ParsedTokenList.size() != 0) {	//FIXME
			int size = ParsedTokenList.size();
			KonohaToken preToken = ParsedTokenList.get(size - 1);
			System.out.println("show preToken " + preToken);
			if(!preToken.ParsedText.equals(";")) {
				if(preToken.ResolvedSyntax != null) {
					if(!preToken.ResolvedSyntax.equals(KonohaSyntax.IndentSyntax)) {
						return -1;
					} 			
				}

			} 
		}
		
		int sourceLength = SourceText.length();
		boolean isFistToken = true;
		boolean isUnixCommand = false;
		StringBuilder cmdBuffer = new StringBuilder();
		
		for(; pos < sourceLength; pos++) {
			ch = SourceText.charAt(pos);
			if(ch == ' ') {
				if(isFistToken) {
					isFistToken = false;
					if(searchUnixCommand(cmdBuffer.toString()) == false) {
						return -1;
					}
				}
				cmdBuffer.append(ch);
			} 
			else if(ch == '\n') {
				pos++;
				break;
			} 
			else if(ch == '\\') { //FIXME
				// do nothing
			}
			else {
				cmdBuffer.append(ch);
			}
		}
		
		if(!isUnixCommand) {
			return -1;
		}
		
		TokenList BufferList = ShellGrammar.ParseShellCommandLine(ns, cmdBuffer.toString(), 0, false);
		int size = BufferList.size();
		for(int i = 0; i < size; i++) {
			KonohaToken Token = BufferList.get(i);
			ParsedTokenList.add(Token);			
		}
		
		return pos;
	}
	
	public boolean searchUnixCommand(String cmd) {
		System.out.println(cmd + " at searchUnixCommand");
		return false;
	}
	

	/*
	 * Command: command name & arguments & redirect CommandLine: piped commands
	 */

	public static ArrayList<String> SplitIntoCommands(String CommandLine) {
		ArrayList<String> Commands = new ArrayList<String>();
		boolean inQuate = false;
		boolean inDoubleQuate = false;
		int start = 0;
		for(int pos = 0; pos < CommandLine.length(); pos++) {
			char ch = CommandLine.charAt(pos);
			if(ch == '\\') {
				pos++;
				continue;
			}
			if(inQuate || inDoubleQuate) {
				if(inDoubleQuate && ch == '"') {
					inDoubleQuate = false;
				} else if(inQuate && ch == '\'') {
					inQuate = false;
				}
			} else {
				if(ch == '|') {
					Commands.add(CommandLine.substring(start, pos - 1));
					start = pos + 1;
				} else if(ch == ' ' && start == pos) {
					start++;
				} else if(ch == '"') {
					inDoubleQuate = true;
				} else if(ch == '\'') {
					inQuate = true;
				}
			}
		}
		if(start < CommandLine.length() - 1) {
			Commands.add(CommandLine.substring(start));
		}
		return Commands;
	}

	public static ArrayList<String> SplitIntoCommandTokens(String Command) {
		ArrayList<String> Tokens = new ArrayList<String>();
		int start = 0;
		int pos = 0;
		boolean inQuate = false;
		boolean inDoubleQuate = false;
		StringBuilder TokenBuilder = new StringBuilder();
		while(Command.charAt(pos) == ' ') {
			pos++;
		}
		for(; pos < Command.length(); pos++) {
			char ch = Command.charAt(pos);
			if(ch == '\\') {
				pos++;
				TokenBuilder.append(Command.charAt(pos));
				continue;
			}
			if(inQuate || inDoubleQuate) {
				if(inDoubleQuate && ch == '"') {
					inDoubleQuate = false;
				} else if(inQuate && ch == '\'') {
					inQuate = false;
				} else if(inQuate && ch == '"') {
					TokenBuilder.append("\\\"");
				} else {
					TokenBuilder.append(ch);
				}
			} else {
				if(ch == ' ') {
					if(start == pos) {
						start++;
					} else {
						//Tokens.add(Command.substring(start, pos).replaceAll("\\\\(.)", "$1"));
						Tokens.add(TokenBuilder.toString());
						TokenBuilder.delete(0, TokenBuilder.length());
						start = pos + 1;
					}
				} else if(ch == '"') {
					inDoubleQuate = true;
				} else if(ch == '\'') {
					inQuate = true;
				} else {
					TokenBuilder.append(ch);
				}
			}
		}
		if(start < Command.length() - 1) {
			Tokens.add(TokenBuilder.toString());
			TokenBuilder.delete(0, TokenBuilder.length());
		}
		return Tokens;
	}

	public static ArrayList<String> makeArguments(ArrayList<String> Tokens) {
		ArrayList<String> Args = new ArrayList<String>();
		int n = Tokens.size();
		for(int i = 1; i < n; i++) {
			String tk = Tokens.get(i);
			if(tk.equals(">") || tk.equals("<")) {
				n = i;
				break;
			}
		}
		for(int i = 1; i < n; i++) {
			Args.add(Tokens.get(i));
		}
		return Args;
	}

	public static String FindOutputFileName(ArrayList<String> Tokens) {
		for(int i = 1; i < Tokens.size(); i++) {
			String tk = Tokens.get(i);
			if(tk.equals(">") && i + 1 < Tokens.size()) {
				return Tokens.get(i + 1);
			}
		}
		return null;
	}

	public static String FindInputFileName(ArrayList<String> Tokens) {
		for(int i = 1; i < Tokens.size(); i++) {
			String tk = Tokens.get(i);
			if(tk.equals("<") && i + 1 < Tokens.size()) {
				return Tokens.get(i + 1);
			}
		}
		return null;
	}

	static final boolean enableMonitor = true;
	static int shellMehtodCounter = 0;

	public static TokenList ParseShellCommandLine(KonohaNameSpace NameSpace, String CommandLine, long uline, boolean isExpression) {
		String msg = isExpression ? "Expression" : "Statement";
		System.out.println("Create Shell " + msg +" at ParseShellCommandLine");
		
		// split commandline by pipe
		ArrayList<String> Commands = ShellGrammar.SplitIntoCommands(CommandLine);

		StringBuilder SourceBuilder = new StringBuilder();

		int n = Commands.size();
		String monitorName = "monitor";
		if(enableMonitor) {
			SourceBuilder.append(MonitorClassName + " " + monitorName + " = new " + MonitorClassName + "();\n");
		}
		for(int i = 0; i < n; i++) {
			ArrayList<String> Tokens = ShellGrammar.SplitIntoCommandTokens(Commands.get(i));
			ArrayList<String> Args = ShellGrammar.makeArguments(Tokens);
			String procName = "p" + i;
			//SourceBuilder.append(ProcessClassName + " " + procName + " = new " + ProcessClassName + "(\"" + Tokens.get(0) + "\");\n");
			SourceBuilder.append(ProcessClassName + " " + procName + " = new " + ProcessClassName + "();\n");
			SourceBuilder.append(procName + ".SetArgument(\"" + Tokens.get(0) + "\");\n");
			if(enableMonitor) {
				SourceBuilder.append(monitorName + ".SetProcess(" + procName + ");\n");
			}
			for(String arg : Args) {
				SourceBuilder.append(procName + ".SetArgument(\"" + arg + "\");\n");
			}
			SourceBuilder.append(procName + ".Start();\n");
			if(i == 0) {
				String Input = ShellGrammar.FindInputFileName(Tokens);
				if(Input != null) {
					SourceBuilder.append(procName + ".SetInputFileName(\"" + Input + "\");\n");
				}
			}
			if(i > 0) {
				SourceBuilder.append("p" + (i - 1) + ".Pipe(p" + i + ");\n");
			}
			if(i == n - 1) {
				String Output = ShellGrammar.FindOutputFileName(Tokens);
				if(Output != null) {
					SourceBuilder.append(procName + ".SetOutputFileName(\"" + Output + "\");\n");
				}
				SourceBuilder.append(procName + ".WaitResult();\n");
				if(isExpression) {
					SourceBuilder.append("String out = " + procName + ".GetOut();\n");
				} else {
					SourceBuilder.append("System.p(" + procName + ".GetOut());\n");		
				}
			}
		}
		if(enableMonitor) {
			SourceBuilder.append(monitorName + ".ThrowException();\n");
		}
		if(isExpression) {	//FIXME
			String shellMethodName = "ShellMethod" + shellMehtodCounter;
			SourceBuilder.append("return out;\n");
			String body = SourceBuilder.toString();
			SourceBuilder = new StringBuilder();
			SourceBuilder.append("String " + shellMethodName + "(){\n\n" + body+ "\n}\n");
//			NameSpace.Eval(SourceBuilder.toString(), 0);	// Eval ShellMethod. future must enable
//			SourceBuilder = new StringBuilder();
			SourceBuilder.append(shellMethodName + "();\n");
			shellMehtodCounter++;
		}

		StringBuilder SourceBuilder2 = new StringBuilder();
		SourceBuilder2.append("System.p(\"hello shell!\"); System.p(\"hello shell!!\"); System.p(\"hello shell!!!\");");
		
//		System.out.println(SourceBuilder.toString());	// future must enable
//		return NameSpace.Tokenize(SourceBuilder.toString(), uline);
		
		// temporary enable
		System.out.println("<<---- actual syntax ---->>\n" + SourceBuilder.toString() + "----------------------");
		System.out.println(SourceBuilder2.toString());
		return NameSpace.Tokenize(SourceBuilder2.toString(), uline);
	}

	public int ParseShell(UntypedNode UNode, TokenList TokenList, int BeginIdx, int EndIdx, int ParseOption) {	// currently unused
		if(!TokenList.get(BeginIdx).ParsedText.startsWith("$(")) {
			return -1;
		}
		KonohaToken ShellToken = TokenList.get(BeginIdx);
		String CommandLine = ShellToken.ParsedText.substring(2, ShellToken.ParsedText.length() - 1);

		KonohaDebug.P("Shell: [" + CommandLine + "]");
		KonohaNameSpace ns = UNode.NodeNameSpace;
		TokenList BufferList = ShellGrammar.ParseShellCommandLine(ns, CommandLine, ShellToken.uline, false);
		int next = BufferList.size();
		ns.PreProcess(BufferList, 0, next, BufferList);
		UntypedNode ShellUNode = UntypedNode
				.ParseNewNode(ns, null, BufferList, 0, BufferList.size(), ParseOption);
		UNode.AddParsedNode(ShellUNode);
		System.out.println("untyped tree: " + ShellUNode);
		return BeginIdx + 1;
	}

	public TypedNode TypeShell(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {	// currently unused
		//KonohaDebug.P("** Syntax " + UNode.Syntax + " is undefined **");
		UntypedNode subNode = (UntypedNode)UNode.NodeList.get(0);
		UntypedNode nextNode = UNode.NextNode;
		UNode.NextNode = subNode.NextNode;
		UNode = subNode;
		while(subNode.NextNode != null){
			subNode = subNode.NextNode;
		}
		subNode.NextNode = nextNode;
		return TypeEnv.TypeCheck(Gamma, UNode, TypeInfo, 0);
	}

	@Override
	public void LoadDefaultSyntax(KonohaNameSpace NameSpace) {
		this.MiniKonoha.LoadDefaultSyntax(NameSpace);

		// load Process definition
		new KonohaProcessDef().MakeDefinition(NameSpace);
		new KonohaProcessMonitorDef().MakeDefinition(NameSpace);

		// load Shell syntax 
		NameSpace.AddTokenFunc("$", this, "ShellToken");
		NameSpace.AddTokenFunc("(){}[]<>,;+-*/%=&|!", this, "UnixCommandToken");	//SingleSymbol
		NameSpace.AddTokenFunc("Aa", this, "UnixCommandToken");						//Symbol
		NameSpace.AddTokenFunc("1", this, "UnixCommandToken");						//NumberLiteral
		
//		NameSpace.DefineSyntax("$Shell", Term, this, "Shell");
//		NameSpace.DefineSyntax("$Symbol", Term, this, "Shell"); // currently unused
	}
}
