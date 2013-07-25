package org.KonohaScript.Peg.KonohaClass;

import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.Parser.KonohaGrammar;
import org.KonohaScript.Peg.MiniKonoha.MiniKonohaPegGrammar;
import org.KonohaScript.PegParser.PegParser;

final class ClassDefinitionGrammar extends KonohaGrammar {
	static boolean	Inited	= false;

	@Override
	public void LoadDefaultSyntax(KonohaNameSpace NameSpace) {
		if(!Inited) {
			Inited = true;
			PegParser Parser = NameSpace.PegParser;

			Parser.AddSyntax(new ClassDefinitionSyntax(), false);
			NameSpace.MergePatternSyntax(new TopLevelDefinitionSyntax(), new ClassDefinitionSyntax(), false);
		}
	}
}

public final class KonohaClassPegGrammar extends KonohaGrammar {
	@Override
	public void LoadDefaultSyntax(KonohaNameSpace NameSpace) {
		new MiniKonohaPegGrammar().LoadDefaultSyntax(NameSpace);
		new ClassDefinitionGrammar().LoadDefaultSyntax(NameSpace);
	}
}
