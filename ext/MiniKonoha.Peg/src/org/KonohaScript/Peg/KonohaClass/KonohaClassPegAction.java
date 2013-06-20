package org.KonohaScript.Peg.KonohaClass;

import org.KonohaScript.KonohaType;
import org.KonohaScript.KLib.TokenList;
import org.KonohaScript.Parser.TypeEnv;
import org.KonohaScript.Parser.UntypedNode;
import org.KonohaScript.PegParser.SyntaxAcceptor;
import org.KonohaScript.PegParser.SyntaxModule;
import org.KonohaScript.SyntaxTree.TypedNode;

// action: <Symbol:"class">, <Symbol:$#Symbol>, <Symbol:$#block>
class ClassDefinitionSyntax0 extends SyntaxAcceptor {
	static final int	ClassNameOffset			= ListOffset;
	static final int	ClassParentNameOffset	= ClassNameOffset + 1;
	static final int	ClassBlockOffset		= ClassNameOffset + 2;

	@Override
	public int Parse(SyntaxModule Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
		Report("ClassDefinitionSyntax0", NodeSize);
		UntypedNode UNode = this.CreateNodeWithSyntax(Parser, TokenList.get(BeginIdx), "$ClassDefinitionSyntax");
		int Index = 0;
		UntypedNode ClassName = (UntypedNode) Parser.Get(Index, NodeSize);
		Index = Index + 1;
		UntypedNode BlockNode = (UntypedNode) Parser.Get(Index, NodeSize);
		Index = Index + 1;
		UNode.SetAtToken(ClassNameOffset, ClassName.KeyToken);
		UNode.SetAtNode(ClassParentNameOffset, null);
		UNode.SetAtNode(ClassBlockOffset, BlockNode);
		Parser.ReAssign(NodeSize, UNode);
		return EndIdx;
	}

	@Override
	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		return null;
	}
}

// action: <Symbol:"class">, <Symbol:$#Symbol>, <Symbol:"extends">, <Symbol:$#type>
class ClassDefinitionSyntax1 extends SyntaxAcceptor {
	static final int	ClassNameOffset			= ListOffset;
	static final int	ClassParentNameOffset	= ClassNameOffset + 1;
	static final int	ClassBlockOffset		= ClassNameOffset + 2;

	@Override
	public int Parse(SyntaxModule Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
		Report("ClassDefinitionSyntax1", NodeSize);
		UntypedNode UNode = this.CreateNodeWithSyntax(Parser, TokenList.get(BeginIdx), "$ClassDefinitionSyntax");
		int Index = 0;
		UntypedNode ClassName = (UntypedNode) Parser.Get(Index, NodeSize);
		Index = Index + 1;
		UntypedNode SupClassName = (UntypedNode) Parser.Get(Index, NodeSize);
		Index = Index + 1;
		UntypedNode BlockNode = (UntypedNode) Parser.Get(Index, NodeSize);
		Index = Index + 1;
		UNode.SetAtToken(ClassNameOffset, ClassName.KeyToken);
		UNode.SetAtToken(ClassParentNameOffset, SupClassName.KeyToken);
		UNode.SetAtNode(ClassBlockOffset, BlockNode);
		Parser.ReAssign(NodeSize, UNode);
		return EndIdx;
	}

	@Override
	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		return null;
	}
}

// action: <Symbol:$ClassDefinition>
class TopLevelDefinitionSyntax0 extends SyntaxAcceptor {
	static final TopLevelDefinitionSyntax0	Instance	= new TopLevelDefinitionSyntax0();

	@Override
	public int Parse(SyntaxModule Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
		Report("TopLevelDefinitionSyntax2", NodeSize);
		/* do nothing */
		return EndIdx;
	}

	@Override
	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		/* do nothing */
		return null;
	}
}
