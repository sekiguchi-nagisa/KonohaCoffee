package org.KonohaScript.Grammar;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;


public class KonohaProcess {
	private Process					proc;

	private OutputStream			stdin	= null;
	private InputStream				stdout	= null;
	private InputStream				stderr	= null;

	private StringBuilder cmdNameBuilder;
	private ArrayList<String>	commandList;
	private boolean enableSyscallTrace = false;
	public boolean isKilled = false;
	private final String logdirPath = "/tmp/strace-log";
	public String logFilePath = null;

	private boolean stdoutIsRedireted = false;
	private boolean stderrIsRedireted = false;
	private boolean streamIsLocked = false;
	
	private ByteArrayOutputStream outBuf;
	private ByteArrayOutputStream errBuf;


	public static void main(String[] args) throws Exception {		
		KonohaProcess kProc = new KonohaProcess(args[0]);
		for(int i = 1; i < args.length; i++) {
			kProc.setArgument(args[i]);
		}
		kProc.start();
		kProc.writeToFile("/tmp/kProclog.txt");
		kProc.waitResult();

		System.out.print(kProc.getStdout());
		System.err.print(kProc.getStderr());
		
		KonohaProcessMonitor monitor = new KonohaProcessMonitor();
		monitor.setProcess(kProc);
		monitor.throwException();
	}

	public KonohaProcess() {
		this.cmdNameBuilder = new StringBuilder();
		this.commandList = new ArrayList<String>();
		
		initTrace();
	}

	public KonohaProcess(String command) {
		this.cmdNameBuilder = new StringBuilder();
		this.commandList = new ArrayList<String>();
		
		initTrace();
		setArgument(command);
	}

	public KonohaProcess(String command, boolean enableSyscallTrace) {
		this.cmdNameBuilder = new StringBuilder();
		this.commandList = new ArrayList<String>();
		this.enableSyscallTrace = enableSyscallTrace;
		
		initTrace();
		setArgument(command);
	}

	private void initTrace() {
		if(this.enableSyscallTrace) {
			String currentLogdirPath = createLogDirectory();
			String logNameHeader = createLogNameHeader();
			logFilePath = new String(currentLogdirPath + "/" + logNameHeader + ".log");
			
			String[] straceCmd = {"strace", "-t", "-f", "-F", "-o", logFilePath};
			for(int i = 0; i < straceCmd.length; i++) {
				this.commandList.add(straceCmd[i]);
			}
		}		
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
		new PipeStreamHandler(this.stdout, destProc.stdin, true).start();
	}

	public void readFromFile(String fileName) {
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName));
			new PipeStreamHandler(bis, this.stdin, true).start();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void writeToFile(String fileName) {
		try {
			this.stdoutIsRedireted = true;
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName));
			new PipeStreamHandler(this.stdout, bos, true).start();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void handleOutputStream(OutputStream out, OutputStream err, boolean closeStream) {
		if(streamIsLocked) {
			return;
		}
		streamIsLocked = true;
		
		PipeStreamHandler stdoutHandler = null;
		PipeStreamHandler stderrHandler = null;
		if(!stdoutIsRedireted) {
			stdoutHandler = new PipeStreamHandler(stdout, out, closeStream);	
			stdoutHandler.start();
		}
		
		if(!stderrIsRedireted) {
			stderrHandler = new PipeStreamHandler(stderr, err, closeStream);
			stderrHandler.start();
		}

		try {
			if(stdoutHandler != null) {
				stdoutHandler.join();
			}
			if(stderrHandler != null) {
				stderrHandler.join();
			}
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}		
	}

	public void waitResult() {
		outBuf = new ByteArrayOutputStream();
		errBuf = new ByteArrayOutputStream();
		
		handleOutputStream(outBuf, errBuf, true);
	}

	public String getStdout() {
		return this.stdoutIsRedireted ? "" : this.outBuf.toString();
	}

	public String getStderr() {
		return this.stderrIsRedireted ? "" : this.errBuf.toString();
	}
	
	public void console() {		
		handleOutputStream(System.out, System.err, false);
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

	public String getLogFilePath() {
		return this.logFilePath;
	}

	public String getCmdName() {
		return this.cmdNameBuilder.toString();
	}
}

// copied from http://blog.art-of-coding.eu/piping-between-processes/
class PipeStreamHandler extends Thread {
	private InputStream		input;
	private OutputStream	output;
	private boolean closeStream;

	public PipeStreamHandler(InputStream input, OutputStream output, boolean closeStream) {
		this.input = input;
		this.output = output;
		this.closeStream = closeStream;
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
			if(closeStream) {
				this.input.close();
				this.output.close();				
			}
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
