package org.KonohaScript.PegParser;

import org.KonohaScript.KonohaNameSpace;
import org.KonohaScript.KLib.KonohaArray;
import org.KonohaScript.KLib.KonohaMap;
import org.KonohaScript.KLib.TokenList;
import org.KonohaScript.Parser.KonohaSyntax;
import org.KonohaScript.Parser.KonohaToken;
import org.KonohaScript.Parser.UntypedNode;

public class PegParser {
	public int		Cursor;
	public int		ThunkPos;
	public int		EndIdx;

	KonohaArray		EntryPoints;
	KonohaMap		SyntaxTable;
	SyntaxPattern	RootSyntax;

	KonohaArray		ThunkObjects;
	KonohaArray		UNodeStack;
	KonohaArray		ThunkRangeBegins;
	KonohaArray		ThunkRangeEnd;
	KonohaArray		ThunkNodeSizes;
	KonohaNameSpace	NameSpace;
	public int		ParseOption;

	public PegParser(KonohaNameSpace NameSpace) {
		super();
		this.EntryPoints = new KonohaArray();
		this.SyntaxTable = new KonohaMap();
		this.ThunkObjects = new KonohaArray();
		this.ThunkRangeBegins = new KonohaArray();
		this.ThunkRangeEnd = new KonohaArray();
		this.ThunkNodeSizes = new KonohaArray();
		this.UNodeStack = new KonohaArray();
		this.NameSpace = NameSpace;
	}

	public UntypedNode Parse(KonohaNameSpace ns, UntypedNode PrevNode, TokenList TokenList, int BeginIdx, int EndIdx,
			int ParseOption) {
		Integer EndIdxRef = 0;
		UntypedNode UNode = this.Parse(TokenList, BeginIdx, EndIdx, EndIdxRef);
		return UNode;
	}

	public int MatchPattern(String SyntaxName, UntypedNode[] Ref, TokenList TokenList, int BeginIdx, int EndIdx) {
		this.Init(BeginIdx, EndIdx);
		int NextIdx = this.Match(SyntaxName, TokenList);
		if(NextIdx > 0) {
			UntypedNode ret = null;
			NextIdx = this.ConstructSyntaxTree(TokenList, BeginIdx, EndIdx);
			if(this.UNodeStack.size() > 0) {
				ret = (UntypedNode) this.Pop();
			}
			Ref[0] = ret;
		}
		return NextIdx;
	}

	void Init(int BeginIdx, int EndIdx) {
		this.Cursor = BeginIdx;
		this.EndIdx = EndIdx;
		this.ThunkPos = 0;
		this.ThunkObjects.clear();
		this.ThunkRangeBegins.clear();
		this.ThunkRangeEnd.clear();
		this.ThunkNodeSizes.clear();
		this.UNodeStack.clear();
	}

	public int Match(String SyntaxName, TokenList TokenList) {
		Object Syntax = this.SyntaxTable.get(SyntaxName);
		if(Syntax != null) {
			if(Syntax instanceof KonohaArray) {
				KonohaArray List = (KonohaArray) Syntax;
				for(int i = 0; i < List.size(); i++) {
					SyntaxPattern Syn = (SyntaxPattern) List.get(i);
					System.out.println(Syn.Name);
					int Index = Syn.Match(this, TokenList);
					if(Index >= 0) {
						return Index;
					}
				}
			} else {
				SyntaxPattern Syn = (SyntaxPattern) Syntax;
				return Syn.Match(this, TokenList);
			}
		}
		return -1;
	}

	public int MatchToken(String SyntaxName, TokenList TokenList, int Index) {
		if(Index < this.EndIdx) {
			KonohaToken Token = TokenList.get(Index);
			KonohaSyntax Syntax = Token.ResolvedSyntax;
			if(Syntax == null) {
				//System.out.println("Token:" + Token.ParsedText + "// Expected:" + SyntaxName);
			}
			//System.out.println("Token:" + Token.ParsedText + "// Expected:" + SyntaxName);
			if(SyntaxName.equals(Syntax.SyntaxName)) {
				this.Cursor = this.Cursor + 1;
				return this.Cursor;
			}
			if(Syntax.SyntaxName.equals("$Symbol") && Token.ParsedText.equals(SyntaxName)) {
				this.Cursor = this.Cursor + 1;
				return this.Cursor;
			}
		}
		return -1;
	}

	void SetRootSyntax(SyntaxPattern Syntax) {
		this.RootSyntax = Syntax;
		this.AddSyntax(null, this.RootSyntax, true);
	}

	int MatchSyntax(TokenList TokenList, int BeginIdx, int EndIdx) {
		assert (BeginIdx >= 0 && BeginIdx <= EndIdx);
		this.Cursor = BeginIdx;
		this.EndIdx = EndIdx;
		int Pos = this.RootSyntax.Match(this, TokenList);
		if(Pos != BeginIdx) {
			//System.out.println("BeginIdx=" + BeginIdx + "CurrentIdx=" + Pos + ", EndIdx=" + this.EndIdx);
			return this.ConstructSyntaxTree(TokenList, BeginIdx, EndIdx);
		}
		for(int i = 0; i < this.EntryPoints.size(); i++) {
			SyntaxPattern Syntax = (SyntaxPattern) this.EntryPoints.get(i);
			if(Syntax == this.RootSyntax) {
				continue;
			}
			Pos = this.RootSyntax.Match(this, TokenList);
			if(Pos != BeginIdx) {
				return this.ConstructSyntaxTree(TokenList, BeginIdx, EndIdx);
			}
		}
		return -1;
	}

