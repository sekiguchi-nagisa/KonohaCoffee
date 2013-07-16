package org.KonohaScript.Peg.KonohaClass;

import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.Parser.KonohaGrammar;
import org.KonohaScript.Peg.MiniKonoha.MiniKonohaPegGrammar;

public class KonohaClassGrammar extends KonohaGrammar {
	@Override
	public void LoadDefaultSyntax(KonohaNameSpace NameSpace) {
		new MiniKonohaPegGrammar().LoadDefaultSyntax(NameSpace);
		NameSpace.PegParser.MixSyntax(new TopLevelDefinitionSyntax(), new ClassDefinitionSyntax(), false);
	}
}
