// Generated from Query.g4 by ANTLR 4.7
package ca.ece.ubc.cpen221.mp5;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QueryParser}.
 */
public interface QueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QueryParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(QueryParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(QueryParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(QueryParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(QueryParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(QueryParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(QueryParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(QueryParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(QueryParser.AtomContext ctx);
}