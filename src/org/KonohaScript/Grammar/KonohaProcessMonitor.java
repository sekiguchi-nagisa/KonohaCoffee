package org.KonohaScript.Grammar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KonohaProcessMonitor {
	private ArrayList<KonohaProcess> procList;

	public KonohaProcessMonitor() {
		this.procList = new ArrayList<KonohaProcess>();
	}

	public void setProcess(KonohaProcess kproc) {
		this.procList.add(kproc);
	}

	public void throwException() throws Exception {
		int size = procList.size();
		for(int i = 0; i < size; i++) {
			KonohaProcess targetProc = procList.get(i);
			targetProc.waitFor();
			
			String message = targetProc.getCmdName();
			String logFilePath = targetProc.getLogFilePath();
			if(logFilePath != null) {
				if(targetProc.getRet() != 0) {
					Stack<String[]> syscallStack = parseTraceLog(logFilePath);
					deleteLogFile(logFilePath);
					throw createException(message, syscallStack.peek());
				}			
				deleteLogFile(logFilePath);				
			} else {
				if(targetProc.getRet() != 0) {
					throw new Exception(message);
				}
			}
			
		}
	}

	private void deleteLogFile(String logFilePath) {
		new File(logFilePath).delete();
	}

	private Stack<String[]> parseTraceLog(String logFilePath) {
		try {
			Stack<String[]> syscallStack = new Stack<String[]>();
			BufferedReader br = new BufferedReader(new FileReader(logFilePath));
			
			String regex1 = "^[1-9][0-9]* .+(.+) *= *-[1-9].+";
			Pattern p1 = Pattern.compile(regex1);
			
			String regex2 = "^.+(.+/locale.+).+";
			Pattern p2 = Pattern.compile(regex2);
			
			String regex3 = "(^[1-9][0-9]*)( *)([0-9][0-9]:[0-9][0-9]:[0-9][0-9])( *)(.+)";
			Pattern p3 = Pattern.compile(regex3);
			
			Matcher m1, m2, m3;
			
			String line;
			while((line = br.readLine()) != null){
				m1 = p1.matcher(line);
				m2 = p2.matcher(line);
				if(m1.find() && !m2.find()) {
					m3 = p3.matcher(line);
					syscallStack.push(parseLine(m3.replaceAll("$5")));
				}
			}
			br.close();
			
			return syscallStack;
		} 
		catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} 
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String[] parseLine(String syscallLine) {
		int p = 0;
		int openBracketCount = 0;
		int closeBracketCount = 0;
		String[] parsedSyscall = new String[3];
		String[] parsedSyscallTemp = new String[4];
		StringBuilder sBuilder= new StringBuilder();

		for(int i = 0; i < syscallLine.length(); i++) {
			char token = syscallLine.charAt(i);
			switch(token) {
			case '(':
				if(openBracketCount++ == 0) {
					parsedSyscallTemp[p++] = new String(sBuilder.toString());
					sBuilder = new StringBuilder();					
				}
				break;
			case ')':
				if(openBracketCount == ++closeBracketCount) {
					parsedSyscallTemp[p++] = new String(sBuilder.toString());
					sBuilder = new StringBuilder();
					openBracketCount = closeBracketCount = 0;
				}
				break;
			default:
				sBuilder.append(token);
				break;
			}
		}
		String[] splitStrings = parsedSyscallTemp[2].trim().split(" ");
		
		parsedSyscall[0] = parsedSyscallTemp[0];
		parsedSyscall[1] = parsedSyscallTemp[1];
		parsedSyscall[2] = splitStrings[2];
		
		// print 
//		System.out.print(syscallLine + ": ");
//		for(int i = 0; i < parsedSyscall.length; i++) {
//			System.out.print(parsedSyscall[i] + " ");
//		}
//		System.out.println();
		return parsedSyscall;
	}

	private Exception createException(String message, String[] syscall) throws Exception {
		try {
			return ErrNo.valueOf(syscall[2]).toException(message, syscall[0], syscall[1]);
		}
		catch (IllegalArgumentException e) {
			return new Exception((syscall[2] + " is not syscall!!"));
		}
	}
}

// shell Exception definition
class NotPermittedException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotPermittedException(String message) {
		super(message);
	}
}

class TooManyLinkException extends Exception {
	private static final long serialVersionUID = 1L;

	public TooManyLinkException(String message) {
		super(message);
	}
}

class TooLongNameException extends Exception {
	private static final long serialVersionUID = 1L;

	public TooLongNameException(String message) {
		super(message);
	}
}

class NotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotFoundException(String message) {
		super(message);
	}
}

class NetworkTimeoutException extends Exception {
	private static final long serialVersionUID = 1L;

	public NetworkTimeoutException(String message) {
		super(message);
	}
}

class InterruptedBySignalException extends Exception {
	private static final long serialVersionUID = 1L;

	public InterruptedBySignalException(String message) {
		super(message);
	}
}

class UnreachableException extends Exception {
	private static final long serialVersionUID = 1L;

