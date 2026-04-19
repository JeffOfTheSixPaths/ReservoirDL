// Generated from RDL.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RDLParser}.
 */
public interface RDLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RDLParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(RDLParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(RDLParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(RDLParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(RDLParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#dataDecl}.
	 * @param ctx the parse tree
	 */
	void enterDataDecl(RDLParser.DataDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#dataDecl}.
	 * @param ctx the parse tree
	 */
	void exitDataDecl(RDLParser.DataDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#lsmDecl}.
	 * @param ctx the parse tree
	 */
	void enterLsmDecl(RDLParser.LsmDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#lsmDecl}.
	 * @param ctx the parse tree
	 */
	void exitLsmDecl(RDLParser.LsmDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#layerDecl}.
	 * @param ctx the parse tree
	 */
	void enterLayerDecl(RDLParser.LayerDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#layerDecl}.
	 * @param ctx the parse tree
	 */
	void exitLayerDecl(RDLParser.LayerDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#connectStmt}.
	 * @param ctx the parse tree
	 */
	void enterConnectStmt(RDLParser.ConnectStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#connectStmt}.
	 * @param ctx the parse tree
	 */
	void exitConnectStmt(RDLParser.ConnectStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#simulateStmt}.
	 * @param ctx the parse tree
	 */
	void enterSimulateStmt(RDLParser.SimulateStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#simulateStmt}.
	 * @param ctx the parse tree
	 */
	void exitSimulateStmt(RDLParser.SimulateStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#saveStmt}.
	 * @param ctx the parse tree
	 */
	void enterSaveStmt(RDLParser.SaveStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#saveStmt}.
	 * @param ctx the parse tree
	 */
	void exitSaveStmt(RDLParser.SaveStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(RDLParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(RDLParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(RDLParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(RDLParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#methodDecl}.
	 * @param ctx the parse tree
	 */
	void enterMethodDecl(RDLParser.MethodDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#methodDecl}.
	 * @param ctx the parse tree
	 */
	void exitMethodDecl(RDLParser.MethodDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#methodName}.
	 * @param ctx the parse tree
	 */
	void enterMethodName(RDLParser.MethodNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#methodName}.
	 * @param ctx the parse tree
	 */
	void exitMethodName(RDLParser.MethodNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#paramList}.
	 * @param ctx the parse tree
	 */
	void enterParamList(RDLParser.ParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#paramList}.
	 * @param ctx the parse tree
	 */
	void exitParamList(RDLParser.ParamListContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#spikeDecl}.
	 * @param ctx the parse tree
	 */
	void enterSpikeDecl(RDLParser.SpikeDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#spikeDecl}.
	 * @param ctx the parse tree
	 */
	void exitSpikeDecl(RDLParser.SpikeDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(RDLParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(RDLParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#emitStmt}.
	 * @param ctx the parse tree
	 */
	void enterEmitStmt(RDLParser.EmitStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#emitStmt}.
	 * @param ctx the parse tree
	 */
	void exitEmitStmt(RDLParser.EmitStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(RDLParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(RDLParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#augmentedAssignment}.
	 * @param ctx the parse tree
	 */
	void enterAugmentedAssignment(RDLParser.AugmentedAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#augmentedAssignment}.
	 * @param ctx the parse tree
	 */
	void exitAugmentedAssignment(RDLParser.AugmentedAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#incrementStmt}.
	 * @param ctx the parse tree
	 */
	void enterIncrementStmt(RDLParser.IncrementStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#incrementStmt}.
	 * @param ctx the parse tree
	 */
	void exitIncrementStmt(RDLParser.IncrementStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#sizeStmt}.
	 * @param ctx the parse tree
	 */
	void enterSizeStmt(RDLParser.SizeStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#sizeStmt}.
	 * @param ctx the parse tree
	 */
	void exitSizeStmt(RDLParser.SizeStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#typeStmt}.
	 * @param ctx the parse tree
	 */
	void enterTypeStmt(RDLParser.TypeStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#typeStmt}.
	 * @param ctx the parse tree
	 */
	void exitTypeStmt(RDLParser.TypeStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#stepsStmt}.
	 * @param ctx the parse tree
	 */
	void enterStepsStmt(RDLParser.StepsStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#stepsStmt}.
	 * @param ctx the parse tree
	 */
	void exitStepsStmt(RDLParser.StepsStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#typeValue}.
	 * @param ctx the parse tree
	 */
	void enterTypeValue(RDLParser.TypeValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#typeValue}.
	 * @param ctx the parse tree
	 */
	void exitTypeValue(RDLParser.TypeValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(RDLParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(RDLParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#comparisonExpr}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpr(RDLParser.ComparisonExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#comparisonExpr}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpr(RDLParser.ComparisonExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#additiveExpr}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpr(RDLParser.AdditiveExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#additiveExpr}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpr(RDLParser.AdditiveExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpr(RDLParser.MultiplicativeExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpr(RDLParser.MultiplicativeExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(RDLParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(RDLParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(RDLParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(RDLParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link RDLParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(RDLParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link RDLParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(RDLParser.NumberContext ctx);
}