	int ConstructSyntaxTree(TokenList TokenList, int BeginIdx, int EndIdx) {
		int Pos = BeginIdx;
		for(int i = 0; i < this.ThunkPos; i++) {
			SyntaxAcceptor Action = (SyntaxAcceptor) this.ThunkObjects.get(i);
			int Begin = ((Integer) this.ThunkRangeBegins.get(i)).intValue();
			int End = ((Integer) this.ThunkRangeEnd.get(i)).intValue();
			int NodeSize = ((Integer) this.ThunkNodeSizes.get(i)).intValue();
			Pos = Action.Parse(this, TokenList, Begin, End, NodeSize);
		}
		return Pos;
	}

	public UntypedNode Parse(TokenList TokenList, int BeginIdx, int EndIdx, Integer EndIdxRef) {
		this.Init(BeginIdx, EndIdx);
		this.EndIdx = this.MatchSyntax(TokenList, BeginIdx, EndIdx);
		if(EndIdx == -1) {
			return null;
		}
		UntypedNode ret = null;
		if(this.UNodeStack.size() > 0) {
			ret = (UntypedNode) this.Pop();
		}
		EndIdxRef = EndIdx;
		return ret;
	}

	boolean AlreadyRegistered(SyntaxPattern Syntax) {
		return this.SyntaxTable.get(Syntax.Name) != null;
	}

	public void AddSyntax(SyntaxPattern NewGrammer, boolean TopLevel) {
		this.AddSyntax(null, NewGrammer, TopLevel);
	}

	public void AddSyntax(SyntaxPattern ParentSyntax, SyntaxPattern Syntax, boolean TopLevelSyntax) {
		if(TopLevelSyntax) {
			this.RootSyntax = Syntax;
			this.EntryPoints.add(Syntax);
		}
		if(!this.AlreadyRegistered(Syntax)) {
			this.SyntaxTable.put(Syntax.Name, Syntax);
			if(!(Syntax instanceof KonohaSyntaxPattern)) {
				this.NameSpace.AddSyntax(Syntax.Name, 0, Syntax, "UNUSED", "PegParser");
			}
			Syntax.Init(this.NameSpace, this);
		} else {
			this.MergeSyntax(Syntax);
		}
	}

	void MergeSyntax(SyntaxPattern NewSyntax) {
		Object Entry = this.SyntaxTable.get(NewSyntax.Name);

		if(Entry instanceof KonohaArray) {
			KonohaArray List = (KonohaArray) Entry;
			for(int i = 0; i < List.size(); i++) {
				SyntaxPattern Syntax = (SyntaxPattern) List.get(i);
				if(Syntax.equals(NewSyntax)) {
					return;
				}
			}
			List.add(NewSyntax);
		} else {
			SyntaxPattern OldSyntax = (SyntaxPattern) Entry;
			if(OldSyntax.equals(NewSyntax)) {
				return;
			}

			KonohaArray List = new KonohaArray();
			List.add(Entry);
			List.add(NewSyntax);
			this.SyntaxTable.put(NewSyntax.Name, List);
		}
		NewSyntax.Init(this.NameSpace, this);
	}

	public void MixSyntax(SyntaxPattern ParentSyntax, SyntaxPattern Syntax, boolean TopLevelSyntax) {
		if(this.AlreadyRegistered(ParentSyntax)) {
			this.MergeSyntax(ParentSyntax);
		} else {
			this.AddSyntax(ParentSyntax, Syntax, TopLevelSyntax);
		}
	}

	//	public void DumpSyntax() {
	//		String[] keys = this.SyntaxTable.keys();
	//		for(int i = 0; i < this.SyntaxTable.size(); i++) {
	//			SyntaxTemplate syntax = (SyntaxTemplate) this.SyntaxTable.get(keys[i]);
	//			System.out.println(syntax.Name);
	//		}
	//	}

	public void PushThunk(SyntaxAcceptor Action, int BeginIdx, int NodeSize) {
		int thunkpos = this.ThunkPos;
		KonohaArray Actions = this.ThunkObjects;
		KonohaArray BeginIndexes = this.ThunkRangeBegins;
		KonohaArray EndIndexes = this.ThunkRangeEnd;
		KonohaArray NodeIndexes = this.ThunkNodeSizes;
		while(BeginIndexes.size() <= thunkpos) {
			Actions.add(null);
			BeginIndexes.add(0);
			EndIndexes.add(0);
			NodeIndexes.add(0);
		}
		Actions.set(thunkpos, Action);
		BeginIndexes.set(thunkpos, BeginIdx);
		EndIndexes.set(thunkpos, this.Cursor);
		NodeIndexes.set(thunkpos, NodeSize);

		this.ThunkPos = thunkpos + 1;
	}

	public void Push(Object UNode) {
		this.UNodeStack.add(UNode);
	}

	public Object Pop() {
		assert (this.UNodeStack.size() > 0);
		return this.UNodeStack.remove(this.UNodeStack.size() - 1);
	}

	public Object Get(int Index, int NodeSize) {
		return this.UNodeStack.get(this.UNodeStack.size() - NodeSize + Index);
	}

	public void ReAssign(int NodeSize, Object Value) {
		while(NodeSize > 0) {
			this.Pop();
			NodeSize = NodeSize - 1;
		}
		this.Push(Value);
	}
}
