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

	@Test
	public void test0() throws FileNotFoundException {

		Gson gson = new Gson();
		String preFix = "data/";
		YelpDB aiya = new YelpDB(preFix + "restaurants.json", preFix + "reviews.json", preFix + "users.json");
		assertEquals(135, aiya.restaurantSet.size());
		assertEquals(8556, aiya.userSet.size());
		assertEquals(17396, aiya.reviewSet.size());
		// problem(?): creates Json as JsonArray which I don't like... not actually sure
		// if that'll be an issue later on...
		System.out.println(gson.toJson(aiya.restaurantSet));
	}

}
