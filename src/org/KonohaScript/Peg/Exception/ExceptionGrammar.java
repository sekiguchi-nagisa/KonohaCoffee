package org.KonohaScript.Peg.Exception;

import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.Parser.KonohaGrammar;
import org.KonohaScript.Peg.MiniKonoha.MiniKonohaPegGrammar;

public class ExceptionGrammar extends KonohaGrammar {
	@Override
	public void LoadDefaultSyntax(KonohaNameSpace NameSpace) {
		new MiniKonohaPegGrammar().LoadDefaultSyntax(NameSpace);
		NameSpace.PegParser.MixSyntax(new statementSyntax(), new ExceptionStatementSyntax(), false);
	}
}
