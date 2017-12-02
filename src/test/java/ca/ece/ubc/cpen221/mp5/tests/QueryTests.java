package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.*;
import ca.ece.ubc.cpen221.mp5.datatypes.YelpRestaurant;

public class QueryTests {

	YelpDB yelpQT1 = new YelpDB("data/QueryTest1.json", "data/reviews.json", "data/users.json");
	YelpDB yelp = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
	
	@Test
	public void test00() {
		Set<YelpRestaurant> result = yelpQT1.getMatches("category(Chinese)");
		
		Stream<YelpRestaurant> predictedStream = yelpQT1.getRestaurants().stream()
				.filter(yr -> new HashSet<String>(Arrays.asList(yr.getCategories())).contains("Chinese"));
		Set<YelpRestaurant> predictedSet = predictedStream.collect(Collectors.toCollection(HashSet::new));
		
		assertEquals(3, predictedSet.size());
		assertEquals(predictedSet,result);
	}
/*
	@Test
	// results found by hand
	public void test0() {
		Set<YelpRestaurant> set1 = yelpQT1
				.getMatches("(category(Chinese) && rating < 4) || (category(Cafe || Italian) && price =2)");
		String[] ans = { "Peking Express", "Sun Hong Kong Restaurant", "Tivoli Cafe", "Pasta Bene" };
		Set<String> resultNames = new HashSet<String>(Arrays.asList(ans));
		boolean isGood = true;

		for (YelpRestaurant yr : set1) {
			if (!resultNames.contains(yr.getName()))
				isGood = false;
		}
		assertEquals(ans.length, set1.size());
		assertTrue(isGood);
	}
/*
	@Test
	// Test an impossible case
	public void test1() {
		Set<YelpRestaurant> set1 = yelpQT1.getMatches("rating < 4 && rating > 2");
		assertTrue(set1.isEmpty());
	}

	@Test
	// sets and subsets
	public void test2() {

		Set<YelpRestaurant> set1 = yelpQT1.getMatches("price <= 2 && rating >= 4");
		Set<YelpRestaurant> set2 = yelpQT1.getMatches("rating >= 4");
		boolean isGood = true;

		String[] ans = { "Happy Valley", "Momo Masala", "Tivoli Cafe", "The Coffee Lab", "Pasta Bene",
				"Crepes A-Go Go" };
		Set<String> resultNames = new HashSet<String>(Arrays.asList(ans));

		for (YelpRestaurant yr : set1) {
			if (!resultNames.contains(yr.getName()))
				isGood = false;
		}

		assertTrue(isGood);
		assertEquals(set1.size(), set2.size());
		assertEquals(6, set1.size());
		assertFalse(set1.retainAll(set2));
	}

	@Test
	// just another normal test
	public void test3() {
		Set<YelpRestaurant> set1 = yelpQT1
				.getMatches("(category(Chinese) && rating < 4) || (category(Cafe || Italian) && price =2)");
		String[] ans = { "Peking Express", "Sun Hong Kong Restaurant", "Happy Valley" };
		Set<String> resultNames = new HashSet<String>(Arrays.asList(ans));
		boolean isGood = true;

		for (YelpRestaurant yr : set1) {
			if (!resultNames.contains(yr.getName()))
				isGood = false;
		}
		assertEquals(ans.length, set1.size());
		assertTrue(isGood);
	}//*/

	/*@Test
	// invalid input
	public void test4() {
		try {
			yelp.getMatches("in UC Berkeley Area & Chinese");
			//fail();
		} catch (IllegalArgumentException e) {
		}
	}*/

	@Test
	public void test5() {
	}

	@Test
	public void test6() {
	}

	@Test
	public void test7() {
	}
}
