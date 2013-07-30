//package org.KonohaScript.Peg.CSyntax;
//import org.KonohaScript.PegParser.PegParser;
//
//final class SourceCodeGrammar extends KonohaGrammar {
//  @Override
//  public void LoadDefaultSyntax(KonohaNameSpace NameSpace) {
//    Parser.AddSyntax(new additiveOperatorSyntax(), false);
//    Parser.AddSyntax(new logicalOrExpressionSyntax(), false);
//    Parser.AddSyntax(new stringLiteralSyntax(), false);
//    Parser.AddSyntax(new EmptyStatementSyntax(), false);
//    Parser.AddSyntax(new returnStatementSyntax(), false);
//    Parser.AddSyntax(new whileStatementSyntax(), false);
//    Parser.AddSyntax(new SymbolSyntax(), false);
//    Parser.AddSyntax(new continueStatementSyntax(), false);
//    Parser.AddSyntax(new identifierSyntax(), false);
//    Parser.AddSyntax(new TopLevelStatementSyntax(), false);
//    Parser.AddSyntax(new ParameterListSyntax(), false);
//    Parser.AddSyntax(new TypeParamaterSyntax(), false);
//    Parser.AddSyntax(new typeSyntax(), false);
//    Parser.AddSyntax(new ParamDeclSyntax(), false);
//    Parser.AddSyntax(new breakStatementSyntax(), false);
//    Parser.AddSyntax(new statementsSyntax(), false);
//    Parser.AddSyntax(new ParameterSyntax(), false);
//    Parser.AddSyntax(new relationExpressionSyntax(), false);
//    Parser.AddSyntax(new TypeParamDeclsSyntax(), false);
//    Parser.AddSyntax(new ParametersSyntax(), false);
//    Parser.AddSyntax(new primarySyntax(), false);
//    Parser.AddSyntax(new functionDefinitionSyntax(), false);
//    Parser.AddSyntax(new incOperatorSyntax(), false);
//    Parser.AddSyntax(new logicalAndExpressionSyntax(), false);
//    Parser.AddSyntax(new TypeParamDeclSyntax(), false);
//    Parser.AddSyntax(new TypeSyntax(), false);
//    Parser.AddSyntax(new multiplicativeOperatorSyntax(), false);
//    Parser.AddSyntax(new functionSignatureSyntax(), false);
//    Parser.AddSyntax(new expressionStatementSyntax(), false);
//    Parser.AddSyntax(new functionTypeSyntax(), false);
//    Parser.AddSyntax(new ifStatementSyntax(), false);
//    Parser.AddSyntax(new literalSyntax(), false);
//    Parser.AddSyntax(new arrayTypeSyntax(), false);
//    Parser.AddSyntax(new variableSyntax(), false);
//    Parser.AddSyntax(new unaryExpressionSyntax(), false);
//    Parser.AddSyntax(new newExpressionSyntax(), false);
//    Parser.AddSyntax(new TopLevelDefinitionSyntax(), false);
//    Parser.AddSyntax(new ParamDeclListSyntax(), false);
//    Parser.AddSyntax(new selectorSyntax(), false);
//    Parser.AddSyntax(new variableDeclarationSyntax(), false);
//    Parser.AddSyntax(new callExpressionSyntax(), false);
//    Parser.AddSyntax(new expressionSyntax(), false);
//    Parser.AddSyntax(new memberExpressionSyntax(), false);
//    Parser.AddSyntax(new ParamDeclsSyntax(), false);
//    Parser.AddSyntax(new blockSyntax(), false);
//    Parser.AddSyntax(new multiplicativeExpressionSyntax(), false);
//    Parser.AddSyntax(new additiveExpressionSyntax(), false);
//    Parser.AddSyntax(new leftHandSideExpressionSyntax(), false);
//    Parser.AddSyntax(new statementSyntax(), false);
//    Parser.AddSyntax(new relationOperatorSyntax(), false);
//    Parser.AddSyntax(new EQSyntax(), false);
//    Parser.AddSyntax(new intLiteralSyntax(), false);
//    Parser.AddSyntax(new functionBodySyntax(), false);
//    NameSpace.AddPatternSyntax(null, new SourceCodeSyntax(), true);
//  }
//}
