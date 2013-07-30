//package org.KonohaScript.Peg.CSyntax;
//
//import org.KonohaScript.KLib.TokenList;
//import org.KonohaScript.KonohaNameSpace;
//import org.KonohaScript.PegParser.SyntaxAcceptor;
//import org.KonohaScript.PegParser.PegParser;
//import org.KonohaScript.PegParser.SyntaxPattern;
///*
//[$SourceCode:
//	[
//		<Repeat:<Symbol:$TopLevelDefinition>>
//	]
//]
// */
//class SourceCodeSyntax extends SyntaxPattern {
//	SourceCodeSyntax() {
//		super("$SourceCode");
//	}
//	public SyntaxAcceptor Acceptor0 = new SourceCodeSyntax0();
//	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $SourceCode");
//		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	@Override
//	public int Match(PegParser Parser, TokenList TokenList) {
//		int NodeSize = 0;
//		int pos0 = Parser.Cursor;
//		int thunkpos0 = Parser.ThunkPos;
//		int NodeSize0 = NodeSize;
//		this.Report("Enter $SourceCode");
//		while(true) {
//			if(Parser.Match("$TopLevelDefinition", TokenList) >= 0) {
//				NodeSize = NodeSize + 1;
//				continue;
//			}
//			return this.action0("$SourceCode", Parser, pos0, NodeSize);
//		}
//	}
//}
///*
//[$TopLevelDefinition:
//	[
//		<Symbol:$TopLevelStatement>
//	]
//	[
//		<Symbol:$functionDefinition>
//	]
//]
// */
//class TopLevelDefinitionSyntax extends SyntaxPattern {
//	TopLevelDefinitionSyntax() {
//		super("$TopLevelDefinition");
//	}
//	public SyntaxAcceptor Acceptor0 = new TopLevelDefinitionSyntax0();
//	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $TopLevelDefinition");
//		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	public SyntaxAcceptor Acceptor1 = new TopLevelDefinitionSyntax1();
//	int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $TopLevelDefinition");
//		Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	@Override
//	public int Match(PegParser Parser, TokenList TokenList) {
//		int NodeSize = 0;
//		int pos0 = Parser.Cursor;
//		int thunkpos0 = Parser.ThunkPos;
//		int NodeSize0 = NodeSize;
//		this.Report("Enter $TopLevelDefinition");
//		if(Parser.Match("$TopLevelStatement", TokenList) >= 0) {
//			NodeSize = NodeSize + 1;
//			return this.action0("$TopLevelDefinition", Parser, pos0, NodeSize);
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $TopLevelDefinition 0");
//		if(Parser.Match("$functionDefinition", TokenList) >= 0) {
//			NodeSize = NodeSize + 1;
//			return this.action1("$TopLevelDefinition", Parser, pos0, NodeSize);
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $TopLevelDefinition 0");
//		return this.Fail("$TopLevelDefinition", Parser);
//	}
//}
///*
//[$TopLevelStatement:
//	[
//		<Symbol:$variableDeclaration>
//	]
//	[
//		<Symbol:$EmptyStatement>
//	]
//]
// */
//class TopLevelStatementSyntax extends SyntaxPattern {
//	TopLevelStatementSyntax() {
//		super("$TopLevelStatement");
//	}
//	public SyntaxAcceptor Acceptor0 = new TopLevelStatementSyntax0();
//	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $TopLevelStatement");
//		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	public SyntaxAcceptor Acceptor1 = new TopLevelStatementSyntax1();
//	int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $TopLevelStatement");
//		Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	@Override
//	public int Match(PegParser Parser, TokenList TokenList) {
//		int NodeSize = 0;
//		int pos0 = Parser.Cursor;
//		int thunkpos0 = Parser.ThunkPos;
//		int NodeSize0 = NodeSize;
//		this.Report("Enter $TopLevelStatement");
//		if(Parser.Match("$variableDeclaration", TokenList) >= 0) {
//			NodeSize = NodeSize + 1;
//			return this.action0("$TopLevelStatement", Parser, pos0, NodeSize);
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $TopLevelStatement 0");
//		if(Parser.Match("$EmptyStatement", TokenList) >= 0) {
//			NodeSize = NodeSize + 1;
//			return this.action1("$TopLevelStatement", Parser, pos0, NodeSize);
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $TopLevelStatement 0");
//		return this.Fail("$TopLevelStatement", Parser);
//	}
//}
///*
//[$functionSignature:
//	[
//		<Symbol:$type>
//		<Symbol:$identifier>
//		<Symbol:$ParamDeclList>
//	]
//]
// */
//class functionSignatureSyntax extends SyntaxPattern {
//	functionSignatureSyntax() {
//		super("$functionSignature");
//	}
//	public SyntaxAcceptor Acceptor0 = new functionSignatureSyntax0();
//	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $functionSignature");
//		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	@Override
//	public int Match(PegParser Parser, TokenList TokenList) {
//		int NodeSize = 0;
//		int pos0 = Parser.Cursor;
//		int thunkpos0 = Parser.ThunkPos;
//		int NodeSize0 = NodeSize;
//		this.Report("Enter $functionSignature");
//		if(Parser.Match("$type", TokenList) >= 0) {
//			NodeSize = NodeSize + 1;
//			if(Parser.Match("$identifier", TokenList) >= 0) {
//				NodeSize = NodeSize + 1;
//				if(Parser.Match("$ParamDeclList", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return this.action0("$functionSignature", Parser, pos0, NodeSize);
//				}
//			}
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $functionSignature 0");
//		return this.Fail("$functionSignature", Parser);
//	}
//}
///*
//[$functionBody:
//	[
//		<Symbol:$block>
//	]
//]
// */
//class functionBodySyntax extends SyntaxPattern {
//	functionBodySyntax() {
//		super("$functionBody");
//	}
//	public SyntaxAcceptor Acceptor0 = new functionBodySyntax0();
//	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $functionBody");
//		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	@Override
//	public int Match(PegParser Parser, TokenList TokenList) {
//		int NodeSize = 0;
//		int pos0 = Parser.Cursor;
//		int thunkpos0 = Parser.ThunkPos;
//		int NodeSize0 = NodeSize;
//		this.Report("Enter $functionBody");
//		if(Parser.Match("$block", TokenList) >= 0) {
//			NodeSize = NodeSize + 1;
//			return this.action0("$functionBody", Parser, pos0, NodeSize);
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $functionBody 0");
//		return this.Fail("$functionBody", Parser);
//	}
//}
///*
//[$functionDefinition:
//	[
//		<Symbol:$functionSignature>
//		<Symbol:$functionBody>
//	]
//	[
//		<Symbol:$functionSignature>
//		<Symbol:";">
//	]
//]
// */
//class functionDefinitionSyntax extends SyntaxPattern {
//	functionDefinitionSyntax() {
//		super("$functionDefinition");
//	}
//	public SyntaxAcceptor Acceptor0 = new functionDefinitionSyntax0();
//	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $functionDefinition");
//		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	public SyntaxAcceptor Acceptor1 = new functionDefinitionSyntax1();
//	int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $functionDefinition");
//		Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	@Override
//	public int Match(PegParser Parser, TokenList TokenList) {
//		int NodeSize = 0;
//		int pos0 = Parser.Cursor;
//		int thunkpos0 = Parser.ThunkPos;
//		int NodeSize0 = NodeSize;
//		this.Report("Enter $functionDefinition");
//		if(Parser.Match("$functionSignature", TokenList) >= 0) {
//			NodeSize = NodeSize + 1;
//			if(Parser.Match("$functionBody", TokenList) >= 0) {
//				NodeSize = NodeSize + 1;
//				return this.action0("$functionDefinition", Parser, pos0, NodeSize);
//			}
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $functionDefinition 0");
//		if(Parser.Match("$functionSignature", TokenList) >= 0) {
//			NodeSize = NodeSize + 1;
//			if(Parser.MatchToken("$SemiColon", TokenList, Parser.Cursor) >= 0) {
//				return this.action1("$functionDefinition", Parser, pos0, NodeSize);
//			}
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $functionDefinition 0");
//		return this.Fail("$functionDefinition", Parser);
//	}
//}
///*
//[$ParamDeclList:
//	[
//		<Symbol:"(">
//		<Symbol:")">
//	]
//	[
//		<Symbol:"(">
//		<Symbol:$ParamDecls>
//		<Symbol:")">
//	]
//]
// */
//class ParamDeclListSyntax extends SyntaxPattern {
//	ParamDeclListSyntax() {
//		super("$ParamDeclList");
//	}
//	public SyntaxAcceptor Acceptor0 = new ParamDeclListSyntax0();
//	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $ParamDeclList");
//		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	public SyntaxAcceptor Acceptor1 = new ParamDeclListSyntax1();
//	int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $ParamDeclList");
//		Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	@Override
//	public int Match(PegParser Parser, TokenList TokenList) {
//		int NodeSize = 0;
//		int pos0 = Parser.Cursor;
//		int thunkpos0 = Parser.ThunkPos;
//		int NodeSize0 = NodeSize;
//		this.Report("Enter $ParamDeclList");
//		if(Parser.MatchToken("$LBrace", TokenList, Parser.Cursor) >= 0) {
//			if(Parser.MatchToken("$RBrace", TokenList, Parser.Cursor) >= 0) {
//				return this.action0("$ParamDeclList", Parser, pos0, NodeSize);
//			}
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $ParamDeclList 0");
//		if(Parser.MatchToken("$LBrace", TokenList, Parser.Cursor) >= 0) {
//			if(Parser.Match("$ParamDecls", TokenList) >= 0) {
//				NodeSize = NodeSize + 1;
//				if(Parser.MatchToken("$RBrace", TokenList, Parser.Cursor) >= 0) {
//					return this.action1("$ParamDeclList", Parser, pos0, NodeSize);
//				}
//			}
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $ParamDeclList 0");
//		return this.Fail("$ParamDeclList", Parser);
//	}
//}
///*
//[$ParamDecls:
//	[
//		<Symbol:$ParamDecl>
//		<Repeat:<Group:<Symbol:","> <Symbol:$ParamDecl> >>
//	]
//]
// */
//class ParamDeclsSyntax extends SyntaxPattern {
//	ParamDeclsSyntax() {
//		super("$ParamDecls");
//	}
//	public SyntaxAcceptor Acceptor0 = new ParamDeclsSyntax0();
//	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $ParamDecls");
//		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	@Override
//	public int Match(PegParser Parser, TokenList TokenList) {
//		int NodeSize = 0;
//		int pos0 = Parser.Cursor;
//		int thunkpos0 = Parser.ThunkPos;
//		int NodeSize0 = NodeSize;
//		this.Report("Enter $ParamDecls");
//		if(Parser.Match("$ParamDecl", TokenList) >= 0) {
//			NodeSize = NodeSize + 1;
//			while(true) {
//				if(Parser.MatchToken("$Camma", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.Match("$ParamDecl", TokenList) >= 0) {
//						NodeSize = NodeSize + 1;
//						continue;
//					}
//				}
//				return this.action0("$ParamDecls", Parser, pos0, NodeSize);
//			}
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $ParamDecls 0");
//		return this.Fail("$ParamDecls", Parser);
//	}
//}
///*
//[$ParamDecl:
//	[
//		<Symbol:$type>
//		<Symbol:$identifier>
//	]
//]
// */
//class ParamDeclSyntax extends SyntaxPattern {
//	ParamDeclSyntax() {
//		super("$ParamDecl");
//	}
//	public SyntaxAcceptor Acceptor0 = new ParamDeclSyntax0();
//	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $ParamDecl");
//		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	@Override
//	public int Match(PegParser Parser, TokenList TokenList) {
//		int NodeSize = 0;
//		int pos0 = Parser.Cursor;
//		int thunkpos0 = Parser.ThunkPos;
//		int NodeSize0 = NodeSize;
//		this.Report("Enter $ParamDecl");
//		if(Parser.Match("$type", TokenList) >= 0) {
//			NodeSize = NodeSize + 1;
//			if(Parser.Match("$identifier", TokenList) >= 0) {
//				NodeSize = NodeSize + 1;
//				return this.action0("$ParamDecl", Parser, pos0, NodeSize);
//			}
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $ParamDecl 0");
//		return this.Fail("$ParamDecl", Parser);
//	}
//}
///*
//[$ParameterList:
//	[
//		<Symbol:"(">
//		<Symbol:")">
//	]
//	[
//		<Symbol:"(">
//		<Symbol:$Parameters>
//		<Symbol:")">
//	]
//]
// */
//class ParameterListSyntax extends SyntaxPattern {
//	ParameterListSyntax() {
//		super("$ParameterList");
//	}
//	public SyntaxAcceptor Acceptor0 = new ParameterListSyntax0();
//	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $ParameterList");
//		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	public SyntaxAcceptor Acceptor1 = new ParameterListSyntax1();
//	int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $ParameterList");
//		Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	@Override
//	public int Match(PegParser Parser, TokenList TokenList) {
//		int NodeSize = 0;
//		int pos0 = Parser.Cursor;
//		int thunkpos0 = Parser.ThunkPos;
//		int NodeSize0 = NodeSize;
//		this.Report("Enter $ParameterList");
//		if(Parser.MatchToken("$LBrace", TokenList, Parser.Cursor) >= 0) {
//			if(Parser.MatchToken("$RBrace", TokenList, Parser.Cursor) >= 0) {
//				return this.action0("$ParameterList", Parser, pos0, NodeSize);
//			}
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $ParameterList 0");
//		if(Parser.MatchToken("$LBrace", TokenList, Parser.Cursor) >= 0) {
//			if(Parser.Match("$Parameters", TokenList) >= 0) {
//				NodeSize = NodeSize + 1;
//				if(Parser.MatchToken("$RBrace", TokenList, Parser.Cursor) >= 0) {
//					return this.action1("$ParameterList", Parser, pos0, NodeSize);
//				}
//			}
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $ParameterList 0");
//		return this.Fail("$ParameterList", Parser);
//	}
//}
///*
//[$Parameters:
//	[
//		<Symbol:$Parameter>
//		<Repeat:<Group:<Symbol:","> <Symbol:$Parameter> >>
//	]
//]
// */
//class ParametersSyntax extends SyntaxPattern {
//	ParametersSyntax() {
//		super("$Parameters");
//	}
//	public SyntaxAcceptor Acceptor0 = new ParametersSyntax0();
//	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $Parameters");
//		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	@Override
//	public int Match(PegParser Parser, TokenList TokenList) {
//		int NodeSize = 0;
//		int pos0 = Parser.Cursor;
//		int thunkpos0 = Parser.ThunkPos;
//		int NodeSize0 = NodeSize;
//		this.Report("Enter $Parameters");
//		if(Parser.Match("$Parameter", TokenList) >= 0) {
//			NodeSize = NodeSize + 1;
//			while(true) {
//				if(Parser.MatchToken("$Camma", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.Match("$Parameter", TokenList) >= 0) {
//						NodeSize = NodeSize + 1;
//						continue;
//					}
//				}
//				return this.action0("$Parameters", Parser, pos0, NodeSize);
//			}
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $Parameters 0");
//		return this.Fail("$Parameters", Parser);
//	}
//}
///*
//[$Parameter:
//	[
//		<Symbol:$expression>
//	]
//]
// */
//class ParameterSyntax extends SyntaxPattern {
//	ParameterSyntax() {
//		super("$Parameter");
//	}
//	public SyntaxAcceptor Acceptor0 = new ParameterSyntax0();
//	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $Parameter");
//		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	@Override
//	public int Match(PegParser Parser, TokenList TokenList) {
//		int NodeSize = 0;
//		int pos0 = Parser.Cursor;
//		int thunkpos0 = Parser.ThunkPos;
//		int NodeSize0 = NodeSize;
//		this.Report("Enter $Parameter");
//		if(Parser.Match("$expression", TokenList) >= 0) {
//			NodeSize = NodeSize + 1;
//			return this.action0("$Parameter", Parser, pos0, NodeSize);
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $Parameter 0");
//		return this.Fail("$Parameter", Parser);
//	}
//}
///*
//[$literal:
//	[
//		<Symbol:"NULL">
//	]
//	[
//		<Symbol:"true">
//	]
//	[
//		<Symbol:"false">
//	]
//	[
//		<Symbol:$intLiteral>
//	]
//	[
//		<Symbol:$stringLiteral>
//	]
//]
// */
//class literalSyntax extends SyntaxPattern {
//	literalSyntax() {
//		super("$literal");
//	}
//	public SyntaxAcceptor Acceptor0 = new literalSyntax0();
//	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $literal");
//		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	public SyntaxAcceptor Acceptor1 = new literalSyntax1();
//	int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $literal");
//		Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	public SyntaxAcceptor Acceptor2 = new literalSyntax2();
//	int action2(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $literal");
//		Parser.PushThunk(this.Acceptor2, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	public SyntaxAcceptor Acceptor3 = new literalSyntax3();
//	int action3(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $literal");
//		Parser.PushThunk(this.Acceptor3, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	public SyntaxAcceptor Acceptor4 = new literalSyntax4();
//	int action4(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $literal");
//		Parser.PushThunk(this.Acceptor4, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	@Override
//	public int Match(PegParser Parser, TokenList TokenList) {
//		int NodeSize = 0;
//		int pos0 = Parser.Cursor;
//		int thunkpos0 = Parser.ThunkPos;
//		int NodeSize0 = NodeSize;
//		this.Report("Enter $literal");
//		if(Parser.MatchToken("NULL", TokenList, Parser.Cursor) >= 0) {
//			return this.action0("$literal", Parser, pos0, NodeSize);
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $literal 0");
//		if(Parser.MatchToken("true", TokenList, Parser.Cursor) >= 0) {
//			return this.action1("$literal", Parser, pos0, NodeSize);
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $literal 0");
//		if(Parser.MatchToken("false", TokenList, Parser.Cursor) >= 0) {
//			return this.action2("$literal", Parser, pos0, NodeSize);
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $literal 0");
//		if(Parser.Match("$intLiteral", TokenList) >= 0) {
//			NodeSize = NodeSize + 1;
//			return this.action3("$literal", Parser, pos0, NodeSize);
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $literal 0");
//		if(Parser.Match("$stringLiteral", TokenList) >= 0) {
//			NodeSize = NodeSize + 1;
//			return this.action4("$literal", Parser, pos0, NodeSize);
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $literal 0");
//		return this.Fail("$literal", Parser);
//	}
//}
///*
//[$type:
//	[
//		<Symbol:$functionType>
//	]
//	[
//		<Symbol:$arrayType>
//	]
//	[
//		<Symbol:$Type>
//	]
//]
// */
//class typeSyntax extends SyntaxPattern {
//	typeSyntax() {
//		super("$type");
//	}
//	public SyntaxAcceptor Acceptor0 = new typeSyntax0();
//	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $type");
//		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	public SyntaxAcceptor Acceptor1 = new typeSyntax1();
//	int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $type");
//		Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	public SyntaxAcceptor Acceptor2 = new typeSyntax2();
//	int action2(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $type");
//		Parser.PushThunk(this.Acceptor2, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	@Override
//	public int Match(PegParser Parser, TokenList TokenList) {
//		int NodeSize = 0;
//		int pos0 = Parser.Cursor;
//		int thunkpos0 = Parser.ThunkPos;
//		int NodeSize0 = NodeSize;
//		this.Report("Enter $type");
//		if(Parser.Match("$functionType", TokenList) >= 0) {
//			NodeSize = NodeSize + 1;
//			return this.action0("$type", Parser, pos0, NodeSize);
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $type 0");
//		if(Parser.Match("$arrayType", TokenList) >= 0) {
//			NodeSize = NodeSize + 1;
//			return this.action1("$type", Parser, pos0, NodeSize);
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $type 0");
//		if(Parser.Match("$Type", TokenList) >= 0) {
//			NodeSize = NodeSize + 1;
//			return this.action2("$type", Parser, pos0, NodeSize);
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $type 0");
//		return this.Fail("$type", Parser);
//	}
//}
///*
//[$functionType:
//	[
//		<Symbol:$type>
//		<Symbol:"(">
//		<Symbol:"*">
//		<Symbol:")">
//		<Symbol:"(">
//		<Symbol:$TypeParamater>
//		<Symbol:")">
//	]
//]
// */
//class functionTypeSyntax extends SyntaxPattern {
//	functionTypeSyntax() {
//		super("$functionType");
//	}
//	public SyntaxAcceptor Acceptor0 = new functionTypeSyntax0();
//	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $functionType");
//		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	@Override
//	public int Match(PegParser Parser, TokenList TokenList) {
//		int NodeSize = 0;
//		int pos0 = Parser.Cursor;
//		int thunkpos0 = Parser.ThunkPos;
//		int NodeSize0 = NodeSize;
//		this.Report("Enter $functionType");
//		if(Parser.Match("$type", TokenList) >= 0) {
//			NodeSize = NodeSize + 1;
//			if(Parser.MatchToken("$LBrace", TokenList, Parser.Cursor) >= 0) {
//				if(Parser.MatchToken("$Star", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.MatchToken("$RBrace", TokenList, Parser.Cursor) >= 0) {
//						if(Parser.MatchToken("$LBrace", TokenList, Parser.Cursor) >= 0) {
//							if(Parser.Match("$TypeParamater", TokenList) >= 0) {
//								NodeSize = NodeSize + 1;
//								if(Parser.MatchToken("$RBrace", TokenList, Parser.Cursor) >= 0) {
//									return this.action0("$functionType", Parser, pos0, NodeSize);
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $functionType 0");
//		return this.Fail("$functionType", Parser);
//	}
//}
///*
//[$arrayType:
//	[
//		<Symbol:$type>
//		<Group:<Symbol:"["> <Symbol:$intLiteral> >
//		<Repeat:<Group:<Symbol:"]"> <Symbol:> >>
//	]
//	[
//		<Symbol:$Type>
//		<Group:<Symbol:> <Symbol:"["> >
//		<Symbol:"]">
//		<Repeat:<Group:<Symbol:"]"> <Symbol:> >>
//	]
//]
// */
//class arrayTypeSyntax extends SyntaxPattern {
//	arrayTypeSyntax() {
//		super("$arrayType");
//	}
//	public SyntaxAcceptor Acceptor0 = new arrayTypeSyntax0();
//	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $arrayType");
//		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	public SyntaxAcceptor Acceptor1 = new arrayTypeSyntax1();
//	int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//		this.Report("Accept $arrayType");
//		Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//		return Parser.Cursor;
//	}
//	@Override
//	public int Match(PegParser Parser, TokenList TokenList) {
//		int NodeSize = 0;
//		int pos0 = Parser.Cursor;
//		int thunkpos0 = Parser.ThunkPos;
//		int NodeSize0 = NodeSize;
//		Report("Enter $arrayType");
//		if(Parser.Match("$type", TokenList) >= 0) {
//			NodeSize = NodeSize + 1;
//			if(Parser.MatchToken("$LParenthesis", TokenList, Parser.Cursor) >= 0) {
//				if(Parser.Match("$intLiteral", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					while(true) {
//						if(Parser.MatchToken("$RParenthesis", TokenList, Parser.Cursor) >= 0) {
//							if(Parser.MatchToken(, TokenList, Parser.Cursor) >= 0) {
//								continue;
//							}
//						}
//						return action0("$arrayType", Parser, pos0, NodeSize);
//					}
//				}
//			}
//			NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $arrayType 0");
//			if(Parser.Match("$Type", TokenList) >= 0) {
//				NodeSize = NodeSize + 1;
//				if(Parser.MatchToken(, TokenList, Parser.Cursor) >= 0) {
//					if(Parser.MatchToken("$LParenthesis", TokenList, Parser.Cursor) >= 0) {
//						if(Parser.MatchToken("$RParenthesis", TokenList, Parser.Cursor) >= 0) {
//							while(true) {
//								if(Parser.MatchToken("$RParenthesis", TokenList, Parser.Cursor) >= 0) {
//									if(Parser.MatchToken(, TokenList, Parser.Cursor) >= 0) {
//										continue;
//									}
//								}
//								return action1("$arrayType", Parser, pos0, NodeSize);
//							}
//						}
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $arrayType 0");
//				return Fail("$arrayType", Parser);
//			}
//		}
//		/*
//[$TypeParamater:
//	[
//		<Symbol:"(">
//		<Symbol:")">
//	]
//	[
//		<Symbol:"(">
//		<Symbol:$TypeParamDecls>
//		<Symbol:")">
//	]
//]
//		 */
//		class TypeParamaterSyntax extends SyntaxPattern {
//			TypeParamaterSyntax() {
//				super("$TypeParamater");
//			}
//			public SyntaxAcceptor Acceptor0 = new TypeParamaterSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $TypeParamater");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor1 = new TypeParamaterSyntax1();
//			int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $TypeParamater");
//				Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $TypeParamater");
//				if(Parser.MatchToken("$LBrace", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.MatchToken("$RBrace", TokenList, Parser.Cursor) >= 0) {
//						return action0("$TypeParamater", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $TypeParamater 0");
//				if(Parser.MatchToken("$LBrace", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.Match("$TypeParamDecls", TokenList) >= 0) {
//						NodeSize = NodeSize + 1;
//						if(Parser.MatchToken("$RBrace", TokenList, Parser.Cursor) >= 0) {
//							return action1("$TypeParamater", Parser, pos0, NodeSize);
//						}
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $TypeParamater 0");
//				return Fail("$TypeParamater", Parser);
//			}
//		}
//		/*
//[$TypeParamDecls:
//	[
//		<Symbol:$TypeParamDecl>
//		<Repeat:<Group:<Symbol:","> <Symbol:$TypeParamDecl> >>
//	]
//]
//		 */
//		class TypeParamDeclsSyntax extends SyntaxPattern {
//			TypeParamDeclsSyntax() {
//				super("$TypeParamDecls");
//			}
//			public SyntaxAcceptor Acceptor0 = new TypeParamDeclsSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $TypeParamDecls");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $TypeParamDecls");
//				if(Parser.Match("$TypeParamDecl", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					while(true) {
//						if(Parser.MatchToken("$Camma", TokenList, Parser.Cursor) >= 0) {
//							if(Parser.Match("$TypeParamDecl", TokenList) >= 0) {
//								NodeSize = NodeSize + 1;
//								continue;
//							}
//						}
//						return action0("$TypeParamDecls", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $TypeParamDecls 0");
//				return Fail("$TypeParamDecls", Parser);
//			}
//		}
//		/*
//[$TypeParamDecl:
//	[
//		<Symbol:$type>
//	]
//]
//		 */
//		class TypeParamDeclSyntax extends SyntaxPattern {
//			TypeParamDeclSyntax() {
//				super("$TypeParamDecl");
//			}
//			public SyntaxAcceptor Acceptor0 = new TypeParamDeclSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $TypeParamDecl");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $TypeParamDecl");
//				if(Parser.Match("$type", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action0("$TypeParamDecl", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $TypeParamDecl 0");
//				return Fail("$TypeParamDecl", Parser);
//			}
//		}
//		/*
//[$statement:
//	[
//		<Symbol:$block>
//	]
//	[
//		<Symbol:$variableDeclaration>
//	]
//	[
//		<Symbol:$ifStatement>
//	]
//	[
//		<Symbol:$whileStatement>
//	]
//	[
//		<Symbol:$breakStatement>
//	]
//	[
//		<Symbol:$continueStatement>
//	]
//	[
//		<Symbol:$returnStatement>
//	]
//	[
//		<Symbol:$EmptyStatement>
//	]
//	[
//		<Symbol:$expressionStatement>
//	]
//]
//		 */
//		class statementSyntax extends SyntaxPattern {
//			statementSyntax() {
//				super("$statement");
//			}
//			public SyntaxAcceptor Acceptor0 = new statementSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $statement");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor1 = new statementSyntax1();
//			int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $statement");
//				Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor2 = new statementSyntax2();
//			int action2(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $statement");
//				Parser.PushThunk(this.Acceptor2, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor3 = new statementSyntax3();
//			int action3(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $statement");
//				Parser.PushThunk(this.Acceptor3, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor4 = new statementSyntax4();
//			int action4(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $statement");
//				Parser.PushThunk(this.Acceptor4, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor5 = new statementSyntax5();
//			int action5(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $statement");
//				Parser.PushThunk(this.Acceptor5, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor6 = new statementSyntax6();
//			int action6(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $statement");
//				Parser.PushThunk(this.Acceptor6, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor7 = new statementSyntax7();
//			int action7(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $statement");
//				Parser.PushThunk(this.Acceptor7, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor8 = new statementSyntax8();
//			int action8(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $statement");
//				Parser.PushThunk(this.Acceptor8, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $statement");
//				if(Parser.Match("$block", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action0("$statement", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $statement 0");
//				if(Parser.Match("$variableDeclaration", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action1("$statement", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $statement 0");
//				if(Parser.Match("$ifStatement", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action2("$statement", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $statement 0");
//				if(Parser.Match("$whileStatement", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action3("$statement", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $statement 0");
//				if(Parser.Match("$breakStatement", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action4("$statement", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $statement 0");
//				if(Parser.Match("$continueStatement", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action5("$statement", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $statement 0");
//				if(Parser.Match("$returnStatement", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action6("$statement", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $statement 0");
//				if(Parser.Match("$EmptyStatement", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action7("$statement", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $statement 0");
//				if(Parser.Match("$expressionStatement", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action8("$statement", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $statement 0");
//				return Fail("$statement", Parser);
//			}
//		}
//		/*
//[$variable:
//	[
//		<Symbol:$identifier>
//	]
//]
//		 */
//		class variableSyntax extends SyntaxPattern {
//			variableSyntax() {
//				super("$variable");
//			}
//			public SyntaxAcceptor Acceptor0 = new variableSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $variable");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $variable");
//				if(Parser.Match("$identifier", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action0("$variable", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $variable 0");
//				return Fail("$variable", Parser);
//			}
//		}
//		/*
//[$EQ:
//	[
//		<Symbol:"=">
//	]
//]
//		 */
//		class EQSyntax extends SyntaxPattern {
//			EQSyntax() {
//				super("$EQ");
//			}
//			public SyntaxAcceptor Acceptor0 = new EQSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $EQ");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $EQ");
//				if(Parser.MatchToken("$Equal", TokenList, Parser.Cursor) >= 0) {
//					return action0("$EQ", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $EQ 0");
//				return Fail("$EQ", Parser);
//			}
//		}
//		/*
//[$variableDeclaration:
//	[
//		<Symbol:$type>
//		<Symbol:$identifier>
//		<Symbol:";">
//	]
//	[
//		<Symbol:$type>
//		<Symbol:$variable>
//		<Symbol:$EQ>
//		<Symbol:$expression>
//		<Symbol:";">
//	]
//	[
//		<Symbol:$type>
//		<Symbol:$variable>
//		<Symbol:"[">
//		<Symbol:"]">
//		<Symbol:$EQ>
//		<Symbol:$expression>
//		<Symbol:";">
//	]
//]
//		 */
//		class variableDeclarationSyntax extends SyntaxPattern {
//			variableDeclarationSyntax() {
//				super("$variableDeclaration");
//			}
//			public SyntaxAcceptor Acceptor0 = new variableDeclarationSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $variableDeclaration");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor1 = new variableDeclarationSyntax1();
//			int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $variableDeclaration");
//				Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor2 = new variableDeclarationSyntax2();
//			int action2(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $variableDeclaration");
//				Parser.PushThunk(this.Acceptor2, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $variableDeclaration");
//				if(Parser.Match("$type", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					if(Parser.Match("$identifier", TokenList) >= 0) {
//						NodeSize = NodeSize + 1;
//						if(Parser.MatchToken("$SemiColon", TokenList, Parser.Cursor) >= 0) {
//							return action0("$variableDeclaration", Parser, pos0, NodeSize);
//						}
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $variableDeclaration 0");
//				if(Parser.Match("$type", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					if(Parser.Match("$variable", TokenList) >= 0) {
//						NodeSize = NodeSize + 1;
//						if(Parser.Match("$EQ", TokenList) >= 0) {
//							NodeSize = NodeSize + 1;
//							if(Parser.Match("$expression", TokenList) >= 0) {
//								NodeSize = NodeSize + 1;
//								if(Parser.MatchToken("$SemiColon", TokenList, Parser.Cursor) >= 0) {
//									return action1("$variableDeclaration", Parser, pos0, NodeSize);
//								}
//							}
//						}
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $variableDeclaration 0");
//				if(Parser.Match("$type", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					if(Parser.Match("$variable", TokenList) >= 0) {
//						NodeSize = NodeSize + 1;
//						if(Parser.MatchToken("$LParenthesis", TokenList, Parser.Cursor) >= 0) {
//							if(Parser.MatchToken("$RParenthesis", TokenList, Parser.Cursor) >= 0) {
//								if(Parser.Match("$EQ", TokenList) >= 0) {
//									NodeSize = NodeSize + 1;
//									if(Parser.Match("$expression", TokenList) >= 0) {
//										NodeSize = NodeSize + 1;
//										if(Parser.MatchToken("$SemiColon", TokenList, Parser.Cursor) >= 0) {
//											return action2("$variableDeclaration", Parser, pos0, NodeSize);
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $variableDeclaration 0");
//				return Fail("$variableDeclaration", Parser);
//			}
//		}
//		/*
//[$statements:
//	[
//		<Repeat:<Symbol:$statement>>
//	]
//]
//		 */
//		class statementsSyntax extends SyntaxPattern {
//			statementsSyntax() {
//				super("$statements");
//			}
//			public SyntaxAcceptor Acceptor0 = new statementsSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $statements");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $statements");
//				while(true) {
//					if(Parser.Match("$statement", TokenList) >= 0) {
//						NodeSize = NodeSize + 1;
//						continue;
//					}
//					return action0("$statements", Parser, pos0, NodeSize);
//				}
//			}
//		}
//		/*
//[$block:
//	[
//		<Symbol:"{">
//		<Symbol:$statements>
//		<Symbol:"}">
//	]
//]
//		 */
//		class blockSyntax extends SyntaxPattern {
//			blockSyntax() {
//				super("$block");
//			}
//			public SyntaxAcceptor Acceptor0 = new blockSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $block");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $block");
//				if(Parser.MatchToken("$LCBrace", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.Match("$statements", TokenList) >= 0) {
//						NodeSize = NodeSize + 1;
//						if(Parser.MatchToken("$RCBrace", TokenList, Parser.Cursor) >= 0) {
//							return action0("$block", Parser, pos0, NodeSize);
//						}
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $block 0");
//				return Fail("$block", Parser);
//			}
//		}
//		/*
//[$ifStatement:
//	[
//		<Symbol:"if">
//		<Symbol:"(">
//		<Symbol:$expression>
//		<Symbol:")">
//		<Symbol:$block>
//		<Symbol:"else">
//		<Symbol:$block>
//	]
//	[
//		<Symbol:"if">
//		<Symbol:"(">
//		<Symbol:$expression>
//		<Symbol:")">
//		<Symbol:$block>
//	]
//]
//		 */
//		class ifStatementSyntax extends SyntaxPattern {
//			ifStatementSyntax() {
//				super("$ifStatement");
//			}
//			public SyntaxAcceptor Acceptor0 = new ifStatementSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $ifStatement");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor1 = new ifStatementSyntax1();
//			int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $ifStatement");
//				Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $ifStatement");
//				if(Parser.MatchToken("if", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.MatchToken("$LBrace", TokenList, Parser.Cursor) >= 0) {
//						if(Parser.Match("$expression", TokenList) >= 0) {
//							NodeSize = NodeSize + 1;
//							if(Parser.MatchToken("$RBrace", TokenList, Parser.Cursor) >= 0) {
//								if(Parser.Match("$block", TokenList) >= 0) {
//									NodeSize = NodeSize + 1;
//									if(Parser.MatchToken("else", TokenList, Parser.Cursor) >= 0) {
//										if(Parser.Match("$block", TokenList) >= 0) {
//											NodeSize = NodeSize + 1;
//											return action0("$ifStatement", Parser, pos0, NodeSize);
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $ifStatement 0");
//				if(Parser.MatchToken("if", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.MatchToken("$LBrace", TokenList, Parser.Cursor) >= 0) {
//						if(Parser.Match("$expression", TokenList) >= 0) {
//							NodeSize = NodeSize + 1;
//							if(Parser.MatchToken("$RBrace", TokenList, Parser.Cursor) >= 0) {
//								if(Parser.Match("$block", TokenList) >= 0) {
//									NodeSize = NodeSize + 1;
//									return action1("$ifStatement", Parser, pos0, NodeSize);
//								}
//							}
//						}
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $ifStatement 0");
//				return Fail("$ifStatement", Parser);
//			}
//		}
//		/*
//[$whileStatement:
//	[
//		<Symbol:"while">
//		<Symbol:"(">
//		<Symbol:$expression>
//		<Symbol:")">
//		<Symbol:$block>
//	]
//]
//		 */
//		class whileStatementSyntax extends SyntaxPattern {
//			whileStatementSyntax() {
//				super("$whileStatement");
//			}
//			public SyntaxAcceptor Acceptor0 = new whileStatementSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $whileStatement");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $whileStatement");
//				if(Parser.MatchToken("while", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.MatchToken("$LBrace", TokenList, Parser.Cursor) >= 0) {
//						if(Parser.Match("$expression", TokenList) >= 0) {
//							NodeSize = NodeSize + 1;
//							if(Parser.MatchToken("$RBrace", TokenList, Parser.Cursor) >= 0) {
//								if(Parser.Match("$block", TokenList) >= 0) {
//									NodeSize = NodeSize + 1;
//									return action0("$whileStatement", Parser, pos0, NodeSize);
//								}
//							}
//						}
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $whileStatement 0");
//				return Fail("$whileStatement", Parser);
//			}
//		}
//		/*
//[$breakStatement:
//	[
//		<Symbol:"break">
//		<Symbol:";">
//	]
//]
//		 */
//		class breakStatementSyntax extends SyntaxPattern {
//			breakStatementSyntax() {
//				super("$breakStatement");
//			}
//			public SyntaxAcceptor Acceptor0 = new breakStatementSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $breakStatement");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $breakStatement");
//				if(Parser.MatchToken("break", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.MatchToken("$SemiColon", TokenList, Parser.Cursor) >= 0) {
//						return action0("$breakStatement", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $breakStatement 0");
//				return Fail("$breakStatement", Parser);
//			}
//		}
//		/*
//[$continueStatement:
//	[
//		<Symbol:"continue">
//		<Symbol:";">
//	]
//]
//		 */
//		class continueStatementSyntax extends SyntaxPattern {
//			continueStatementSyntax() {
//				super("$continueStatement");
//			}
//			public SyntaxAcceptor Acceptor0 = new continueStatementSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $continueStatement");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $continueStatement");
//				if(Parser.MatchToken("continue", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.MatchToken("$SemiColon", TokenList, Parser.Cursor) >= 0) {
//						return action0("$continueStatement", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $continueStatement 0");
//				return Fail("$continueStatement", Parser);
//			}
//		}
//		/*
//[$returnStatement:
//	[
//		<Symbol:"return">
//		<Symbol:";">
//	]
//	[
//		<Symbol:"return">
//		<Symbol:$expression>
//		<Symbol:";">
//	]
//]
//		 */
//		class returnStatementSyntax extends SyntaxPattern {
//			returnStatementSyntax() {
//				super("$returnStatement");
//			}
//			public SyntaxAcceptor Acceptor0 = new returnStatementSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $returnStatement");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor1 = new returnStatementSyntax1();
//			int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $returnStatement");
//				Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $returnStatement");
//				if(Parser.MatchToken("return", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.MatchToken("$SemiColon", TokenList, Parser.Cursor) >= 0) {
//						return action0("$returnStatement", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $returnStatement 0");
//				if(Parser.MatchToken("return", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.Match("$expression", TokenList) >= 0) {
//						NodeSize = NodeSize + 1;
//						if(Parser.MatchToken("$SemiColon", TokenList, Parser.Cursor) >= 0) {
//							return action1("$returnStatement", Parser, pos0, NodeSize);
//						}
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $returnStatement 0");
//				return Fail("$returnStatement", Parser);
//			}
//		}
//		/*
//[$EmptyStatement:
//	[
//		<Symbol:";">
//	]
//]
//		 */
//		class EmptyStatementSyntax extends SyntaxPattern {
//			EmptyStatementSyntax() {
//				super("$EmptyStatement");
//			}
//			public SyntaxAcceptor Acceptor0 = new EmptyStatementSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $EmptyStatement");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $EmptyStatement");
//				if(Parser.MatchToken("$SemiColon", TokenList, Parser.Cursor) >= 0) {
//					return action0("$EmptyStatement", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $EmptyStatement 0");
//				return Fail("$EmptyStatement", Parser);
//			}
//		}
//		/*
//[$expressionStatement:
//	[
//		<Symbol:$expression>
//		<Symbol:";">
//	]
//]
//		 */
//		class expressionStatementSyntax extends SyntaxPattern {
//			expressionStatementSyntax() {
//				super("$expressionStatement");
//			}
//			public SyntaxAcceptor Acceptor0 = new expressionStatementSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $expressionStatement");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $expressionStatement");
//				if(Parser.Match("$expression", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					if(Parser.MatchToken("$SemiColon", TokenList, Parser.Cursor) >= 0) {
//						return action0("$expressionStatement", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $expressionStatement 0");
//				return Fail("$expressionStatement", Parser);
//			}
//		}
//		/*
//[$expression:
//	[
//		<Symbol:$leftHandSideExpression>
//		<Symbol:$EQ>
//		<Symbol:$expression>
//	]
//	[
//		<Symbol:$logicalOrExpression>
//	]
//]
//		 */
//		class expressionSyntax extends SyntaxPattern {
//			expressionSyntax() {
//				super("$expression");
//			}
//			public SyntaxAcceptor Acceptor0 = new expressionSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $expression");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor1 = new expressionSyntax1();
//			int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $expression");
//				Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $expression");
//				if(Parser.Match("$leftHandSideExpression", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					if(Parser.Match("$EQ", TokenList) >= 0) {
//						NodeSize = NodeSize + 1;
//						if(Parser.Match("$expression", TokenList) >= 0) {
//							NodeSize = NodeSize + 1;
//							return action0("$expression", Parser, pos0, NodeSize);
//						}
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $expression 0");
//				if(Parser.Match("$logicalOrExpression", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action1("$expression", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $expression 0");
//				return Fail("$expression", Parser);
//			}
//		}
//		/*
//[$leftHandSideExpression:
//	[
//		<Symbol:$callExpression>
//	]
//	[
//		<Symbol:$newExpression>
//	]
//]
//		 */
//		class leftHandSideExpressionSyntax extends SyntaxPattern {
//			leftHandSideExpressionSyntax() {
//				super("$leftHandSideExpression");
//			}
//			public SyntaxAcceptor Acceptor0 = new leftHandSideExpressionSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $leftHandSideExpression");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor1 = new leftHandSideExpressionSyntax1();
//			int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $leftHandSideExpression");
//				Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $leftHandSideExpression");
//				if(Parser.Match("$callExpression", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action0("$leftHandSideExpression", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $leftHandSideExpression 0");
//				if(Parser.Match("$newExpression", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action1("$leftHandSideExpression", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $leftHandSideExpression 0");
//				return Fail("$leftHandSideExpression", Parser);
//			}
//		}
//		/*
//[$callExpression:
//	[
//		<Symbol:$memberExpression>
//		<Symbol:$ParameterList>
//	]
//]
//		 */
//		class callExpressionSyntax extends SyntaxPattern {
//			callExpressionSyntax() {
//				super("$callExpression");
//			}
//			public SyntaxAcceptor Acceptor0 = new callExpressionSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $callExpression");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $callExpression");
//				if(Parser.Match("$memberExpression", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					if(Parser.Match("$ParameterList", TokenList) >= 0) {
//						NodeSize = NodeSize + 1;
//						return action0("$callExpression", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $callExpression 0");
//				return Fail("$callExpression", Parser);
//			}
//		}
//		/*
//[$memberExpression:
//	[
//		<Symbol:$primary>
//		<Repeat:<Symbol:$selector>>
//	]
//]
//		 */
//		class memberExpressionSyntax extends SyntaxPattern {
//			memberExpressionSyntax() {
//				super("$memberExpression");
//			}
//			public SyntaxAcceptor Acceptor0 = new memberExpressionSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $memberExpression");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $memberExpression");
//				if(Parser.Match("$primary", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					while(true) {
//						if(Parser.Match("$selector", TokenList) >= 0) {
//							NodeSize = NodeSize + 1;
//							continue;
//						}
//						return action0("$memberExpression", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $memberExpression 0");
//				return Fail("$memberExpression", Parser);
//			}
//		}
//		/*
//[$primary:
//	[
//		<Symbol:"this">
//	]
//	[
//		<Symbol:$literal>
//	]
//	[
//		<Symbol:$identifier>
//	]
//	[
//		<Symbol:$type>
//	]
//	[
//		<Symbol:"(">
//		<Symbol:$expression>
//		<Symbol:")">
//	]
//]
//		 */
//		class primarySyntax extends SyntaxPattern {
//			primarySyntax() {
//				super("$primary");
//			}
//			public SyntaxAcceptor Acceptor0 = new primarySyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $primary");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor1 = new primarySyntax1();
//			int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $primary");
//				Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor2 = new primarySyntax2();
//			int action2(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $primary");
//				Parser.PushThunk(this.Acceptor2, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor3 = new primarySyntax3();
//			int action3(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $primary");
//				Parser.PushThunk(this.Acceptor3, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor4 = new primarySyntax4();
//			int action4(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $primary");
//				Parser.PushThunk(this.Acceptor4, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $primary");
//				if(Parser.MatchToken("this", TokenList, Parser.Cursor) >= 0) {
//					return action0("$primary", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $primary 0");
//				if(Parser.Match("$literal", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action1("$primary", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $primary 0");
//				if(Parser.Match("$identifier", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action2("$primary", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $primary 0");
//				if(Parser.Match("$type", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action3("$primary", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $primary 0");
//				if(Parser.MatchToken("$LBrace", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.Match("$expression", TokenList) >= 0) {
//						NodeSize = NodeSize + 1;
//						if(Parser.MatchToken("$RBrace", TokenList, Parser.Cursor) >= 0) {
//							return action4("$primary", Parser, pos0, NodeSize);
//						}
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $primary 0");
//				return Fail("$primary", Parser);
//			}
//		}
//		/*
//[$selector:
//	[
//		<Symbol:"[">
//		<Symbol:$expression>
//		<Symbol:"]">
//	]
//	[
//		<Symbol:".">
//		<Symbol:$identifier>
//	]
//]
//		 */
//		class selectorSyntax extends SyntaxPattern {
//			selectorSyntax() {
//				super("$selector");
//			}
//			public SyntaxAcceptor Acceptor0 = new selectorSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $selector");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor1 = new selectorSyntax1();
//			int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $selector");
//				Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $selector");
//				if(Parser.MatchToken("$LParenthesis", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.Match("$expression", TokenList) >= 0) {
//						NodeSize = NodeSize + 1;
//						if(Parser.MatchToken("$RParenthesis", TokenList, Parser.Cursor) >= 0) {
//							return action0("$selector", Parser, pos0, NodeSize);
//						}
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $selector 0");
//				if(Parser.MatchToken("$Dot", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.Match("$identifier", TokenList) >= 0) {
//						NodeSize = NodeSize + 1;
//						return action1("$selector", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $selector 0");
//				return Fail("$selector", Parser);
//			}
//		}
//		/*
//[$newExpression:
//	[
//		<Symbol:"new">
//		<Symbol:$type>
//		<Symbol:$ParameterList>
//	]
//	[
//		<Symbol:$memberExpression>
//	]
//]
//		 */
//		class newExpressionSyntax extends SyntaxPattern {
//			newExpressionSyntax() {
//				super("$newExpression");
//			}
//			public SyntaxAcceptor Acceptor0 = new newExpressionSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $newExpression");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor1 = new newExpressionSyntax1();
//			int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $newExpression");
//				Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $newExpression");
//				if(Parser.MatchToken("new", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.Match("$type", TokenList) >= 0) {
//						NodeSize = NodeSize + 1;
//						if(Parser.Match("$ParameterList", TokenList) >= 0) {
//							NodeSize = NodeSize + 1;
//							return action0("$newExpression", Parser, pos0, NodeSize);
//						}
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $newExpression 0");
//				if(Parser.Match("$memberExpression", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action1("$newExpression", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $newExpression 0");
//				return Fail("$newExpression", Parser);
//			}
//		}
//		/*
//[$logicalOrExpression:
//	[
//		<Symbol:$logicalAndExpression>
//		<Repeat:<Group:<Symbol:"||"> <Symbol:$logicalAndExpression> >>
//	]
//]
//		 */
//		class logicalOrExpressionSyntax extends SyntaxPattern {
//			logicalOrExpressionSyntax() {
//				super("$logicalOrExpression");
//			}
//			public SyntaxAcceptor Acceptor0 = new logicalOrExpressionSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $logicalOrExpression");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $logicalOrExpression");
//				if(Parser.Match("$logicalAndExpression", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					while(true) {
//						if(Parser.MatchToken("||", TokenList, Parser.Cursor) >= 0) {
//							if(Parser.Match("$logicalAndExpression", TokenList) >= 0) {
//								NodeSize = NodeSize + 1;
//								continue;
//							}
//						}
//						return action0("$logicalOrExpression", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $logicalOrExpression 0");
//				return Fail("$logicalOrExpression", Parser);
//			}
//		}
//		/*
//[$logicalAndExpression:
//	[
//		<Symbol:$relationExpression>
//		<Repeat:<Group:<Symbol:"&&"> <Symbol:$relationExpression> >>
//	]
//]
//		 */
//		class logicalAndExpressionSyntax extends SyntaxPattern {
//			logicalAndExpressionSyntax() {
//				super("$logicalAndExpression");
//			}
//			public SyntaxAcceptor Acceptor0 = new logicalAndExpressionSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $logicalAndExpression");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $logicalAndExpression");
//				if(Parser.Match("$relationExpression", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					while(true) {
//						if(Parser.MatchToken("&&", TokenList, Parser.Cursor) >= 0) {
//							if(Parser.Match("$relationExpression", TokenList) >= 0) {
//								NodeSize = NodeSize + 1;
//								continue;
//							}
//						}
//						return action0("$logicalAndExpression", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $logicalAndExpression 0");
//				return Fail("$logicalAndExpression", Parser);
//			}
//		}
//		/*
//[$relationExpression:
//	[
//		<Symbol:$additiveExpression>
//		<Symbol:$relationOperator>
//		<Symbol:$additiveExpression>
//	]
//	[
//		<Symbol:$additiveExpression>
//	]
//]
//		 */
//		class relationExpressionSyntax extends SyntaxPattern {
//			relationExpressionSyntax() {
//				super("$relationExpression");
//			}
//			public SyntaxAcceptor Acceptor0 = new relationExpressionSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $relationExpression");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor1 = new relationExpressionSyntax1();
//			int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $relationExpression");
//				Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $relationExpression");
//				if(Parser.Match("$additiveExpression", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					if(Parser.Match("$relationOperator", TokenList) >= 0) {
//						NodeSize = NodeSize + 1;
//						if(Parser.Match("$additiveExpression", TokenList) >= 0) {
//							NodeSize = NodeSize + 1;
//							return action0("$relationExpression", Parser, pos0, NodeSize);
//						}
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $relationExpression 0");
//				if(Parser.Match("$additiveExpression", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action1("$relationExpression", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $relationExpression 0");
//				return Fail("$relationExpression", Parser);
//			}
//		}
//		/*
//[$relationOperator:
//	[
//		<Symbol:"=">
//		<Symbol:"=">
//	]
//	[
//		<Symbol:"!">
//		<Symbol:"=">
//	]
//	[
//		<Symbol:">">
//		<Symbol:"=">
//	]
//	[
//		<Symbol:">">
//	]
//	[
//		<Symbol:"<">
//		<Symbol:"=">
//	]
//	[
//		<Symbol:"<">
//	]
//]
//		 */
//		class relationOperatorSyntax extends SyntaxPattern {
//			relationOperatorSyntax() {
//				super("$relationOperator");
//			}
//			public SyntaxAcceptor Acceptor0 = new relationOperatorSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $relationOperator");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor1 = new relationOperatorSyntax1();
//			int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $relationOperator");
//				Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor2 = new relationOperatorSyntax2();
//			int action2(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $relationOperator");
//				Parser.PushThunk(this.Acceptor2, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor3 = new relationOperatorSyntax3();
//			int action3(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $relationOperator");
//				Parser.PushThunk(this.Acceptor3, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor4 = new relationOperatorSyntax4();
//			int action4(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $relationOperator");
//				Parser.PushThunk(this.Acceptor4, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor5 = new relationOperatorSyntax5();
//			int action5(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $relationOperator");
//				Parser.PushThunk(this.Acceptor5, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $relationOperator");
//				if(Parser.MatchToken("$Equal", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.MatchToken("$Equal", TokenList, Parser.Cursor) >= 0) {
//						return action0("$relationOperator", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $relationOperator 0");
//				if(Parser.MatchToken("$Exclamation", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.MatchToken("$Equal", TokenList, Parser.Cursor) >= 0) {
//						return action1("$relationOperator", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $relationOperator 0");
//				if(Parser.MatchToken("$GraterThan", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.MatchToken("$Equal", TokenList, Parser.Cursor) >= 0) {
//						return action2("$relationOperator", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $relationOperator 0");
//				if(Parser.MatchToken("$GraterThan", TokenList, Parser.Cursor) >= 0) {
//					return action3("$relationOperator", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $relationOperator 0");
//				if(Parser.MatchToken("$LessThan", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.MatchToken("$Equal", TokenList, Parser.Cursor) >= 0) {
//						return action4("$relationOperator", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $relationOperator 0");
//				if(Parser.MatchToken("$LessThan", TokenList, Parser.Cursor) >= 0) {
//					return action5("$relationOperator", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $relationOperator 0");
//				return Fail("$relationOperator", Parser);
//			}
//		}
//		/*
//[$shiftOperator:
//	[
//		<Symbol:"<">
//		<Symbol:"<">
//	]
//	[
//		<Symbol:">">
//		<Symbol:">">
//	]
//]
//		 */
//		class shiftOperatorSyntax extends SyntaxPattern {
//			shiftOperatorSyntax() {
//				super("$shiftOperator");
//			}
//			public SyntaxAcceptor Acceptor0 = new shiftOperatorSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $shiftOperator");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor1 = new shiftOperatorSyntax1();
//			int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $shiftOperator");
//				Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $shiftOperator");
//				if(Parser.MatchToken("$LessThan", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.MatchToken("$LessThan", TokenList, Parser.Cursor) >= 0) {
//						return action0("$shiftOperator", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $shiftOperator 0");
//				if(Parser.MatchToken("$GraterThan", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.MatchToken("$GraterThan", TokenList, Parser.Cursor) >= 0) {
//						return action1("$shiftOperator", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $shiftOperator 0");
//				return Fail("$shiftOperator", Parser);
//			}
//		}
//		/*
//[$additiveOperator:
//	[
//		<Symbol:"+">
//	]
//	[
//		<Symbol:"-">
//	]
//]
//		 */
//		class additiveOperatorSyntax extends SyntaxPattern {
//			additiveOperatorSyntax() {
//				super("$additiveOperator");
//			}
//			public SyntaxAcceptor Acceptor0 = new additiveOperatorSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $additiveOperator");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor1 = new additiveOperatorSyntax1();
//			int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $additiveOperator");
//				Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $additiveOperator");
//				if(Parser.MatchToken("$Plus", TokenList, Parser.Cursor) >= 0) {
//					return action0("$additiveOperator", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $additiveOperator 0");
//				if(Parser.MatchToken("$Minus", TokenList, Parser.Cursor) >= 0) {
//					return action1("$additiveOperator", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $additiveOperator 0");
//				return Fail("$additiveOperator", Parser);
//			}
//		}
//		/*
//[$multiplicativeOperator:
//	[
//		<Symbol:"*">
//	]
//	[
//		<Symbol:"/">
//	]
//	[
//		<Symbol:"%">
//	]
//]
//		 */
//		class multiplicativeOperatorSyntax extends SyntaxPattern {
//			multiplicativeOperatorSyntax() {
//				super("$multiplicativeOperator");
//			}
//			public SyntaxAcceptor Acceptor0 = new multiplicativeOperatorSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $multiplicativeOperator");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor1 = new multiplicativeOperatorSyntax1();
//			int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $multiplicativeOperator");
//				Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor2 = new multiplicativeOperatorSyntax2();
//			int action2(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $multiplicativeOperator");
//				Parser.PushThunk(this.Acceptor2, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $multiplicativeOperator");
//				if(Parser.MatchToken("$Star", TokenList, Parser.Cursor) >= 0) {
//					return action0("$multiplicativeOperator", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $multiplicativeOperator 0");
//				if(Parser.MatchToken("$Slash", TokenList, Parser.Cursor) >= 0) {
//					return action1("$multiplicativeOperator", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $multiplicativeOperator 0");
//				if(Parser.MatchToken("$Percent", TokenList, Parser.Cursor) >= 0) {
//					return action2("$multiplicativeOperator", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $multiplicativeOperator 0");
//				return Fail("$multiplicativeOperator", Parser);
//			}
//		}
//		/*
//[$additiveExpression:
//	[
//		<Symbol:$multiplicativeExpression>
//		<Repeat:<Group:<Symbol:$additiveOperator> <Symbol:$multiplicativeExpression> >>
//	]
//]
//		 */
//		class additiveExpressionSyntax extends SyntaxPattern {
//			additiveExpressionSyntax() {
//				super("$additiveExpression");
//			}
//			public SyntaxAcceptor Acceptor0 = new additiveExpressionSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $additiveExpression");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $additiveExpression");
//				if(Parser.Match("$multiplicativeExpression", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					while(true) {
//						if(Parser.Match("$additiveOperator", TokenList) >= 0) {
//							NodeSize = NodeSize + 1;
//							if(Parser.Match("$multiplicativeExpression", TokenList) >= 0) {
//								NodeSize = NodeSize + 1;
//								continue;
//							}
//						}
//						return action0("$additiveExpression", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $additiveExpression 0");
//				return Fail("$additiveExpression", Parser);
//			}
//		}
//		/*
//[$multiplicativeExpression:
//	[
//		<Symbol:$unaryExpression>
//		<Repeat:<Group:<Symbol:$multiplicativeOperator> <Symbol:$unaryExpression> >>
//	]
//]
//		 */
//		class multiplicativeExpressionSyntax extends SyntaxPattern {
//			multiplicativeExpressionSyntax() {
//				super("$multiplicativeExpression");
//			}
//			public SyntaxAcceptor Acceptor0 = new multiplicativeExpressionSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $multiplicativeExpression");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $multiplicativeExpression");
//				if(Parser.Match("$unaryExpression", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					while(true) {
//						if(Parser.Match("$multiplicativeOperator", TokenList) >= 0) {
//							NodeSize = NodeSize + 1;
//							if(Parser.Match("$unaryExpression", TokenList) >= 0) {
//								NodeSize = NodeSize + 1;
//								continue;
//							}
//						}
//						return action0("$multiplicativeExpression", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $multiplicativeExpression 0");
//				return Fail("$multiplicativeExpression", Parser);
//			}
//		}
//		/*
//[$incOperator:
//	[
//		<Symbol:"+">
//		<Symbol:"+">
//	]
//	[
//		<Symbol:"-">
//		<Symbol:"-">
//	]
//]
//		 */
//		class incOperatorSyntax extends SyntaxPattern {
//			incOperatorSyntax() {
//				super("$incOperator");
//			}
//			public SyntaxAcceptor Acceptor0 = new incOperatorSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $incOperator");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor1 = new incOperatorSyntax1();
//			int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $incOperator");
//				Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $incOperator");
//				if(Parser.MatchToken("$Plus", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.MatchToken("$Plus", TokenList, Parser.Cursor) >= 0) {
//						return action0("$incOperator", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $incOperator 0");
//				if(Parser.MatchToken("$Minus", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.MatchToken("$Minus", TokenList, Parser.Cursor) >= 0) {
//						return action1("$incOperator", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $incOperator 0");
//				return Fail("$incOperator", Parser);
//			}
//		}
//		/*
//[$unaryExpression:
//	[
//		<Symbol:$incOperator>
//		<Symbol:$leftHandSideExpression>
//	]
//	[
//		<Symbol:"(">
//		<Symbol:$type>
//		<Symbol:")">
//		<Symbol:$leftHandSideExpression>
//	]
//	[
//		<Symbol:$leftHandSideExpression>
//		<Symbol:$incOperator>
//	]
//	[
//		<Symbol:$leftHandSideExpression>
//	]
//]
//		 */
//		class unaryExpressionSyntax extends SyntaxPattern {
//			unaryExpressionSyntax() {
//				super("$unaryExpression");
//			}
//			public SyntaxAcceptor Acceptor0 = new unaryExpressionSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $unaryExpression");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor1 = new unaryExpressionSyntax1();
//			int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $unaryExpression");
//				Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor2 = new unaryExpressionSyntax2();
//			int action2(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $unaryExpression");
//				Parser.PushThunk(this.Acceptor2, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public SyntaxAcceptor Acceptor3 = new unaryExpressionSyntax3();
//			int action3(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $unaryExpression");
//				Parser.PushThunk(this.Acceptor3, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $unaryExpression");
//				if(Parser.Match("$incOperator", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					if(Parser.Match("$leftHandSideExpression", TokenList) >= 0) {
//						NodeSize = NodeSize + 1;
//						return action0("$unaryExpression", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $unaryExpression 0");
//				if(Parser.MatchToken("$LBrace", TokenList, Parser.Cursor) >= 0) {
//					if(Parser.Match("$type", TokenList) >= 0) {
//						NodeSize = NodeSize + 1;
//						if(Parser.MatchToken("$RBrace", TokenList, Parser.Cursor) >= 0) {
//							if(Parser.Match("$leftHandSideExpression", TokenList) >= 0) {
//								NodeSize = NodeSize + 1;
//								return action1("$unaryExpression", Parser, pos0, NodeSize);
//							}
//						}
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $unaryExpression 0");
//				if(Parser.Match("$leftHandSideExpression", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					if(Parser.Match("$incOperator", TokenList) >= 0) {
//						NodeSize = NodeSize + 1;
//						return action2("$unaryExpression", Parser, pos0, NodeSize);
//					}
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $unaryExpression 0");
//				if(Parser.Match("$leftHandSideExpression", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action3("$unaryExpression", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $unaryExpression 0");
//				return Fail("$unaryExpression", Parser);
//			}
//		}
//		/*
//[$identifier:
//	[
//		<Symbol:$Symbol>
//	]
//]
//		 */
//		class identifierSyntax extends SyntaxPattern {
//			identifierSyntax() {
//				super("$identifier");
//			}
//			public SyntaxAcceptor Acceptor0 = new identifierSyntax0();
//			int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
//				Report("Accept $identifier");
//				Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
//				return Parser.Cursor;
//			}
//			public int Match(PegParser Parser, TokenList TokenList) {
//				int NodeSize = 0;
//				int pos0 = Parser.Cursor;
//				int thunkpos0 = Parser.ThunkPos;
//				int NodeSize0 = NodeSize;
//				Report("Enter $identifier");
//				if(Parser.Match("$Symbol", TokenList) >= 0) {
//					NodeSize = NodeSize + 1;
//					return action0("$identifier", Parser, pos0, NodeSize);
//				}
//				NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $identifier 0");
//				return Fail("$identifier", Parser);
//			}
//		}
