// Generated from RDL.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RDLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RDLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RDLParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(RDLParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(RDLParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#dataDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataDecl(RDLParser.DataDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#lsmDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLsmDecl(RDLParser.LsmDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#layerDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLayerDecl(RDLParser.LayerDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#connectStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConnectStmt(RDLParser.ConnectStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#simulateStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimulateStmt(RDLParser.SimulateStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#saveStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSaveStmt(RDLParser.SaveStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(RDLParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(RDLParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#methodDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDecl(RDLParser.MethodDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#methodName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodName(RDLParser.MethodNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(RDLParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#spikeDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpikeDecl(RDLParser.SpikeDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(RDLParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#emitStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmitStmt(RDLParser.EmitStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(RDLParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#augmentedAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAugmentedAssignment(RDLParser.AugmentedAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#incrementStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncrementStmt(RDLParser.IncrementStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#sizeStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSizeStmt(RDLParser.SizeStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#typeStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeStmt(RDLParser.TypeStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#stepsStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStepsStmt(RDLParser.StepsStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#typeValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeValue(RDLParser.TypeValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(RDLParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#comparisonExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpr(RDLParser.ComparisonExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#additiveExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpr(RDLParser.AdditiveExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpr(RDLParser.MultiplicativeExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#unaryExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(RDLParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(RDLParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link RDLParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(RDLParser.NumberContext ctx);
}