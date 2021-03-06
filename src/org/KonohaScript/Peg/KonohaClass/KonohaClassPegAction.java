package org.KonohaScript.Peg.KonohaClass;

import org.KonohaScript.KonohaClass;
import org.KonohaScript.KonohaType;
import org.KonohaScript.KLib.TokenList;
import org.KonohaScript.Parser.TypeEnv;
import org.KonohaScript.Parser.UntypedNode;
import org.KonohaScript.PegParser.PegParser;
import org.KonohaScript.PegParser.SyntaxAcceptor;
import org.KonohaScript.SyntaxTree.DefineNode;
import org.KonohaScript.SyntaxTree.TypedNode;

// action: <Symbol:"class">, <Symbol:$#Symbol>, <Symbol:$#block>
class ClassDefinitionSyntax0 extends SyntaxAcceptor {
	static final int	ClassNameOffset			= ListOffset;
	static final int	ClassParentNameOffset	= ClassNameOffset + 1;
	static final int	ClassBlockOffset		= ClassNameOffset + 2;

	@Override
	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
		this.Report("ClassDefinitionSyntax0", NodeSize);
		UntypedNode UNode = this.CreateNodeWithSyntax(Parser, TokenList.get(BeginIdx), "$ClassDefinition");
		int Index = 0;
		UntypedNode ClassName = (UntypedNode) Parser.Get(Index, NodeSize);
		Index = Index + 1;
		UntypedNode Block = (UntypedNode) Parser.Get(Index, NodeSize);
		Index = Index + 1;
		UNode.SetAtToken(ClassNameOffset, ClassName.KeyToken);
		UNode.SetAtNode(ClassParentNameOffset, null);
		UNode.SetAtNode(ClassBlockOffset, Block);
		Parser.ReAssign(NodeSize, UNode);
		return EndIdx;
	}

	@Override
	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		int ClassFlag = 0;
		String ClassName = UNode.GetTokenString(ClassNameOffset, "");
		KonohaClass DefInfo = new KonohaClass(new KonohaType(UNode.NodeNameSpace.KonohaContext, ClassFlag, ClassName, null));
		return new DefineNode(TypeInfo, UNode.KeyToken, DefInfo);
	}
}

//action: <Symbol:"class">, <Symbol:$#Symbol>, <Symbol:"extends">, <Symbol:$#type>, <Symbol:$#block>
class ClassDefinitionSyntax1 extends SyntaxAcceptor {
	static final int	ClassNameOffset			= ListOffset;
	static final int	ClassParentNameOffset	= ClassNameOffset + 1;
	static final int	ClassBlockOffset		= ClassNameOffset + 2;

	@Override
	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
		this.Report("ClassDefinitionSyntax1", NodeSize);
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
		//FIXME
		return null;
	}
}

// action: <Symbol:$ClassDefinition>
class TopLevelDefinitionSyntax0 extends SyntaxAcceptor {
	@Override
	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
		this.Report("TopLevelDefinitionSyntax0", NodeSize);
		/* do nothing */
		return EndIdx;
	}

	@Override
	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
		/* do nothing */
		return null;
	}
}
