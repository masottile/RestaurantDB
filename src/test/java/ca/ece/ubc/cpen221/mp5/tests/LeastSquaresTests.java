package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.YelpDB;

public class LeastSquaresTests {

	// test 0 user DNE
	// test 1 user only has one review
	// test 2 user only writes reviews for restaurants of same price
	// test 3 user wrote 3 reviews, get prediction function and calculate prediction
	// for 4th
	// test 4 basically like 3 but with some tbd differences

	private YelpDB theStuff; // gotta actually make this but with reduced files with few users/reviews
	// has 4 rest, 2 user, some reviews

	@Test
	public void test0() {
		// initialize db and stuff
		String userID = "idgaf";

	}

	@Test
	public void test1() {
		String userID;

	}

	@Test
	public void test2() {
		String userID;

	}

	@Test
	public void test3() {
		String userID;

	}
}
