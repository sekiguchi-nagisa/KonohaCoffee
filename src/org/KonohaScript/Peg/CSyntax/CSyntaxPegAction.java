//package org.KonohaScript.Peg.CSyntax;import org.KonohaScript.KonohaType;
//import org.KonohaScript.KLib.TokenList;
//import org.KonohaScript.Parser.TypeEnv;
//import org.KonohaScript.Parser.UntypedNode;
//import org.KonohaScript.PegParser.PegParser;
//import org.KonohaScript.PegParser.SyntaxAcceptor;
//import org.KonohaScript.SyntaxTree.TypedNode;
//
//// action: <Repeat:<Symbol:$TopLevelDefinition>>
//class SourceCodeSyntax0 extends SyntaxAcceptor {
//	static final SourceCodeSyntax0	Instance	= new SourceCodeSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("SourceCodeSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		while(Index < NodeSize) {
//			List[Index] = Parser.Get(Index, NodeSize);
//			Index = Index + 1;
//		}
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$TopLevelStatement>
//class TopLevelDefinitionSyntax0 extends SyntaxAcceptor {
//	static final TopLevelDefinitionSyntax0	Instance	= new TopLevelDefinitionSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("TopLevelDefinitionSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$functionDefinition>
//class TopLevelDefinitionSyntax1 extends SyntaxAcceptor {
//	static final TopLevelDefinitionSyntax1	Instance	= new TopLevelDefinitionSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("TopLevelDefinitionSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$variableDeclaration>
//class TopLevelStatementSyntax0 extends SyntaxAcceptor {
//	static final TopLevelStatementSyntax0	Instance	= new TopLevelStatementSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("TopLevelStatementSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$EmptyStatement>
//class TopLevelStatementSyntax1 extends SyntaxAcceptor {
//	static final TopLevelStatementSyntax1	Instance	= new TopLevelStatementSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("TopLevelStatementSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$type>, <Symbol:$identifier>, <Symbol:$ParamDeclList>
//class functionSignatureSyntax0 extends SyntaxAcceptor {
//	static final functionSignatureSyntax0	Instance	= new functionSignatureSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("functionSignatureSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$block>
//class functionBodySyntax0 extends SyntaxAcceptor {
//	static final functionBodySyntax0	Instance	= new functionBodySyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("functionBodySyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$functionSignature>, <Symbol:$functionBody>
//class functionDefinitionSyntax0 extends SyntaxAcceptor {
//	static final functionDefinitionSyntax0	Instance	= new functionDefinitionSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("functionDefinitionSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$functionSignature>, <Symbol:";">
//class functionDefinitionSyntax1 extends SyntaxAcceptor {
//	static final functionDefinitionSyntax1	Instance	= new functionDefinitionSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("functionDefinitionSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"(">, <Symbol:")">
//class ParamDeclListSyntax0 extends SyntaxAcceptor {
//	static final ParamDeclListSyntax0	Instance	= new ParamDeclListSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("ParamDeclListSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"(">, <Symbol:$ParamDecls>, <Symbol:")">
//class ParamDeclListSyntax1 extends SyntaxAcceptor {
//	static final ParamDeclListSyntax1	Instance	= new ParamDeclListSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("ParamDeclListSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$ParamDecl>, <Repeat:<Group:<Symbol:","> <Symbol:$ParamDecl> >>
//class ParamDeclsSyntax0 extends SyntaxAcceptor {
//	static final ParamDeclsSyntax0	Instance	= new ParamDeclsSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("ParamDeclsSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		while(Index < NodeSize) {
//			List[Index] = Parser.Get(Index, NodeSize);
//			Index = Index + 1;
//		}
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$type>, <Symbol:$identifier>
//class ParamDeclSyntax0 extends SyntaxAcceptor {
//	static final ParamDeclSyntax0	Instance	= new ParamDeclSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("ParamDeclSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"(">, <Symbol:")">
//class ParameterListSyntax0 extends SyntaxAcceptor {
//	static final ParameterListSyntax0	Instance	= new ParameterListSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("ParameterListSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"(">, <Symbol:$Parameters>, <Symbol:")">
//class ParameterListSyntax1 extends SyntaxAcceptor {
//	static final ParameterListSyntax1	Instance	= new ParameterListSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("ParameterListSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$Parameter>, <Repeat:<Group:<Symbol:","> <Symbol:$Parameter> >>
//class ParametersSyntax0 extends SyntaxAcceptor {
//	static final ParametersSyntax0	Instance	= new ParametersSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("ParametersSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		while(Index < NodeSize) {
//			List[Index] = Parser.Get(Index, NodeSize);
//			Index = Index + 1;
//		}
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$expression>
//class ParameterSyntax0 extends SyntaxAcceptor {
//	static final ParameterSyntax0	Instance	= new ParameterSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("ParameterSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"NULL">
//class literalSyntax0 extends SyntaxAcceptor {
//	static final literalSyntax0	Instance	= new literalSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("literalSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		Parser.Push(new TreeNode("NULL"));
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"true">
//class literalSyntax1 extends SyntaxAcceptor {
//	static final literalSyntax1	Instance	= new literalSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("literalSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		Parser.Push(new TreeNode("true"));
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"false">
//class literalSyntax2 extends SyntaxAcceptor {
//	static final literalSyntax2	Instance	= new literalSyntax2();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("literalSyntax2", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		Parser.Push(new TreeNode("false"));
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$intLiteral>
//class literalSyntax3 extends SyntaxAcceptor {
//	static final literalSyntax3	Instance	= new literalSyntax3();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("literalSyntax3", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$stringLiteral>
//class literalSyntax4 extends SyntaxAcceptor {
//	static final literalSyntax4	Instance	= new literalSyntax4();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("literalSyntax4", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$functionType>
//class typeSyntax0 extends SyntaxAcceptor {
//	static final typeSyntax0	Instance	= new typeSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("typeSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$arrayType>
//class typeSyntax1 extends SyntaxAcceptor {
//	static final typeSyntax1	Instance	= new typeSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("typeSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$Type>
//class typeSyntax2 extends SyntaxAcceptor {
//	static final typeSyntax2	Instance	= new typeSyntax2();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("typeSyntax2", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$type>, <Symbol:"(">, <Symbol:"*">, <Symbol:")">, <Symbol:"(">, <Symbol:$TypeParamater>, <Symbol:")">
//class functionTypeSyntax0 extends SyntaxAcceptor {
//	static final functionTypeSyntax0	Instance	= new functionTypeSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("functionTypeSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$type>, <Group:<Symbol:"["> <Symbol:$intLiteral> >, <Repeat:<Group:<Symbol:"]"> <Symbol:> >>
//class arrayTypeSyntax0 extends SyntaxAcceptor {
//	static final arrayTypeSyntax0	Instance	= new arrayTypeSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("arrayTypeSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		while(Index < NodeSize) {
//		}
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$Type>, <Group:<Symbol:> <Symbol:"["> >, <Symbol:"]">, <Repeat:<Group:<Symbol:"]"> <Symbol:> >>
//class arrayTypeSyntax1 extends SyntaxAcceptor {
//	static final arrayTypeSyntax1	Instance	= new arrayTypeSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("arrayTypeSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		while(Index < NodeSize) {
//		}
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"(">, <Symbol:")">
//class TypeParamaterSyntax0 extends SyntaxAcceptor {
//	static final TypeParamaterSyntax0	Instance	= new TypeParamaterSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("TypeParamaterSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"(">, <Symbol:$TypeParamDecls>, <Symbol:")">
//class TypeParamaterSyntax1 extends SyntaxAcceptor {
//	static final TypeParamaterSyntax1	Instance	= new TypeParamaterSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("TypeParamaterSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$TypeParamDecl>, <Repeat:<Group:<Symbol:","> <Symbol:$TypeParamDecl> >>
//class TypeParamDeclsSyntax0 extends SyntaxAcceptor {
//	static final TypeParamDeclsSyntax0	Instance	= new TypeParamDeclsSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("TypeParamDeclsSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		while(Index < NodeSize) {
//			List[Index] = Parser.Get(Index, NodeSize);
//			Index = Index + 1;
//		}
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$type>
//class TypeParamDeclSyntax0 extends SyntaxAcceptor {
//	static final TypeParamDeclSyntax0	Instance	= new TypeParamDeclSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("TypeParamDeclSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$block>
//class statementSyntax0 extends SyntaxAcceptor {
//	static final statementSyntax0	Instance	= new statementSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("statementSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$variableDeclaration>
//class statementSyntax1 extends SyntaxAcceptor {
//	static final statementSyntax1	Instance	= new statementSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("statementSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$ifStatement>
//class statementSyntax2 extends SyntaxAcceptor {
//	static final statementSyntax2	Instance	= new statementSyntax2();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("statementSyntax2", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$whileStatement>
//class statementSyntax3 extends SyntaxAcceptor {
//	static final statementSyntax3	Instance	= new statementSyntax3();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("statementSyntax3", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$breakStatement>
//class statementSyntax4 extends SyntaxAcceptor {
//	static final statementSyntax4	Instance	= new statementSyntax4();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("statementSyntax4", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$continueStatement>
//class statementSyntax5 extends SyntaxAcceptor {
//	static final statementSyntax5	Instance	= new statementSyntax5();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("statementSyntax5", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$returnStatement>
//class statementSyntax6 extends SyntaxAcceptor {
//	static final statementSyntax6	Instance	= new statementSyntax6();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("statementSyntax6", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$EmptyStatement>
//class statementSyntax7 extends SyntaxAcceptor {
//	static final statementSyntax7	Instance	= new statementSyntax7();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("statementSyntax7", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$expressionStatement>
//class statementSyntax8 extends SyntaxAcceptor {
//	static final statementSyntax8	Instance	= new statementSyntax8();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("statementSyntax8", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$identifier>
//class variableSyntax0 extends SyntaxAcceptor {
//	static final variableSyntax0	Instance	= new variableSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("variableSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"=">
//class EQSyntax0 extends SyntaxAcceptor {
//	static final EQSyntax0	Instance	= new EQSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("EQSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		Parser.Push(new TreeNode("="));
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$type>, <Symbol:$identifier>, <Symbol:";">
//class variableDeclarationSyntax0 extends SyntaxAcceptor {
//	static final variableDeclarationSyntax0	Instance	= new variableDeclarationSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("variableDeclarationSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$type>, <Symbol:$variable>, <Symbol:$EQ>, <Symbol:$expression>, <Symbol:";">
//class variableDeclarationSyntax1 extends SyntaxAcceptor {
//	static final variableDeclarationSyntax1	Instance	= new variableDeclarationSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("variableDeclarationSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$type>, <Symbol:$variable>, <Symbol:"[">, <Symbol:"]">, <Symbol:$EQ>, <Symbol:$expression>, <Symbol:";">
//class variableDeclarationSyntax2 extends SyntaxAcceptor {
//	static final variableDeclarationSyntax2	Instance	= new variableDeclarationSyntax2();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("variableDeclarationSyntax2", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Repeat:<Symbol:$statement>>
//class statementsSyntax0 extends SyntaxAcceptor {
//	static final statementsSyntax0	Instance	= new statementsSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("statementsSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		while(Index < NodeSize) {
//			List[Index] = Parser.Get(Index, NodeSize);
//			Index = Index + 1;
//		}
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"{">, <Symbol:$statements>, <Symbol:"}">
//class blockSyntax0 extends SyntaxAcceptor {
//	static final blockSyntax0	Instance	= new blockSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("blockSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"if">, <Symbol:"(">, <Symbol:$expression>, <Symbol:")">, <Symbol:$block>, <Symbol:"else">, <Symbol:$block>
//class ifStatementSyntax0 extends SyntaxAcceptor {
//	static final ifStatementSyntax0	Instance	= new ifStatementSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("ifStatementSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"if">, <Symbol:"(">, <Symbol:$expression>, <Symbol:")">, <Symbol:$block>
//class ifStatementSyntax1 extends SyntaxAcceptor {
//	static final ifStatementSyntax1	Instance	= new ifStatementSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("ifStatementSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"while">, <Symbol:"(">, <Symbol:$expression>, <Symbol:")">, <Symbol:$block>
//class whileStatementSyntax0 extends SyntaxAcceptor {
//	static final whileStatementSyntax0	Instance	= new whileStatementSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("whileStatementSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"break">, <Symbol:";">
//class breakStatementSyntax0 extends SyntaxAcceptor {
//	static final breakStatementSyntax0	Instance	= new breakStatementSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("breakStatementSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"continue">, <Symbol:";">
//class continueStatementSyntax0 extends SyntaxAcceptor {
//	static final continueStatementSyntax0	Instance	= new continueStatementSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("continueStatementSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"return">, <Symbol:";">
//class returnStatementSyntax0 extends SyntaxAcceptor {
//	static final returnStatementSyntax0	Instance	= new returnStatementSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("returnStatementSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"return">, <Symbol:$expression>, <Symbol:";">
//class returnStatementSyntax1 extends SyntaxAcceptor {
//	static final returnStatementSyntax1	Instance	= new returnStatementSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("returnStatementSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:";">
//class EmptyStatementSyntax0 extends SyntaxAcceptor {
//	static final EmptyStatementSyntax0	Instance	= new EmptyStatementSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("EmptyStatementSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		Parser.Push(new TreeNode(";"));
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$expression>, <Symbol:";">
//class expressionStatementSyntax0 extends SyntaxAcceptor {
//	static final expressionStatementSyntax0	Instance	= new expressionStatementSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("expressionStatementSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$leftHandSideExpression>, <Symbol:$EQ>, <Symbol:$expression>
//class expressionSyntax0 extends SyntaxAcceptor {
//	static final expressionSyntax0	Instance	= new expressionSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("expressionSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$logicalOrExpression>
//class expressionSyntax1 extends SyntaxAcceptor {
//	static final expressionSyntax1	Instance	= new expressionSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("expressionSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$callExpression>
//class leftHandSideExpressionSyntax0 extends SyntaxAcceptor {
//	static final leftHandSideExpressionSyntax0	Instance	= new leftHandSideExpressionSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("leftHandSideExpressionSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$newExpression>
//class leftHandSideExpressionSyntax1 extends SyntaxAcceptor {
//	static final leftHandSideExpressionSyntax1	Instance	= new leftHandSideExpressionSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("leftHandSideExpressionSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$memberExpression>, <Symbol:$ParameterList>
//class callExpressionSyntax0 extends SyntaxAcceptor {
//	static final callExpressionSyntax0	Instance	= new callExpressionSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("callExpressionSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$primary>, <Repeat:<Symbol:$selector>>
//class memberExpressionSyntax0 extends SyntaxAcceptor {
//	static final memberExpressionSyntax0	Instance	= new memberExpressionSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("memberExpressionSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		while(Index < NodeSize) {
//			List[Index] = Parser.Get(Index, NodeSize);
//			Index = Index + 1;
//		}
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"this">
//class primarySyntax0 extends SyntaxAcceptor {
//	static final primarySyntax0	Instance	= new primarySyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("primarySyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		Parser.Push(new TreeNode("this"));
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$literal>
//class primarySyntax1 extends SyntaxAcceptor {
//	static final primarySyntax1	Instance	= new primarySyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("primarySyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$identifier>
//class primarySyntax2 extends SyntaxAcceptor {
//	static final primarySyntax2	Instance	= new primarySyntax2();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("primarySyntax2", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$type>
//class primarySyntax3 extends SyntaxAcceptor {
//	static final primarySyntax3	Instance	= new primarySyntax3();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("primarySyntax3", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"(">, <Symbol:$expression>, <Symbol:")">
//class primarySyntax4 extends SyntaxAcceptor {
//	static final primarySyntax4	Instance	= new primarySyntax4();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("primarySyntax4", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"[">, <Symbol:$expression>, <Symbol:"]">
//class selectorSyntax0 extends SyntaxAcceptor {
//	static final selectorSyntax0	Instance	= new selectorSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("selectorSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:".">, <Symbol:$identifier>
//class selectorSyntax1 extends SyntaxAcceptor {
//	static final selectorSyntax1	Instance	= new selectorSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("selectorSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"new">, <Symbol:$type>, <Symbol:$ParameterList>
//class newExpressionSyntax0 extends SyntaxAcceptor {
//	static final newExpressionSyntax0	Instance	= new newExpressionSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("newExpressionSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$memberExpression>
//class newExpressionSyntax1 extends SyntaxAcceptor {
//	static final newExpressionSyntax1	Instance	= new newExpressionSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("newExpressionSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$logicalAndExpression>, <Repeat:<Group:<Symbol:"||"> <Symbol:$logicalAndExpression> >>
//class logicalOrExpressionSyntax0 extends SyntaxAcceptor {
//	static final logicalOrExpressionSyntax0	Instance	= new logicalOrExpressionSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("logicalOrExpressionSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		while(Index < NodeSize) {
//			List[Index] = Parser.Get(Index, NodeSize);
//			Index = Index + 1;
//		}
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$relationExpression>, <Repeat:<Group:<Symbol:"&&"> <Symbol:$relationExpression> >>
//class logicalAndExpressionSyntax0 extends SyntaxAcceptor {
//	static final logicalAndExpressionSyntax0	Instance	= new logicalAndExpressionSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("logicalAndExpressionSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		while(Index < NodeSize) {
//			List[Index] = Parser.Get(Index, NodeSize);
//			Index = Index + 1;
//		}
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$additiveExpression>, <Symbol:$relationOperator>, <Symbol:$additiveExpression>
//class relationExpressionSyntax0 extends SyntaxAcceptor {
//	static final relationExpressionSyntax0	Instance	= new relationExpressionSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("relationExpressionSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$additiveExpression>
//class relationExpressionSyntax1 extends SyntaxAcceptor {
//	static final relationExpressionSyntax1	Instance	= new relationExpressionSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("relationExpressionSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"=">, <Symbol:"=">
//class relationOperatorSyntax0 extends SyntaxAcceptor {
//	static final relationOperatorSyntax0	Instance	= new relationOperatorSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("relationOperatorSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"!">, <Symbol:"=">
//class relationOperatorSyntax1 extends SyntaxAcceptor {
//	static final relationOperatorSyntax1	Instance	= new relationOperatorSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("relationOperatorSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:">">, <Symbol:"=">
//class relationOperatorSyntax2 extends SyntaxAcceptor {
//	static final relationOperatorSyntax2	Instance	= new relationOperatorSyntax2();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("relationOperatorSyntax2", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:">">
//class relationOperatorSyntax3 extends SyntaxAcceptor {
//	static final relationOperatorSyntax3	Instance	= new relationOperatorSyntax3();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("relationOperatorSyntax3", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		Parser.Push(new TreeNode(">"));
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"<">, <Symbol:"=">
//class relationOperatorSyntax4 extends SyntaxAcceptor {
//	static final relationOperatorSyntax4	Instance	= new relationOperatorSyntax4();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("relationOperatorSyntax4", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"<">
//class relationOperatorSyntax5 extends SyntaxAcceptor {
//	static final relationOperatorSyntax5	Instance	= new relationOperatorSyntax5();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("relationOperatorSyntax5", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		Parser.Push(new TreeNode("<"));
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"<">, <Symbol:"<">
//class shiftOperatorSyntax0 extends SyntaxAcceptor {
//	static final shiftOperatorSyntax0	Instance	= new shiftOperatorSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("shiftOperatorSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:">">, <Symbol:">">
//class shiftOperatorSyntax1 extends SyntaxAcceptor {
//	static final shiftOperatorSyntax1	Instance	= new shiftOperatorSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("shiftOperatorSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"+">
//class additiveOperatorSyntax0 extends SyntaxAcceptor {
//	static final additiveOperatorSyntax0	Instance	= new additiveOperatorSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("additiveOperatorSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		Parser.Push(new TreeNode("+"));
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"-">
//class additiveOperatorSyntax1 extends SyntaxAcceptor {
//	static final additiveOperatorSyntax1	Instance	= new additiveOperatorSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("additiveOperatorSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		Parser.Push(new TreeNode("-"));
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"*">
//class multiplicativeOperatorSyntax0 extends SyntaxAcceptor {
//	static final multiplicativeOperatorSyntax0	Instance	= new multiplicativeOperatorSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("multiplicativeOperatorSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		Parser.Push(new TreeNode("*"));
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"/">
//class multiplicativeOperatorSyntax1 extends SyntaxAcceptor {
//	static final multiplicativeOperatorSyntax1	Instance	= new multiplicativeOperatorSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("multiplicativeOperatorSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		Parser.Push(new TreeNode("/"));
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"%">
//class multiplicativeOperatorSyntax2 extends SyntaxAcceptor {
//	static final multiplicativeOperatorSyntax2	Instance	= new multiplicativeOperatorSyntax2();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("multiplicativeOperatorSyntax2", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		Parser.Push(new TreeNode("%"));
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$multiplicativeExpression>, <Repeat:<Group:<Symbol:$additiveOperator> <Symbol:$multiplicativeExpression> >>
//class additiveExpressionSyntax0 extends SyntaxAcceptor {
//	static final additiveExpressionSyntax0	Instance	= new additiveExpressionSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("additiveExpressionSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		while(Index < NodeSize) {
//			List[Index] = Parser.Get(Index, NodeSize);
//			Index = Index + 1;
//			List[Index] = Parser.Get(Index, NodeSize);
//			Index = Index + 1;
//		}
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$unaryExpression>, <Repeat:<Group:<Symbol:$multiplicativeOperator> <Symbol:$unaryExpression> >>
//class multiplicativeExpressionSyntax0 extends SyntaxAcceptor {
//	static final multiplicativeExpressionSyntax0	Instance	= new multiplicativeExpressionSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("multiplicativeExpressionSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		while(Index < NodeSize) {
//			List[Index] = Parser.Get(Index, NodeSize);
//			Index = Index + 1;
//			List[Index] = Parser.Get(Index, NodeSize);
//			Index = Index + 1;
//		}
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"+">, <Symbol:"+">
//class incOperatorSyntax0 extends SyntaxAcceptor {
//	static final incOperatorSyntax0	Instance	= new incOperatorSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("incOperatorSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"-">, <Symbol:"-">
//class incOperatorSyntax1 extends SyntaxAcceptor {
//	static final incOperatorSyntax1	Instance	= new incOperatorSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("incOperatorSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$incOperator>, <Symbol:$leftHandSideExpression>
//class unaryExpressionSyntax0 extends SyntaxAcceptor {
//	static final unaryExpressionSyntax0	Instance	= new unaryExpressionSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("unaryExpressionSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:"(">, <Symbol:$type>, <Symbol:")">, <Symbol:$leftHandSideExpression>
//class unaryExpressionSyntax1 extends SyntaxAcceptor {
//	static final unaryExpressionSyntax1	Instance	= new unaryExpressionSyntax1();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("unaryExpressionSyntax1", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$leftHandSideExpression>, <Symbol:$incOperator>
//class unaryExpressionSyntax2 extends SyntaxAcceptor {
//	static final unaryExpressionSyntax2	Instance	= new unaryExpressionSyntax2();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("unaryExpressionSyntax2", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$leftHandSideExpression>
//class unaryExpressionSyntax3 extends SyntaxAcceptor {
//	static final unaryExpressionSyntax3	Instance	= new unaryExpressionSyntax3();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("unaryExpressionSyntax3", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
//
//// action: <Symbol:$Symbol>
//class identifierSyntax0 extends SyntaxAcceptor {
//	static final identifierSyntax0	Instance	= new identifierSyntax0();
//
//	@Override
//	public int Parse(PegParser Parser, TokenList TokenList, int BeginIdx, int EndIdx, int NodeSize) {
//		this.Report("identifierSyntax0", NodeSize);
//		int Index = 0;
//		Object[] List = new Object[NodeSize];
//		List[Index] = Parser.Get(Index, NodeSize);
//		Index = Index + 1;
//		if(NodeSize > 0) {
//			Parser.ReAssign(NodeSize, List[0]);
//		}
//		// FIXME
//		return EndIdx;
//	}
//
//	@Override
//	public TypedNode TypeCheck(TypeEnv Gamma, UntypedNode UNode, KonohaType TypeInfo) {
//		// FIXME
//		return null;
//	}
//}
