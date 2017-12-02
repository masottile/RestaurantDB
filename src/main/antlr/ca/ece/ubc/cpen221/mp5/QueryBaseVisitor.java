// Generated from Query.g4 by ANTLR 4.7
package ca.ece.ubc.cpen221.mp5;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.RuleNode;

import ca.ece.ubc.cpen221.mp5.QueryParser.*;
import ca.ece.ubc.cpen221.mp5.datatypes.YelpRestaurant;

/**
 * This class provides an empty implementation of {@link QueryVisitor}, which
 * can be extended to create a visitor which only needs to handle a subset of
 * the available methods.
 *
 * @param <T>
 *            The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
public class QueryBaseVisitor extends AbstractParseTreeVisitor<Set<YelpRestaurant>>
		implements QueryVisitor<Set<YelpRestaurant>> {

	private YelpDB yelpDB;

	public QueryBaseVisitor(YelpDB yelpDB) {
		this.yelpDB = yelpDB;
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public Set<YelpRestaurant> visitQuery(QueryParser.QueryContext ctx) {
		System.out.println("query: " + ctx.getText());
		
		if(ctx.getChild(0) instanceof QueryParser.OrExprContext)
			return visitOrExpr((OrExprContext) ctx.getChild(0));
		else if (ctx.getChild(0) instanceof QueryParser.AndExprContext)
			return visitAndExpr((AndExprContext) ctx.getChild(0));
		else
			return visitAtom((AtomContext) ctx.getChild(0));
	}

	/**
	 * Computes the union of all the andExpr children
	 */
	@Override
	public Set<YelpRestaurant> visitOrExpr(QueryParser.OrExprContext ctx) {
		System.out.println("orExpr: " + ctx.getText());
		Set<YelpRestaurant> retSet = new HashSet<YelpRestaurant>();
		for (int i = 0; i < ctx.getChildCount(); i += 2)
			retSet.addAll(visitAndExpr((QueryParser.AndExprContext) ctx.getChild(i)));

		return retSet;
	}

	/**
	 * Computes the intersection of all the atom children
	 */
	@Override
	public Set<YelpRestaurant> visitAndExpr(QueryParser.AndExprContext ctx) {
		System.out.println("andExpr: " + ctx.getText());
		Set<YelpRestaurant> retSet = yelpDB.getRestaurants();
		for (int i = 0; i < ctx.getChildCount(); i += 2)
			retSet.retainAll(visitAtom((QueryParser.AtomContext) ctx.getChild(i)));

		return retSet;
	}

	/**
	 * passes the operation onto the appropriate child
	 */
	@Override
	public Set<YelpRestaurant> visitAtom(QueryParser.AtomContext ctx) {
		System.out.println("atom: " + ctx.getText());
		if (ctx.getChild(0) instanceof InContext)
			return visitIn((InContext) ctx.getChild(0));
		else if (ctx.getChild(0) instanceof CategoryContext)
			return visitCategory((CategoryContext) ctx.getChild(0));
		else if (ctx.getChild(0) instanceof NameContext)
			return visitName((NameContext) ctx.getChild(0));
		else if (ctx.getChild(0) instanceof PriceContext)
			return visitPrice((PriceContext) ctx.getChild(0));
		else if (ctx.getChild(0) instanceof RatingContext)
			return visitRating((RatingContext) ctx.getChild(0));
		else
			return visitOrExpr((OrExprContext) ctx.getChild(1));
	}

	/**
	 * 
	 */
	@Override
	public Set<YelpRestaurant> visitIn(InContext ctx) {
		System.out.println("in: " + ctx.getText());
		String text = ctx.getChild(1).getText();
		System.out.println(text);
		String testText = text.substring(1, text.length() - 1);
		System.out.println(testText);

		Stream<YelpRestaurant> retStream = yelpDB.getRestaurants().stream()
				.filter(yr -> new HashSet<String>(Arrays.asList(yr.getNeighborhoods())).contains(testText));
		Set<YelpRestaurant> retSet = retStream.collect(Collectors.toCollection(HashSet::new));
		return retSet;
	}

	@Override
	public Set<YelpRestaurant> visitCategory(CategoryContext ctx) {
		System.out.println("category: " + ctx.getText());
		String text = ctx.getChild(1).getText();
		System.out.println(text);
		String testText = text.substring(1, text.length() - 1);
		System.out.println(testText);
		
		Stream<YelpRestaurant> retStream = yelpDB.getRestaurants().stream()
						.filter(yr -> new HashSet<String>(Arrays.asList(yr.getCategories())).contains(testText));
		Set<YelpRestaurant> retSet = retStream.collect(Collectors.toCollection(HashSet::new));
		return retSet;
	}

	@Override
	public Set<YelpRestaurant> visitName(NameContext ctx) {
		String text = ctx.getChild(1).getText();
		System.out.println(text);
		String testText = text.substring(1, text.length() - 1);
		System.out.println(testText);
		Stream<YelpRestaurant> retStream = yelpDB.getRestaurants().stream()
				.filter(yr -> yr.getName().equals(testText));
		Set<YelpRestaurant> retSet = retStream.collect(Collectors.toCollection(HashSet::new));
		return retSet;
	}

	@Override
	public Set<YelpRestaurant> visitRating(RatingContext ctx) {

		String text = ctx.getChild(2).getText();
		String ineq = ctx.getChild(1).getText();
		double rating = Double.parseDouble(text);

		Predicate<YelpRestaurant> predicate;

		switch (ineq) {
		case ">":
			predicate = (yr -> yr.getStars() > rating);
			break;
		case ">=":
			predicate = (yr -> yr.getStars() >= rating);
			break;
		case "<":
			predicate = (yr -> yr.getStars() < rating);
			break;
		case "<=":
			predicate = (yr -> yr.getStars() <= rating);
			break;
		default:
			predicate = (yr -> yr.getStars() == rating);
			break;
		}
		
		Stream<YelpRestaurant> retStream = yelpDB.getRestaurants().stream()
				.filter(predicate);
		Set<YelpRestaurant> retSet = retStream.collect(Collectors.toCollection(HashSet::new));
		return retSet;
	}

	@Override
	public Set<YelpRestaurant> visitPrice(PriceContext ctx) {
		String text = ctx.getChild(2).getText();
		String ineq = ctx.getChild(1).getText();
		double price = Double.parseDouble(text);

		Predicate<YelpRestaurant> predicate;

		switch (ineq) {
		case ">":
			predicate = (yr -> yr.getPrice() > price);
			break;
		case ">=":
			predicate = (yr -> yr.getPrice() >= price);
			break;
		case "<":
			predicate = (yr -> yr.getPrice() < price);
			break;
		case "<=":
			predicate = (yr -> yr.getPrice() <= price);
			break;
		default:
			predicate = (yr -> yr.getPrice() == price);
			break;
		}
		
		Stream<YelpRestaurant> retStream = yelpDB.getRestaurants().stream()
				.filter(predicate);
		Set<YelpRestaurant> retSet = retStream.collect(Collectors.toCollection(HashSet::new));
		return retSet;
	}
	
	@Override
	public Set<YelpRestaurant> defaultResult() {
		return new HashSet<YelpRestaurant>();
	}
	
	@Override
	public Set<YelpRestaurant> aggregateResult(Set<YelpRestaurant> aggregate, Set<YelpRestaurant> nextResult){
		aggregate.addAll(nextResult);
		return  nextResult;
	}
	@Override
	protected boolean shouldVisitNextChild(RuleNode node, Set<YelpRestaurant> currentResult) {
		return true;
		
	}

	private boolean ArrayContains(String[] arr, String s) {
		for (String str : arr) {
			if (str.equals(s))
				return true;
		}
		return false;
	}
}