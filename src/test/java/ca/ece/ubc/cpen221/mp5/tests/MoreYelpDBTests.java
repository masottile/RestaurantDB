package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.YelpDB;

public class MoreYelpDBTests {
	// need to test simple queries methods from the yelpDB

	@Test
	public void test0() {
		try {
			YelpDB ydb = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
			assertEquals("Cafe 3", ydb.getRestNameFromId("gclB3ED6uk6viWlolSb_uA"));
		} catch (FileNotFoundException e) {
			fail();
		}
	}

}
