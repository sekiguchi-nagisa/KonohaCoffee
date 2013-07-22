package org.KonohaScript.Peg.Exception;

import org.KonohaScript.KLib.TokenList;
import org.KonohaScript.PegParser.PegParser;
import org.KonohaScript.PegParser.SyntaxAcceptor;
import org.KonohaScript.PegParser.SyntaxPattern;

/*
 [$ExceptionStatement:
 [
 <Symbol:"try">
 <Symbol:$#block>
 <Repeat:<Symbol:$CatchBlocks>>
 <Symbol:$Finally>
 ]
 [
 <Symbol:"try">
 <Symbol:$#block>
 <Symbol:$CatchBlocks>
 <Repeat:<Symbol:$CatchBlocks>>
 ]
 [
 <Symbol:"throw">
 <Symbol:$#leftHandSideExpression>
 ]
 ]
 */
class ExceptionStatementSyntax extends SyntaxPattern {
	ExceptionStatementSyntax() {
		super("$ExceptionStatement");
	}

	public SyntaxAcceptor	Acceptor0	= new ExceptionStatementSyntax0();

	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
		this.Report("Accept $ExceptionStatement");
		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
		return Parser.Cursor;
	}

	public SyntaxAcceptor	Acceptor1	= new ExceptionStatementSyntax1();

	int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
		this.Report("Accept $ExceptionStatement");
		Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
		return Parser.Cursor;
	}

	public SyntaxAcceptor	Acceptor2	= new ExceptionStatementSyntax2();

	int action2(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
		this.Report("Accept $ExceptionStatement");
		Parser.PushThunk(this.Acceptor2, BeginIdx, NodeSize);
		return Parser.Cursor;
	}

	@Override
	public int Match(PegParser Parser, TokenList TokenList) {
		int NodeSize = 0;
		int pos0 = Parser.Cursor;
		int thunkpos0 = Parser.ThunkPos;
		int NodeSize0 = NodeSize;
		this.Report("Enter $ExceptionStatement");
		if(Parser.MatchToken("try", TokenList, Parser.Cursor) >= 0) {
			if(Parser.Match("$block", TokenList) >= 0) {
				NodeSize = NodeSize + 1;
				while(true) {
					if(Parser.Match("$CatchBlocks", TokenList) >= 0) {
						NodeSize = NodeSize + 1;
						continue;
					}
					if(Parser.Match("$Finally", TokenList) >= 0) {
						NodeSize = NodeSize + 1;
						return this.action0("$ExceptionStatement", Parser, pos0, NodeSize);
					}
				}
			}
		}
		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $ExceptionStatement 0");
		if(Parser.MatchToken("try", TokenList, Parser.Cursor) >= 0) {
			if(Parser.Match("$block", TokenList) >= 0) {
				NodeSize = NodeSize + 1;
				if(Parser.Match("$CatchBlocks", TokenList) >= 0) {
					NodeSize = NodeSize + 1;
					while(true) {
						if(Parser.Match("$CatchBlocks", TokenList) >= 0) {
							NodeSize = NodeSize + 1;
							continue;
						}
						return this.action1("$ExceptionStatement", Parser, pos0, NodeSize);
					}
				}
			}
		}
		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $ExceptionStatement 0");
		if(Parser.MatchToken("throw", TokenList, Parser.Cursor) >= 0) {
			if(Parser.Match("$leftHandSideExpression", TokenList) >= 0) {
				NodeSize = NodeSize + 1;
				return this.action2("$ExceptionStatement", Parser, pos0, NodeSize);
			}
		}
		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $ExceptionStatement 0");
		return this.Fail("$ExceptionStatement", Parser);
	}
}

/*
 * [$CatchBlocks: [ <Symbol:"catch"> <Symbol:"("> <Symbol:$#type>
 * <Symbol:$#Symbol> <Symbol:")"> <Symbol:$#block> ] ]
 */
class CatchBlocksSyntax extends SyntaxPattern {
	CatchBlocksSyntax() {
		super("$CatchBlocks");
	}

	public SyntaxAcceptor	Acceptor0	= new CatchBlocksSyntax0();

	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
		this.Report("Accept $CatchBlocks");
		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
		return Parser.Cursor;
	}

	@Override
	public int Match(PegParser Parser, TokenList TokenList) {
		int NodeSize = 0;
		int pos0 = Parser.Cursor;
		int thunkpos0 = Parser.ThunkPos;
		int NodeSize0 = NodeSize;
		this.Report("Enter $CatchBlocks");
		if(Parser.MatchToken("catch", TokenList, Parser.Cursor) >= 0) {
			if(Parser.MatchToken("$LBrace", TokenList, Parser.Cursor) >= 0) {
				if(Parser.Match("$type", TokenList) >= 0) {
					NodeSize = NodeSize + 1;
					if(Parser.Match("$Symbol", TokenList) >= 0) {
						NodeSize = NodeSize + 1;
						if(Parser.MatchToken("$RBrace", TokenList, Parser.Cursor) >= 0) {
							if(Parser.Match("$block", TokenList) >= 0) {
								NodeSize = NodeSize + 1;
								return this.action0("$CatchBlocks", Parser, pos0, NodeSize);
							}
						}
					}
				}
			}
		}
		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $CatchBlocks 0");
		return this.Fail("$CatchBlocks", Parser);
	}
}

/*
 * [$Finally: [ <Symbol:"finally"> <Symbol:$#block> ] ]
 */
class FinallySyntax extends SyntaxPattern {
	FinallySyntax() {
		super("$Finally");
	}

	public SyntaxAcceptor	Acceptor0	= new FinallySyntax0();

	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
		this.Report("Accept $Finally");
		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
		return Parser.Cursor;
	}

	@Override
	public int Match(PegParser Parser, TokenList TokenList) {
		int NodeSize = 0;
		int pos0 = Parser.Cursor;
		int thunkpos0 = Parser.ThunkPos;
		int NodeSize0 = NodeSize;
		this.Report("Enter $Finally");
		if(Parser.MatchToken("finally", TokenList, Parser.Cursor) >= 0) {
			if(Parser.Match("$block", TokenList) >= 0) {
				NodeSize = NodeSize + 1;
				return this.action0("$Finally", Parser, pos0, NodeSize);
			}
		}
		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $Finally 0");
		return this.Fail("$Finally", Parser);
	}
}

/*
 * [$statement: [ <Symbol:$ExceptionStatement> ] ]
 */
class statementSyntax extends SyntaxPattern {
	statementSyntax() {
		super("$statement");
	}

	public SyntaxAcceptor	Acceptor0	= new statementSyntax0();

	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
		this.Report("Accept $statement");
		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
		return Parser.Cursor;
	}

	@Override
	public int Match(PegParser Parser, TokenList TokenList) {
		int NodeSize = 0;
		int pos0 = Parser.Cursor;
		int thunkpos0 = Parser.ThunkPos;
		int NodeSize0 = NodeSize;
		this.Report("Enter $statement");
		if(Parser.Match("$ExceptionStatement", TokenList) >= 0) {
			NodeSize = NodeSize + 1;
			return this.action0("$statement", Parser, pos0, NodeSize);
		}
		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $statement 0");
		return this.Fail("$statement", Parser);
	}
}
