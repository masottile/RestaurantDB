package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.*;
import ca.ece.ubc.cpen221.mp5.datatypes.YelpRestaurant;

public class QueryTests {

	YelpDB yelpQT1 = new YelpDB("data/QueryTest1.json", "data/reviews.json", "data/users.json");
	YelpDB yelp = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");

	/*
	 * @Test public void test00() { Set<YelpRestaurant> result =
	 * yelpQT1.getMatches("category(Chinese)");
	 * 
	 * Stream<YelpRestaurant> predictedStream = yelpQT1.getRestaurants().stream()
	 * .filter(yr -> new
	 * HashSet<String>(Arrays.asList(yr.getCategories())).contains("Chinese"));
	 * Set<YelpRestaurant> predictedSet =
	 * predictedStream.collect(Collectors.toCollection(HashSet::new));
	 * 
	 * assertEquals(3, predictedSet.size()); assertEquals(predictedSet, result); }
	 * 
	 * @Test public void test01() { Set<YelpRestaurant> result =
	 * yelpQT1.getMatches("price < 2");
	 * 
	 * Stream<YelpRestaurant> predictedStream =
	 * yelpQT1.getRestaurants().stream().filter(yr -> yr.getPrice() < 2);
	 * Set<YelpRestaurant> predictedSet =
	 * predictedStream.collect(Collectors.toCollection(HashSet::new));
	 * 
	 * assertEquals(5, predictedSet.size()); assertEquals(predictedSet, result); }//
	 */

	@Test
	public void test02() {
		Set<YelpRestaurant> result = yelpQT1.getMatches("category(Chinese) && price < 2");

		Stream<YelpRestaurant> predictedStream = yelpQT1.getRestaurants().stream().filter(
				yr -> yr.getPrice() < 2 && new HashSet<String>(Arrays.asList(yr.getCategories())).contains("Chinese"));
		Set<YelpRestaurant> predictedSet = predictedStream.collect(Collectors.toCollection(HashSet::new));

		assertEquals(1, predictedSet.size());
		assertEquals(predictedSet, result);
	}

	@Test
	public void test03() {
		Set<YelpRestaurant> result = yelpQT1.getMatches("category(Chinese) || price < 2");

		Stream<YelpRestaurant> predictedStream = yelpQT1.getRestaurants().stream().filter(
				yr -> yr.getPrice() < 2 || new HashSet<String>(Arrays.asList(yr.getCategories())).contains("Chinese"));

		Set<YelpRestaurant> predictedSet = predictedStream.collect(Collectors.toCollection(HashSet::new));

		assertEquals(7, predictedSet.size());
		assertEquals(predictedSet, result);
	}

	@Test
	public void test0() {
		Set<YelpRestaurant> set1 = yelpQT1
				.getMatches("(category(Chinese) && rating < 4) || ((category(Cafe) || category(Italian)) && price =2)");

		
		Predicate<YelpRestaurant> predicate1 = (yr -> yr.getStars() < 4
				&& new HashSet<String>(Arrays.asList(yr.getCategories())).contains("Chinese"));
		Predicate<YelpRestaurant> predicate2 = (yr -> new HashSet<String>(Arrays.asList(yr.getCategories()))
				.contains("Cafe") || new HashSet<String>(Arrays.asList(yr.getCategories())).contains("Italian"));

		
		Stream<YelpRestaurant> predictedStream1 = yelpQT1.getRestaurants().stream().filter(predicate2)
				.filter(yr -> yr.getPrice() == 2);
		Stream<YelpRestaurant> predictedStream2 = yelpQT1.getRestaurants().stream().filter(predicate1);

		
		Set<YelpRestaurant> predictedSet = predictedStream1.collect(Collectors.toCollection(HashSet::new));
		predictedSet.addAll(predictedStream2.collect(Collectors.toCollection(HashSet::new)));

		assertEquals(predictedSet, set1);
	}

	@Test
	// Test an impossible case
	public void test1() {
		Set<YelpRestaurant> set1 = yelpQT1.getMatches("rating > 4 && rating < 2");
		assertTrue(set1.isEmpty());
	}

	@Test
	// sets and subsets
	public void test2() {

		Set<YelpRestaurant> set1 = yelpQT1.getMatches("price <= 2 && rating >= 4");
		Set<YelpRestaurant> set2 = yelpQT1.getMatches("rating >= 4");
		boolean isGood = true;

		assertTrue(isGood);
		assertEquals(set1.size(), set2.size());
		assertEquals(6, set1.size());
		assertFalse(set1.retainAll(set2));
	}

	@Test
	// just another normal test
	public void test3() {
		Set<YelpRestaurant> set1 = yelpQT1.getMatches("(in(UC Campus Area) || in(Telegraph Ave)) && category(Chinese)");
		String[] ans = { "Peking Express", "Sun Hong Kong Restaurant", "Happy Valley" };
		Set<String> resultNames = new HashSet<String>(Arrays.asList(ans));
		boolean isGood = true;

		for (YelpRestaurant yr : set1) {
			if (!resultNames.contains(yr.getName()))
				isGood = false;

		}
		assertEquals(ans.length, set1.size());
		assertTrue(isGood);
	}

	@Test
	public void test5() {
		// System.out.println(yelp.getMatches("(in(UC Campus Area) || in(Telegraph Ave))
		// && category(Chinese)").size());
	}

	@Test
	public void test6() {
	}

	@Test
	public void test7() {
	}// */
}
