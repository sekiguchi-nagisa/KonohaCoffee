package org.KonohaScript.CodeGen;

import org.KonohaScript.KonohaType;

public class Local {
	public String		Name;
	public KonohaType	TypeInfo;
	public int			Index;

	public Local(int Index, KonohaType TypeInfo, String Name) {
		this.Index = Index;
		this.TypeInfo = TypeInfo;
		this.Name = Name;
	}
}
