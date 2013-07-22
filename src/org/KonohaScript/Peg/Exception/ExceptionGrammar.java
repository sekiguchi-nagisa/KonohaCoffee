package org.KonohaScript.Peg.Exception;

import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.Parser.KonohaGrammar;
import org.KonohaScript.Peg.MiniKonoha.MiniKonohaPegGrammar;
import org.KonohaScript.PegParser.PegParser;

final class ExceptionStatementGrammar extends KonohaGrammar {
	static boolean	Inited	= false;

	@Override
	public void LoadDefaultSyntax(KonohaNameSpace NameSpace) {
		if(!Inited) {
			Inited = true;
			PegParser Parser = NameSpace.PegParser;
			Parser.AddSyntax(new CatchBlocksSyntax(), false);
			Parser.AddSyntax(new ExceptionStatementSyntax(), false);
			Parser.AddSyntax(new FinallySyntax(), false);
			NameSpace.MergePatternSyntax(new statementSyntax(), new ExceptionStatementSyntax(), false);
		}
	}
}

public class ExceptionGrammar extends KonohaGrammar {
	@Override
	public void LoadDefaultSyntax(KonohaNameSpace NameSpace) {
		new MiniKonohaPegGrammar().LoadDefaultSyntax(NameSpace);
		new ExceptionStatementGrammar().LoadDefaultSyntax(NameSpace);
	}
}
