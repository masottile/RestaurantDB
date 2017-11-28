package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;

import org.junit.Test;

import com.google.gson.Gson;

import ca.ece.ubc.cpen221.mp5.YelpDB;
// TODO: Write useful tests

public class YelpDBTests {

	private static Gson gson = new Gson();
	private static String preFix = "data/";
	private YelpDB aiya;

	@Test
	public void test0() throws FileNotFoundException {

		String preFix = "data/";
		YelpDB aiya = new YelpDB(preFix + "restaurants.json", preFix + "reviews.json", preFix + "users.json");

		assertEquals(135, aiya.getRestaurants().size());
		assertEquals(8556, aiya.getUsers().size());
		assertEquals(17396, aiya.getReviews().size());

		Gson gson = new Gson();
		System.out.println(gson.toJson(aiya.getRestaurants()));
	}

	@Test
	public void test1() throws FileNotFoundException {

		String preFix = "data/";
		YelpDB aiya = new YelpDB(preFix + "restaurants.json", preFix + "reviews.json", preFix + "users.json");
		System.out.println(aiya.kMeansClusters_json(5));

	}

}
