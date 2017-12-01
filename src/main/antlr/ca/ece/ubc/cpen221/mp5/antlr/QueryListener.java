// Generated from Query.g4 by ANTLR 4.7
package ca.ece.ubc.cpen221.mp5.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QueryParser}.
 */
public interface QueryListener extends ParseTreeListener {
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
	/**
	 * Enter a parse tree produced by {@link QueryParser#or}.
	 * @param ctx the parse tree
	 */
	void enterOr(QueryParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#or}.
	 * @param ctx the parse tree
	 */
	void exitOr(QueryParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#and}.
	 * @param ctx the parse tree
	 */
	void enterAnd(QueryParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#and}.
	 * @param ctx the parse tree
	 */
	void exitAnd(QueryParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#ineq}.
	 * @param ctx the parse tree
	 */
	void enterIneq(QueryParser.IneqContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#ineq}.
	 * @param ctx the parse tree
	 */
	void exitIneq(QueryParser.IneqContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#gt}.
	 * @param ctx the parse tree
	 */
	void enterGt(QueryParser.GtContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#gt}.
	 * @param ctx the parse tree
	 */
	void exitGt(QueryParser.GtContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#gte}.
	 * @param ctx the parse tree
	 */
	void enterGte(QueryParser.GteContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#gte}.
	 * @param ctx the parse tree
	 */
	void exitGte(QueryParser.GteContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#lt}.
	 * @param ctx the parse tree
	 */
	void enterLt(QueryParser.LtContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#lt}.
	 * @param ctx the parse tree
	 */
	void exitLt(QueryParser.LtContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#lte}.
	 * @param ctx the parse tree
	 */
	void enterLte(QueryParser.LteContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#lte}.
	 * @param ctx the parse tree
	 */
	void exitLte(QueryParser.LteContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#eq}.
	 * @param ctx the parse tree
	 */
	void enterEq(QueryParser.EqContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#eq}.
	 * @param ctx the parse tree
	 */
	void exitEq(QueryParser.EqContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#in}.
	 * @param ctx the parse tree
	 */
	void enterIn(QueryParser.InContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#in}.
	 * @param ctx the parse tree
	 */
	void exitIn(QueryParser.InContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#category}.
	 * @param ctx the parse tree
	 */
	void enterCategory(QueryParser.CategoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#category}.
	 * @param ctx the parse tree
	 */
	void exitCategory(QueryParser.CategoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(QueryParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(QueryParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#rating}.
	 * @param ctx the parse tree
	 */
	void enterRating(QueryParser.RatingContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#rating}.
	 * @param ctx the parse tree
	 */
	void exitRating(QueryParser.RatingContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#price}.
	 * @param ctx the parse tree
	 */
	void enterPrice(QueryParser.PriceContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#price}.
	 * @param ctx the parse tree
	 */
	void exitPrice(QueryParser.PriceContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(QueryParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(QueryParser.StringContext ctx);
}