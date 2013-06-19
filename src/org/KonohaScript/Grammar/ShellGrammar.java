package org.KonohaScript.Grammar;

import java.util.ArrayList;

import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KonohaType;
import org.KonohaScript.JUtils.KonohaConst;
import org.KonohaScript.JUtils.KonohaDebug;
import org.KonohaScript.KLib.TokenList;
import org.KonohaScript.Parser.KonohaGrammar;
import org.KonohaScript.Parser.KonohaToken;
import org.KonohaScript.Parser.TypeEnv;
import org.KonohaScript.Parser.UntypedNode;
import org.KonohaScript.SyntaxTree.TypedNode;

public final class ShellGrammar extends KonohaGrammar implements KonohaConst {

	MiniKonohaGrammar MiniKonoha = new MiniKonohaGrammar();

	// $(ls -la | grep .txt)
	public int ShellToken(KonohaNameSpace ns, String SourceText, int pos, TokenList ParsedTokenList) {
		int start = pos;
		int level = 1;
		pos++;
		if(SourceText.charAt(pos) != '('){
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
			}else if(ch == ')') {
				level--;
				if(level < 0){
					pos++;
					break;
				}
			}
		}
		KonohaToken Token = new KonohaToken(SourceText.substring(start, pos));
		Token.ResolvedSyntax = ns.GetSyntax("$Shell");
		ParsedTokenList.add(Token);
		return pos;
	}

	/*
	 * Command: command name & arguments & redirect
	 * CommandLine: piped commands
	 */

	private ArrayList<String> SplitIntoCommands(String CommandLine) {
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
			if(inQuate || inDoubleQuate){
				if(inDoubleQuate && ch == '"'){
					inDoubleQuate = false;
				}else if(inQuate && ch == '\''){
					inQuate = false;
				}
			}else{
				if(ch == '|') {
					Commands.add(CommandLine.substring(start, pos - 1));
					start = pos + 1;
				}else if(ch == ' ' && start == pos) {
					start++;
				}else if(ch == '"'){
					inDoubleQuate = true;
				}else if(ch == '\''){
					inQuate = true;
				}
			}
		}
		if(start < CommandLine.length() - 1){
			Commands.add(CommandLine.substring(start));
		}
		return Commands;
	}

	private ArrayList<String> SplitIntoCommandTokens(String Command){
		ArrayList<String> Tokens = new ArrayList<String>();
		int start = 0;
		int pos = 0;
		boolean inQuate = false;
		boolean inDoubleQuate = false;
		StringBuilder TokenBuilder = new StringBuilder();
		while(Command.charAt(pos) == ' '){
			pos++;
		}
		for(; pos < Command.length(); pos++) {
			char ch = Command.charAt(pos);
			if(ch == '\\') {
				pos++;
				TokenBuilder.append(Command.charAt(pos));
				continue;
			}
			if(inQuate || inDoubleQuate){
				if(inDoubleQuate && ch == '"'){
					inDoubleQuate = false;
				}else if(inQuate && ch == '\''){
					inQuate = false;
				}else if(inQuate && ch == '"'){
					TokenBuilder.append("\\\"");
				}else{
					TokenBuilder.append(ch);
				}
			}else{
				if(ch == ' ') {
					if(start == pos) {
						start++;
					}else{
						//Tokens.add(Command.substring(start, pos).replaceAll("\\\\(.)", "$1"));
						Tokens.add(TokenBuilder.toString());
						TokenBuilder.delete(0, TokenBuilder.length());
						start = pos + 1;
					}
				}else if(ch == '"'){
					inDoubleQuate = true;
				}else if(ch == '\''){
					inQuate = true;
				}else{
					TokenBuilder.append(ch);
				}
			}
		}
		if(start < Command.length() - 1){
			Tokens.add(TokenBuilder.toString());
			TokenBuilder.delete(0, TokenBuilder.length());
		}
		return Tokens;
	}

	private ArrayList<String> makeArguments(ArrayList<String> Tokens){
		ArrayList<String> Args = new ArrayList<String>();
		int n = Tokens.size();
		for(int i = 1; i < n; i++){
			String tk = Tokens.get(i);
			if(tk.equals(">") || tk.equals("<")){
				n = i;
				break;
			}
		}
		for(int i = 1; i < n; i++){
			Args.add(Tokens.get(i));
		}
		return Args;
	}

	private String FindOutputFileName(ArrayList<String> Tokens){
		for(int i = 1; i < Tokens.size(); i++){
			String tk = Tokens.get(i);
			if(tk.equals(">") && i + 1 < Tokens.size()){
				return Tokens.get(i + 1);
			}
		}
		return null;
	}

	private String FindInputFileName(ArrayList<String> Tokens){
		for(int i = 1; i < Tokens.size(); i++){
			String tk = Tokens.get(i);
			if(tk.equals("<") && i + 1 < Tokens.size()){
				return Tokens.get(i + 1);
			}
		}
		return null;
	}

	private TokenList ParseShellCommandLine(KonohaNameSpace NameSpace, String CommandLine, long uline) {
		// split commandline by pipe
		ArrayList<String> Commands = SplitIntoCommands(CommandLine);

		StringBuilder SourceBuilder = new StringBuilder();

		int n = Commands.size();
		for(int i = 0; i < n; i++){
			ArrayList<String> Tokens = SplitIntoCommandTokens(Commands.get(i));
			ArrayList<String> Args =  makeArguments(Tokens);
			String procName = "p" + i;
			SourceBuilder.append("Process " + procName + " = new Process(\"" + Tokens.get(0) + "\");\n");
			for(String arg : Args){
				SourceBuilder.append(procName + ".AddArgument(\"" + arg + "\");\n");
			}
			if(i == 0){
				String Input = FindInputFileName(Tokens);
				if(Input != null){
					SourceBuilder.append(procName + "." + "SetInputFileName(\"" + Input + "\");\n");
				}
			}
			if(i > 0){
				SourceBuilder.append("p" + (i-1) + ".Pipe(p" + i + ");\n");
			}
			if(i == n - 1){
				String Output = FindOutputFileName(Tokens);
				if(Output != null){
					SourceBuilder.append(procName + "." + "SetOutputFileName(\"" + Output + "\");\n");
				}
			}
			SourceBuilder.append(procName + ".Fg();\n");
		}
		System.out.println(SourceBuilder.toString());
		return NameSpace.Tokenize(SourceBuilder.toString(), uline);
	}

	/*
	public int ShellMacro(LexicalConverter Lexer, TokenList SourceList, int BeginIdx, int EndIdx, TokenList BufferList) {
		KonohaToken ShellToken = SourceList.get(BeginIdx);
		String CommandLine = ShellToken.ParsedText.substring(2, ShellToken.ParsedText.length() - 1);

		KonohaNameSpace ns = ShellToken.ResolvedSyntax.PackageNameSpace;
		TokenList ParsedList = ParseShellCommandLine(ns, CommandLine, ShellToken.uline);
		int next = ParsedList.size();
		ns.PreProcess(ParsedList, 0, next, ParsedList);

		for(int i = 0; i < ParsedList.size(); i++){
			BufferList.add(ParsedList.get(i));
		}
		return BeginIdx + 1;
	}
	*/

	public int ParseShell(UntypedNode UNode, TokenList TokenList, int BeginIdx, int EndIdx, int ParseOption) {
		KonohaToken ShellToken = TokenList.get(BeginIdx);
		String CommandLine = ShellToken.ParsedText.substring(2, ShellToken.ParsedText.length() - 2);

		KonohaDebug.P("Shell: [" + CommandLine + "]");
		KonohaNameSpace ns = UNode.NodeNameSpace;
		TokenList BufferList = ParseShellCommandLine(ns, CommandLine, ShellToken.uline);
		int next = BufferList.size();
		ns.PreProcess(BufferList, 0, next, BufferList);
		UntypedNode ShellUNode = ns.Parser.ParseNewNode(ns, null, BufferList, next, BufferList.size(), KonohaConst.AllowEmpty);
		UNode.AddParsedNode(ShellUNode);
		System.out.println("untyped tree: " + ShellUNode);
		return BeginIdx + 1;
	}

	public TypedNode TypeShell(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		//KonohaDebug.P("** Syntax " + UNode.Syntax + " is undefined **");
		return null;
	}

	@Override
	public void LoadDefaultSyntax(KonohaNameSpace NameSpace) {
		MiniKonoha.LoadDefaultSyntax(NameSpace);

		NameSpace.AddTokenFunc("$", this, "ShellToken");
		NameSpace.DefineSyntax("$Shell", Term, this, "Shell");
		//NameSpace.DefineMacro("$", this, "ShellMacro");

		new KonohaProcessDef().MakeDefinition(NameSpace);
	}
}
