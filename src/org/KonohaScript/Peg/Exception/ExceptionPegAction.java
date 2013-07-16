package org.KonohaScript.Peg.Exception;

import org.KonohaScript.KonohaType;
import org.KonohaScript.KLib.TokenList;
import org.KonohaScript.Parser.TypeEnv;
import org.KonohaScript.Parser.UntypedNode;
import org.KonohaScript.PegParser.PegParser;
import org.KonohaScript.PegParser.SyntaxAcceptor;
import org.KonohaScript.SyntaxTree.TypedNode;

// action: <Symbol:"try">, <Symbol:$#block>, <Repeat:<Symbol:$CatchBlocks>>, <Symbol:$Finally>
class ExceptionStatementSyntax0 extends SyntaxAcceptor {
	static final ExceptionStatementSyntax0	Instance	= new ExceptionStatementSyntax0();

	@Override
	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
		this.Report("ExceptionStatementSyntax0", NodeSize);
		int Index = 0;
		Object[] List = new Object[NodeSize];
		List[Index] = Parser.Get(Index, NodeSize);
		Index = Index + 1;
		while(Index < NodeSize) {
			List[Index] = Parser.Get(Index, NodeSize);
			Index = Index + 1;
		}
		List[Index] = Parser.Get(Index, NodeSize);
		Index = Index + 1;
		if(NodeSize > 0) {
			Parser.ReAssign(NodeSize, List[0]);
		}
		return EndIdx;
	}

	@Override
	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		return null;
	}
}

// action: <Symbol:"try">, <Symbol:$#block>, <Symbol:$CatchBlocks>, <Repeat:<Symbol:$CatchBlocks>>
class ExceptionStatementSyntax1 extends SyntaxAcceptor {
	static final ExceptionStatementSyntax1	Instance	= new ExceptionStatementSyntax1();

	@Override
	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
		this.Report("ExceptionStatementSyntax1", NodeSize);
		int Index = 0;
		Object[] List = new Object[NodeSize];
		List[Index] = Parser.Get(Index, NodeSize);
		Index = Index + 1;
		List[Index] = Parser.Get(Index, NodeSize);
		Index = Index + 1;
		while(Index < NodeSize) {
			List[Index] = Parser.Get(Index, NodeSize);
			Index = Index + 1;
		}
		if(NodeSize > 0) {
			Parser.ReAssign(NodeSize, List[0]);
		}
		return EndIdx;
	}

	@Override
	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		return null;
	}
}

// action: <Symbol:"throw">, <Symbol:$#leftHandSideExpression>
class ExceptionStatementSyntax2 extends SyntaxAcceptor {
	static final ExceptionStatementSyntax2	Instance	= new ExceptionStatementSyntax2();

	@Override
	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
		this.Report("ExceptionStatementSyntax2", NodeSize);
		int Index = 0;
		Object[] List = new Object[NodeSize];
		List[Index] = Parser.Get(Index, NodeSize);
		Index = Index + 1;
		if(NodeSize > 0) {
			Parser.ReAssign(NodeSize, List[0]);
		}
		return EndIdx;
	}

	@Override
	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		return null;
	}
}

// action: <Symbol:"catch">, <Symbol:"(">, <Symbol:$#type>, <Symbol:$#Symbol>, <Symbol:")">, <Symbol:$#block>
class CatchBlocksSyntax0 extends SyntaxAcceptor {
	static final CatchBlocksSyntax0	Instance	= new CatchBlocksSyntax0();

	@Override
	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
		this.Report("CatchBlocksSyntax0", NodeSize);
		int Index = 0;
		Object[] List = new Object[NodeSize];
		List[Index] = Parser.Get(Index, NodeSize);
		Index = Index + 1;
		List[Index] = Parser.Get(Index, NodeSize);
		Index = Index + 1;
		List[Index] = Parser.Get(Index, NodeSize);
		Index = Index + 1;
		if(NodeSize > 0) {
			Parser.ReAssign(NodeSize, List[0]);
		}
		return EndIdx;
	}

	@Override
	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		return null;
	}
}

// action: <Symbol:"finally">, <Symbol:$#block>
class FinallySyntax0 extends SyntaxAcceptor {
	static final FinallySyntax0	Instance	= new FinallySyntax0();

	@Override
	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
		this.Report("FinallySyntax0", NodeSize);
		int Index = 0;
		Object[] List = new Object[NodeSize];
		List[Index] = Parser.Get(Index, NodeSize);
		Index = Index + 1;
		if(NodeSize > 0) {
			Parser.ReAssign(NodeSize, List[0]);
		}
		return EndIdx;
	}

	@Override
	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		return null;
	}
}

// action: <Symbol:$ExceptionStatement>
class statementSyntax0 extends SyntaxAcceptor {
	static final statementSyntax0	Instance	= new statementSyntax0();

	@Override
	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
		this.Report("statementSyntax0", NodeSize);
		int Index = 0;
		Object[] List = new Object[NodeSize];
		List[Index] = Parser.Get(Index, NodeSize);
		Index = Index + 1;
		if(NodeSize > 0) {
			Parser.ReAssign(NodeSize, List[0]);
		}
		return EndIdx;
	}

	@Override
	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		return null;
	}
}