	public UnreachableException(String message) {
		super(message);
	}
}

class ConnectRefusedException extends Exception {
	private static final long serialVersionUID = 1L;

	public ConnectRefusedException(String message) {
		super(message);
	}
}

class NoFreeSpaceException	extends Exception {
	private static final long serialVersionUID = 1L;

	public NoFreeSpaceException(String message) {
		super(message);
	}
}

class ReadOnlyException extends Exception {
	private static final long serialVersionUID = 1L;

	public ReadOnlyException(String message) {
		super(message);
	}
}


enum Syscall {
	open, openat, connect,
}

enum ErrNo {
	E2BIG, 
	EACCES {
		public Exception toException(String message, String syscallName, String param) {
			return new NotPermittedException(message);
		}
	}, 
	EADDRINUSE, 
	EADDRNOTAVAIL, 
	EAFNOSUPPORT,
	EAGAIN, 
	EALREADY, 
	EBADE, 
	EBADF, 
	EBADFD, 
	EBADMSG, 
	EBADR, 
	EBADRQC, 
	EBADSLT, 
	EBUSY, 
	ECANCELED, 
	ECHILD, 
	ECHRNG, 
	ECOMM, 
	ECONNABORTED,
	ECONNREFUSED {
		public Exception toException(String message, String syscallName, String param) {
			return new ConnectRefusedException(message);
		}
	}, 
	ECONNRESET, 
	EDEADLK, 
	EDEADLOCK, 
	EDESTADDRREQ, 
	EDOM,
	EDQUOT, 
	EEXIST, 
	EFAULT, 
	EFBIG, 
	EHOSTDOWN, 
	EHOSTUNREACH, 
	EIDRM, 
	EILSEQ,
	EINPROGRESS, 
	EINTR {
		public Exception toException(String message, String syscallName, String param) {
			return new InterruptedBySignalException(message);
		}
	}, 
	EINVAL, 
	EIO, 
	EISCONN, 
	EISDIR, 
	EISNAM, 
	EKEYEXPIRED,
	EKEYREJECTED, 
	EKEYREVOKED, 
	EL2HLT, 
	EL2NSYNC, 
	EL3HLT, 
	EL3RST, 
	ELIBACC, 
	ELIBBAD, 
	ELIBMAX, 
	ELIBSCN, 
	ELIBEXEC, 
	ELOOP {
		public Exception toException(String message, String syscallName, String param) {
			return new TooManyLinkException(message);
		}
	}, 
	EMEDIUMTYPE, 
	EMFILE, 
	EMLINK, 
	EMSGSIZE, 
	EMULTIHOP, 
	ENAMETOOLONG {
		public Exception toException(String message, String syscallName, String param) {
			return new TooLongNameException(message);
		}
	}, 
	ENETDOWN, 
	ENETRESET, 
	ENETUNREACH {
		public Exception toException(String message, String syscallName, String param) {
			return new UnreachableException(message);
		}
	}, 
	ENFILE,
	ENOBUFS, 
	ENODATA, 
	ENODEV, 
	ENOENT {
		public Exception toException(String message, String syscallName, String param) {
			return new NotFoundException(message);
		}
	}, 
	ENOEXEC, 
	ENOKEY, 
	ENOLCK, 
	ENOLINK, 
	ENOMEDIUM, 
	ENOMEM, 
	ENOMSG, 
	ENONET, 
	ENOPKG, 
	ENOPROTOOPT, 
	ENOSPC {
		public Exception toException(String message, String syscallName, String param) {
			return new NoFreeSpaceException(message);
		}
	}, 
	ENOSR, 
	ENOSTR,
	ENOSYS, 
	ENOTBLK, 
	ENOTCONN, 
	ENOTDIR, 
	ENOTEMPTY, 
	ENOTSOCK, 
	ENOTSUP, 
	ENOTTY, 
	ENOTUNIQ, 
	ENXIO, 
	EOPNOTSUPP, 
	EOVERFLOW, 
	EPERM, 
	EPFNOSUPPORT, 
	EPIPE, 
	EPROTO, 
	EPROTONOSUPPORT, 
	EPROTOTYPE, 
	ERANGE, 
	EREMCHG, 
	EREMOTE, 
	EREMOTEIO,
	ERESTART, 
	EROFS {
		public Exception toException(String message, String syscallName, String param) {
			return new ReadOnlyException(message);
		}
	}, 
	ESHUTDOWN, 
	ESPIPE, 
	ESOCKTNOSUPPORT, 
	ESRCH, 
	ESTALE, 
	ESTRPIPE, 
	ETIME, 
	ETIMEDOUT {
		public Exception toException(String message, String syscallName, String param) {
			return new NetworkTimeoutException(message);
		}
	}, 
	ETXTBSY, 
	EUCLEAN, 
	EUNATCH, 
	EUSERS, 
	EWOULDBLOCK, 
	EXDEV, 
	EXFULL;

	public Exception toException(String message, String syscallName, String param) {
		return new Exception(this.toString() + " is not yet implemented!!");
	}
}
