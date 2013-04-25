package org.KonohaScript.CodeGen;

import org.KonohaScript.KClass;
import org.KonohaScript.KPackage;
import org.KonohaScript.Konoha;
import org.KonohaScript.AST.BlockNode;
import org.KonohaScript.AST.ConstNode;
import org.KonohaScript.AST.IfNode;
import org.KonohaScript.AST.NewNode;
import org.KonohaScript.AST.ReturnNode;
import org.KonohaScript.GrammarSet.MiniKonoha;
import org.junit.Assert;
import org.junit.Test;

public class SimpleVMCodeGenTest {

	@Test
	public void testCompile() {
		MiniKonoha defaultSyntax = new MiniKonoha();
		Konoha kctx = new Konoha(defaultSyntax);
		KPackage pkg = new KPackage(kctx, 0, "SimpleVMTest");
		KClass VoidTy = kctx.AddClass("void", pkg);
		KClass ObjectTy = kctx.AddClass("Object", pkg);
		KClass BooleanTy = kctx.AddClass("booelan", pkg);
		KClass IntTy = kctx.AddClass("int", pkg);
		KClass StringTy = kctx.AddClass("String", pkg);
		{
			SimpleVMCodeGen G = new SimpleVMCodeGen();
			BlockNode Block = new BlockNode(VoidTy, new ReturnNode(IntTy,
					new ConstNode(IntTy, 1)));
			CompiledMethod Mtd = G.Compile(Block);
			Assert.assertTrue(Mtd.CompiledCode instanceof String);
			String Program = (String) Mtd.CompiledCode;
			Assert.assertEquals(Program, "{return 1;}");
		}

		{
			SimpleVMCodeGen G = new SimpleVMCodeGen();
			IfNode Block = new IfNode(VoidTy, new ConstNode(BooleanTy, true),
					new BlockNode(VoidTy, new NewNode(ObjectTy)),
					new BlockNode(VoidTy, new ReturnNode(IntTy, new ConstNode(
							BooleanTy, false))));
			CompiledMethod Mtd = G.Compile(Block);
			Assert.assertTrue(Mtd.CompiledCode instanceof String);
			String Program = (String) Mtd.CompiledCode;
			Assert.assertEquals(Program,
					"if(true) { new Object();} else { return false; }");
		}
	}
}
