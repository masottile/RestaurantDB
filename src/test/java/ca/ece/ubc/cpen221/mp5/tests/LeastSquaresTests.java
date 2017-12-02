package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.function.ToDoubleBiFunction;

import ca.ece.ubc.cpen221.mp5.MP5Db;
import ca.ece.ubc.cpen221.mp5.YelpDB;
import ca.ece.ubc.cpen221.mp5.datatypes.YelpRestaurant;

public class LeastSquaresTests {

	private static final double TOLERANCE = 0.000001;
	// test 0 user DNE
	// test 1 user only has one review
	// test 2 user only writes reviews for restaurants of same price
	// test 3 user wrote 3 reviews, get prediction function and calculate prediction
	// for 4th
	// test 4 basically like 3 but with some tbd differences

	private YelpDB theStuff; // gotta actually make this but with reduced files with few users/reviews
	// has 4 rest, 2 user, some reviews

	@Test
	// tests a user not included in the database
	public void test0() {

		try {
			theStuff = new YelpDB("data/testRest.json", "data/reviewsTest.json", "data/usersTest.json");

			String userID = "idgaf";
			theStuff.getPredictorFunction(userID);
			fail();

		} catch (FileNotFoundException e) {
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	// tests a user who wrote no reviews
	public void test1() {
		try {
			theStuff = new YelpDB("data/testRest.json", "data/reviewsTest.json", "data/usersTest.json");

			String userID = "_NH7Cpq3qZkByP5xR4gXog";
			theStuff.getPredictorFunction(userID);
			fail();

		} catch (FileNotFoundException e) {
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	// tests a user with only one review
	public void test2() {
		try {
			theStuff = new YelpDB("data/testRest.json", "data/reviewsTest.json", "data/usersTest.json");

			String userID = "QScfKdcxsa7t5qfE0Ev0Cw";
			theStuff.getPredictorFunction(userID);
			fail();

		} catch (FileNotFoundException e) {
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	// tests a user with reviews all at same price point
	public void test3() {
		try {
			theStuff = new YelpDB("data/testRest.json", "data/reviewsTest.json", "data/usersTest.json");

			String userID = "754HGCLgGJLh1VU_WtGjsw";
			theStuff.getPredictorFunction(userID);
			fail();

		} catch (FileNotFoundException e) {
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	// tests the function generation with two separate points to make sure line is
	// correct. Also tests using a different database than the one used to generate
	// the function
	public void test4() {
		try {
			theStuff = new YelpDB("data/testRest.json", "data/reviewsTest.json", "data/usersTest.json");
			String preFix = "data/";
			YelpDB aiya = new YelpDB(preFix + "restaurants.json", preFix + "reviews.json", preFix + "users.json");

			String userID = "7RsdY4_1Bb_bCf5ZbK6tyQ";
			ToDoubleBiFunction<MP5Db<YelpRestaurant>, String> predictorFn = theStuff.getPredictorFunction(userID);

			assertEquals(1.7, predictorFn.applyAsDouble(theStuff, "gclB3ED6uk6viWlolSb_uA"), TOLERANCE);
			assertEquals(5, predictorFn.applyAsDouble(aiya, "XD5ybqI0BHcTj5cLQyIPLA"), TOLERANCE);

		} catch (FileNotFoundException e) {
			fail();
		}
	}
}
