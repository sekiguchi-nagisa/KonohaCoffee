package org.KonohaScript.Peg.Exception;

import org.KonohaScript.KonohaType;
import org.KonohaScript.KLib.KonohaArray;
import org.KonohaScript.KLib.TokenList;
import org.KonohaScript.Parser.KonohaToken;
import org.KonohaScript.Parser.TypeEnv;
import org.KonohaScript.Parser.UntypedNode;
import org.KonohaScript.PegParser.PegParser;
import org.KonohaScript.PegParser.SyntaxAcceptor;
import org.KonohaScript.SyntaxTree.LetNode;
import org.KonohaScript.SyntaxTree.ThrowNode;
import org.KonohaScript.SyntaxTree.TryNode;
import org.KonohaScript.SyntaxTree.TypedNode;

// action: <Symbol:"try">, <Symbol:$#block>, <Repeat:<Symbol:$CatchBlocks>>, <Symbol:$Finally>
class ExceptionStatementSyntax0 extends SyntaxAcceptor {
	public final static int					TryBlock				= SyntaxAcceptor.ListOffset;
	public final static int					FinallyBlock			= TryBlock + 1;
	public final static int					CatchTypeNameIdx	= TryBlock + 2;
	public final static int					CatchBlockNameIdx	= TryBlock + 3;

	@Override
	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
		this.Report("ExceptionStatementSyntax0", NodeSize);
		UntypedNode UNode = this.CreateNodeWithSyntax(Parser, TokenList.get(BeginIdx), "$ExceptionStatement");
		int Index = 0;
		UNode.SetAtNode(TryBlock, (UntypedNode) Parser.Get(Index, NodeSize));
		Index = Index + 1;
		int TypeNameIdx = CatchTypeNameIdx;
		int BlockNameIdx = CatchBlockNameIdx;
		int i = 0;
		while(Index < NodeSize) {
			UntypedNode TypeExpr = (UntypedNode) Parser.Get(Index, NodeSize);
			Index = Index + 1;
			UntypedNode CatchBlock = (UntypedNode) Parser.Get(Index, NodeSize);
			Index = Index + 1;
			UNode.SetAtToken(TypeNameIdx + i, TypeExpr.KeyToken);
			UNode.SetAtNode(BlockNameIdx + i, CatchBlock);
			i = i + 2;
		}
		UNode.SetAtNode(FinallyBlock, (UntypedNode) Parser.Get(Index, NodeSize));
		Index = Index + 1;
		if(NodeSize > 0) {
			Parser.ReAssign(NodeSize, UNode);
		}
		return EndIdx;
	}

	@Override
	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		TypedNode TNode = TypeTry(Gamma, UNode, TypeInfo);
		return TNode;
	}

	public static TypedNode TypeTry(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		TypedNode TryBlockNode = UNode.TypeNodeAt(TryBlock, Gamma, Gamma.BooleanType, 0);
		if(TryBlockNode.IsError()) {
			return TryBlockNode;
		}

		boolean IsFinallyEmpty = UNode.GetAtNode(FinallyBlock).IsEmptyNode();
		TypedNode FinallyBlockNode = IsFinallyEmpty ? null : UNode.TypeNodeAt(FinallyBlock, Gamma, TypeInfo, 0);
		TryNode TNode = new TryNode(TryBlockNode.TypeInfo, TryBlockNode, FinallyBlockNode);

		int NumberOfCatchBlocks = (UNode.NodeList.size() - CatchTypeNameIdx) / 2;
		for(int i = 0; i < NumberOfCatchBlocks; i++) {
			KonohaType VarType = UNode.GetTokenType(CatchTypeNameIdx + 2 * i, null);
			KonohaToken VarToken = UNode.GetAtToken(CatchBlockNameIdx + 2 * i);
			TypedNode TargetException = new LetNode(VarType, VarToken, null, null);
			TypedNode CatchBlock = UNode.TypeNodeAt(CatchBlockNameIdx + 2 * i, Gamma, Gamma.VoidType, 0);
			TNode.addCatchBlock(TargetException, CatchBlock);
		}
		return TNode;
	}
}

