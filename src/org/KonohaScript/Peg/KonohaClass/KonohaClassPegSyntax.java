package org.KonohaScript.Peg.KonohaClass;

import org.KonohaScript.KLib.TokenList;
import org.KonohaScript.PegParser.PegParser;
import org.KonohaScript.PegParser.SyntaxAcceptor;
import org.KonohaScript.PegParser.SyntaxPattern;

/*
 [$ClassDefinition:
 [
 <Symbol:"class">
 <Symbol:$#Symbol>
 <Symbol:$#block>
 ]
 [
 <Symbol:"class">
 <Symbol:$#Symbol>
 <Symbol:"extends">
 <Symbol:$#type>
 ]
 ]
 */
class ClassDefinitionSyntax extends SyntaxPattern {
	ClassDefinitionSyntax() {
		super("$ClassDefinition");
	}

	public SyntaxAcceptor	Acceptor0	= new ClassDefinitionSyntax0();

	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
		this.Report("Accept $ClassDefinition");
		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
		return Parser.Cursor;
	}

	public SyntaxAcceptor	Acceptor1	= new ClassDefinitionSyntax1();

	int action1(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
		this.Report("Accept $ClassDefinition");
		Parser.PushThunk(this.Acceptor1, BeginIdx, NodeSize);
		return Parser.Cursor;
	}

	@Override
	public int Match(PegParser Parser, TokenList TokenList) {
		int NodeSize = 0;
		int pos0 = Parser.Cursor;
		int thunkpos0 = Parser.ThunkPos;
		int NodeSize0 = NodeSize;
		this.Report("Enter $ClassDefinition");
		if(Parser.MatchToken("class", TokenList, Parser.Cursor) >= 0) {
			if(Parser.Match("$Symbol", TokenList) >= 0) {
				NodeSize = NodeSize + 1;
				if(Parser.Match("$block", TokenList) >= 0) {
					NodeSize = NodeSize + 1;
					return this.action0("$ClassDefinition", Parser, pos0, NodeSize);
				}
			}
		}
		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $ClassDefinition 0");
		if(Parser.MatchToken("class", TokenList, Parser.Cursor) >= 0) {
			if(Parser.Match("$Symbol", TokenList) >= 0) {
				NodeSize = NodeSize + 1;
				if(Parser.MatchToken("extends", TokenList, Parser.Cursor) >= 0) {
					if(Parser.Match("$type", TokenList) >= 0) {
						NodeSize = NodeSize + 1;
						return this.action1("$ClassDefinition", Parser, pos0, NodeSize);
					}
				}
			}
		}
		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $ClassDefinition 0");
		return this.Fail("$ClassDefinition", Parser);
	}
}

/*
 * [$TopLevelDefinition: [ <Symbol:$ClassDefinition> ] ]
 */
class TopLevelDefinitionSyntax extends SyntaxPattern {
	TopLevelDefinitionSyntax() {
		super("$TopLevelDefinition");
	}

	public SyntaxAcceptor	Acceptor0	= new TopLevelDefinitionSyntax0();

	int action0(String SyntaxName, PegParser Parser, int BeginIdx, int NodeSize) {
		this.Report("Accept $TopLevelDefinition");
		Parser.PushThunk(this.Acceptor0, BeginIdx, NodeSize);
		return Parser.Cursor;
	}

	@Override
	public int Match(PegParser Parser, TokenList TokenList) {
		int NodeSize = 0;
		int pos0 = Parser.Cursor;
		int thunkpos0 = Parser.ThunkPos;
		int NodeSize0 = NodeSize;
		this.Report("Enter $TopLevelDefinition");
		if(Parser.Match("$ClassDefinition", TokenList) >= 0) {
			NodeSize = NodeSize + 1;
			return this.action0("$TopLevelDefinition", Parser, pos0, NodeSize);
		}
		NodeSize = this.BackTrack(Parser, pos0, thunkpos0, NodeSize0, "BackTrack $TopLevelDefinition 0");
		return this.Fail("$TopLevelDefinition", Parser);
	}
}
