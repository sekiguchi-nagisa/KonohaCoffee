package org.KonohaScript.Grammar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class KonohaProcess {
	private Process					proc;

	private OutputStream			stdin	= null;
	private InputStream				stdout	= null;
	private InputStream				stderr	= null;

	private StringBuilder cmdNameBuilder;
	private ArrayList<String>	commandList;
	private boolean enableSyscallTrace = false;
	public boolean isKilled = false;
	private String errorMessage;
	private final String logdirPath = "/tmp/strace-log";
	public String logFilePath;
	private final String log4jConfig = "log4j.properties";


	public static void main(String[] args) {
		KonohaProcess kProc = new KonohaProcess("ls", false);
		kProc.setArgument("/root");
		kProc.start();
		//kProc.kill();
		//System.out.println("return: " + kProc.getRet());
		System.out.print(kProc.getStdout());
		System.err.print(kProc.getStderr());
	}
	
	public KonohaProcess(String command) {
		this.cmdNameBuilder = new StringBuilder();
		this.commandList = new ArrayList<String>();
		setArgument(command);
	}

	public KonohaProcess(String command, boolean enableSyscallTrace) {
		this.cmdNameBuilder = new StringBuilder();
		this.commandList = new ArrayList<String>();
		this.enableSyscallTrace = enableSyscallTrace;
		
		if(this.enableSyscallTrace) {
			String currentLogdirPath = createLogDirectory();
			String logNameHeader = createLogNameHeader();
			logFilePath = new String(currentLogdirPath + "/" + logNameHeader + ".log");
			
			String[] straceCmd = {"strace", "-t", "-f", "-F", "-o", logFilePath};
			for(int i = 0; i < straceCmd.length; i++) {
				this.commandList.add(straceCmd[i]);
			}
		}
		setArgument(command);
	}

	private String createLogDirectory() {
		Calendar cal = Calendar.getInstance();
		StringBuilder pathBuilder = new StringBuilder();
		
		pathBuilder.append(logdirPath + "/");
		pathBuilder.append(cal.get(Calendar.YEAR) + "-");
		pathBuilder.append((cal.get(Calendar.MONTH) + 1) + "-");
		pathBuilder.append(cal.get(Calendar.DATE));
		
		String subdirPath = pathBuilder.toString();
		File subdir = new File(subdirPath);
		subdir.mkdirs();
		
		return subdirPath;
	}

	private String createLogNameHeader() {
		Calendar cal = Calendar.getInstance();
		StringBuilder logNameHeader = new StringBuilder();
		
		logNameHeader.append(cal.get((Calendar.HOUR) + 1) + ":");
		logNameHeader.append(cal.get(Calendar.MINUTE) + "-");
		logNameHeader.append(cal.get(Calendar.MILLISECOND));

		return logNameHeader.toString();
	}

	public void setArgument(String Arg) {
		this.cmdNameBuilder.append(Arg + " ");
		this.commandList.add(Arg);
	}

	public void setArgument(String[] Args) {
		for(int i = 0; i < Args.length; i++) {
			this.setArgument(Args[i]);
		}
	}

	public void start() {		
		int size = this.commandList.size();
		String[] cmd = new String[size];
		for(int i = 0; i < size; i++) {
			cmd[i] = this.commandList.get(i);
		}

		try {
			this.proc = new ProcessBuilder(cmd).start();
			this.stdin = this.proc.getOutputStream();
			this.stdout = this.proc.getInputStream();
			this.stderr = this.proc.getErrorStream();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void pipe(KonohaProcess destProc) {
		new StreamSetter(this.stdout, destProc.stdin).start();
	}

	public void readFromFile(String fileName) {
		try {
			FileInputStream fis = new FileInputStream(fileName);
			StreamSetter stdinSetter = new StreamSetter(fis, this.stdin);
			stdinSetter.start();
			stdinSetter.join();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String getResult(InputStream ins) {
		StreamGetter streamGetter = new StreamGetter(ins);
		streamGetter.start();
		try {
			streamGetter.join();
			return streamGetter.getResult();
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public String getStdout() {
		return this.getResult(this.stdout);
	}

	public String getStderr() {
		return this.getResult(this.stderr);
	}

	public void waitFor() {
		try {
			this.proc.waitFor();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitFor(long timeout) {
		try {
			this.proc.exitValue();
		}
		catch (IllegalThreadStateException e) {
			try {
				Thread.sleep(timeout);
			}
			catch (InterruptedException e1) {
				e1.printStackTrace();
			} 
			finally {
				this.kill();
			}
		}
	}

	public int getRet() {
		return this.proc.exitValue();
	}
	
	public void kill() {
		try {
			// get target pid
			Field pidField = this.proc.getClass().getDeclaredField("pid");
			pidField.setAccessible(true);
			int pid = pidField.getInt(this.proc);
			
			// kill process
			String[] cmds = {"kill", "-9", Integer.toString(pid)};
			Process procKiller = new ProcessBuilder(cmds).start();
			procKiller.waitFor();
			this.isKilled = true;
		} 
		catch (NoSuchFieldException e) {
			e.printStackTrace();
		} 
		catch (SecurityException e) {
			e.printStackTrace();
		} 
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteLogFile() {
		new File(logFilePath).delete();
	}

	public void parseTraceLog() {
		System.out.println("show systemcall error!!");
		System.out.println("Error happened at \"" + cmdNameBuilder.toString() + "\"");
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.logFilePath));
			
			String regex1 = "^[1-9][0-9]* .+(.+) = -[1-9].+";
			Pattern p1 = Pattern.compile(regex1);
			
			String regex2 = "^.+(.+/locale.+).+";
			Pattern p2 = Pattern.compile(regex2);
			
			String regex3 = "(^[1-9][0-9]*)( *)([0-9][0-9]:[0-9][0-9]:[0-9][0-9])( *)(.+)";
			Pattern p3 = Pattern.compile(regex3);
			
			Matcher m1, m2, m3;
			
			String line;
			while((line = br.readLine()) != null){
				m1 = p1.matcher(line);
				if(m1.find()) {
					m3 = p3.matcher(line);
					System.out.println(m3.replaceAll("[pid $1] [time $3] $5"));
				}
			}
			br.close();
			deleteLogFile();
		} 
		catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} 
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void setError(String message) {
		this.errorMessage = message;
	}
	
	public String getError() {
		return this.errorMessage;
	}
}

class StreamGetter extends Thread {
	private BufferedReader	br;
	private StringBuilder	sBuilder;

	public StreamGetter(InputStream is) {
		this.br = new BufferedReader(new InputStreamReader(is));
		this.sBuilder = new StringBuilder();
	}

	@Override
	public void run() {
		String line = null;
		try {
			while((line = this.br.readLine()) != null) {
				this.sBuilder.append(line + "\n");
			}
			this.br.close();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String getResult() {
		return this.sBuilder.toString();
	}
}

// copied from http://blog.art-of-coding.eu/piping-between-processes/
class StreamSetter extends Thread {
	private InputStream	input;
	private OutputStream	output;

	public StreamSetter(InputStream input, OutputStream output) {
		this.input = input;
		this.output = output;
	}

	@Override
	public void run() {
		try {
			byte[] buffer = new byte[512];
			int read = 0;
			while(read > -1) {
				read = this.input.read(buffer, 0, buffer.length);
				if(read > -1) {
					this.output.write(buffer, 0, read);
				}
			}
			this.input.close();
			this.output.close();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

class KonohaProcessMonitor extends Thread {
	private ArrayList<KonohaProcess> procList;
	
	public KonohaProcessMonitor() {
		this.procList = new ArrayList<KonohaProcess>();
	}
	
	public void setProcess(KonohaProcess kproc) {
		this.procList.add(kproc);
	}
	
	private void diagnose(KonohaProcess kproc) {	//FIXME
		try {
			BufferedReader br = new BufferedReader(new FileReader(kproc.logFilePath));
			
			String regex1 = "^[1-9][0-9]* .+(.+) = -[1-9].+";
			Pattern p1 = Pattern.compile(regex1);
			
			Matcher m1;
			String line;
			while((line = br.readLine()) != null){
				m1 = p1.matcher(line);
				if(m1.find()) {
					// check systemcall error
					kproc.setError("error");
					kproc.kill();
				}
			}
			br.close();	
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void throwException() throws Exception {
		int size = procList.size();
		for(int i = 0; i < size; i++) {
			KonohaProcess targetProc = procList.get(i);
			targetProc.waitFor();
			if(targetProc.isKilled) {
				throw new Exception(targetProc.getError());
			} else {
				if(targetProc.getRet() != 0) {
					throw new Exception(targetProc.getError());
				}
			}
		}
	}
	
	@Override
	public void run() {
		int size = procList.size();
		for(int i = 0; i < size; i++) {
			KonohaProcess targetProc = procList.get(i);
			try {
				targetProc.getRet();
			}
			catch (IllegalThreadStateException e) {
				diagnose(targetProc);
			}
		}
	}
}