// action: <Symbol:"try">, <Symbol:$#block>, <Symbol:$CatchBlocks>, <Repeat:<Symbol:$CatchBlocks>>
class ExceptionStatementSyntax1 extends SyntaxAcceptor {
	public final static int					TryBlock			= SyntaxAcceptor.ListOffset;
	public final static int					CatchTypeNameIdx	= TryBlock + 2;
	public final static int					CatchBlockNameIdx	= TryBlock + 3;

	@Override
	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
		this.Report("ExceptionStatementSyntax1", NodeSize);
		UntypedNode UNode = this.CreateNodeWithSyntax(Parser, TokenList.get(BeginIdx), "$ExceptionStatement");
		int Index = 0;
		UNode.SetAtNode(TryBlock, (UntypedNode) Parser.Get(Index, NodeSize));
		Index = Index + 1;
		int TypeNameIdx = CatchTypeNameIdx;
		int BlockNameIdx = CatchBlockNameIdx;
		int i = 0;
		while(Index < NodeSize) {
			KonohaArray CatchPair = (KonohaArray) Parser.Get(Index, NodeSize);
			Index = Index + 1;
			UntypedNode TypeExpr = (UntypedNode) CatchPair.get(0);
			UntypedNode CatchBlock = (UntypedNode) CatchPair.get(1);
			Index = Index + 1;
			UNode.SetAtToken(TypeNameIdx + i, TypeExpr.KeyToken);
			UNode.SetAtNode(BlockNameIdx + i, CatchBlock);
			i = i + 2;
		}
		if(NodeSize > 0) {
			Parser.ReAssign(NodeSize, UNode);
		}
		return EndIdx;
	}

	@Override
	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		return ExceptionStatementSyntax0.TypeTry(Gamma, UNode, TypeInfo);
	}
}

// action: <Symbol:"throw">, <Symbol:$#leftHandSideExpression>
class ExceptionStatementSyntax2 extends SyntaxAcceptor {
	public final static int	Expression	= SyntaxAcceptor.ListOffset;

	@Override
	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
		this.Report("ExceptionStatementSyntax2", NodeSize);
		UntypedNode UNode = this.CreateNodeWithSyntax(Parser, TokenList.get(BeginIdx), "$ExceptionStatement");
		int Index = 0;
		UNode.SetAtNode(Expression, (UntypedNode) Parser.Get(Index, NodeSize));
		Index = Index + 1;
		if(NodeSize > 0) {
			Parser.ReAssign(NodeSize, UNode);
		}
		return EndIdx;
	}

	@Override
	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		TypedNode Expr = UNode.TypeNodeAt(Expression, Gamma, Gamma.VarType/*FIXME*/, 0);
		if(Expr.IsError()) {
			return Expr;
		}
		TypedNode TNode = new ThrowNode(Expr.TypeInfo, Expr);
		return TNode;
	}
}

// action: <Symbol:"catch">, <Symbol:"(">, <Symbol:$#type>, <Symbol:$#Symbol>, <Symbol:")">, <Symbol:$#block>
class CatchBlocksSyntax0 extends SyntaxAcceptor {
	@Override
	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
		this.Report("CatchBlocksSyntax0", NodeSize);
		int Index = 0;
		KonohaArray CatchPair = new KonohaArray();
		CatchPair.set(0, Parser.Get(Index, NodeSize));
		Index = Index + 1;
		CatchPair.set(0, Parser.Get(Index, NodeSize));
		Index = Index + 1;
		if(NodeSize > 0) {
			Parser.ReAssign(NodeSize, CatchPair);
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
		UntypedNode FinallyBlock = (UntypedNode) Parser.Get(Index, NodeSize);
		Index = Index + 1;
		Parser.ReAssign(NodeSize, FinallyBlock);
		return EndIdx;
	}

	@Override
	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		/* do nothing */
		return null;
	}
}

// action: <Symbol:$ExceptionStatement>
class statementSyntax0 extends SyntaxAcceptor {
	static final statementSyntax0	Instance	= new statementSyntax0();

	@Override
	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
		this.Report("statementSyntax0", NodeSize);
		/* do nothing */
		return EndIdx;
	}

	@Override
	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		/* do nothing */
		return null;
	}
}
