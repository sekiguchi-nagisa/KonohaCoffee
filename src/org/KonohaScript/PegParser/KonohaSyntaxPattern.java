package org.KonohaScript.PegParser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.KonohaScript.KonohaFunc;
import org.KonohaScript.KonohaType;
import org.KonohaScript.KLib.TokenList;
import org.KonohaScript.Parser.TypeEnv;
import org.KonohaScript.Parser.UntypedNode;
import org.KonohaScript.SyntaxTree.TypedNode;

class KonohaSyntaxAcceptor extends SyntaxAcceptor {
	public KonohaFunc	ParseFunc;
	public KonohaFunc	TypeFunc;

	@Override
	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
		UntypedNode UNode = UntypedNode.NewNullNode(Parser.NameSpace, TokenList, BeginIdx);
		return KonohaSyntaxPattern.InvokeParseFunc(this.ParseFunc, "KonohaSyntax", UNode, TokenList, BeginIdx, EndIdx,
				Parser.ParseOption);
	}

	@Override
	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		if(UNode.Syntax.TypeMethod == null) {
			return Gamma.NewErrorNode(UNode.KeyToken, "internal error: " + "Syntax " + UNode.Syntax + " 's TypeMethod is null");
		}
		TypedNode Node = null;
		try {
			System.err.println("Syntax " + UNode.Syntax);
			System.err.println("Syntax.TypeMethod " + UNode.Syntax.TypeMethod.getName());
			Node = (TypedNode) UNode.Syntax.TypeMethod.invoke(UNode.Syntax.TypeObject, Gamma, UNode, TypeInfo);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			Node = Gamma.NewErrorNode(UNode.KeyToken, "internal error: " + e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			Node = Gamma.NewErrorNode(UNode.KeyToken, "internal error: " + e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			Node = Gamma.NewErrorNode(UNode.KeyToken, "internal error: " + e + "\n\t" + e.getCause().toString());
		}
		return Node;
	}
}

public class KonohaSyntaxPattern extends SyntaxPattern {
	KonohaFunc	ParseFunc;
	KonohaFunc	TypeFunc;

	public KonohaSyntaxPattern(String Name, KonohaFunc ParseFunc, KonohaFunc TypeFunc) {
		super(Name);
		this.ParseFunc = ParseFunc;
		this.TypeFunc = TypeFunc;
	}

	public KonohaSyntaxAcceptor	Acceptor0	= new KonohaSyntaxAcceptor();

	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
		this.Report("Accept $KonohaSyntax");
		this.Acceptor0.ParseFunc = this.ParseFunc;
		this.Acceptor0.TypeFunc = this.TypeFunc;
		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
		return Parser.Cursor;
	}

	public static int InvokeParseFunc(KonohaFunc Func, String SyntaxName, UntypedNode UNode,
			TokenList TokenList, int BeginIdx, int EndIdx, int ParseOption) {
		Object callee = Func.callee;
		Method Method = Func.method;
		try {
			System.err.println("invoking.." + Method);
			if(Method == null) {
				System.err.println("invoking.." + Method);				
			}
			Integer NextId = (Integer) Method.invoke(callee, UNode, TokenList, BeginIdx, EndIdx, ParseOption);
			int NextIdx = NextId.intValue();
			return NextIdx;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.err.println("undefined ParseMethod: " + SyntaxName);
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int Match(PegParser Parser, TokenList TokenList) {
		int BeginIdx = Parser.Cursor;
		int EndIdx = Parser.EndIdx;
		this.Report("Enter $newExpression");
		UntypedNode UNode = UntypedNode.NewNullNode(Parser.NameSpace, TokenList, BeginIdx);
		int NextIdx = KonohaSyntaxPattern.InvokeParseFunc(this.ParseFunc, "KonohaSyntax", UNode, TokenList, BeginIdx, EndIdx,
				Parser.ParseOption);
		if(NextIdx > BeginIdx) {
			Parser.Cursor = EndIdx;
			return this.action0("KonohaSyntax", Parser, BeginIdx, EndIdx - BeginIdx);
		}
		return -1;
	}
}