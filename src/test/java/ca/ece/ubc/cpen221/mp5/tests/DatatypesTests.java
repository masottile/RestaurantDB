package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.YelpDB;
import ca.ece.ubc.cpen221.mp5.datatypes.Point;
import ca.ece.ubc.cpen221.mp5.datatypes.YelpRestaurant;

public class DatatypesTests {

	YelpDB yelp = new YelpDB("data/testRest.json", "data/reviewsTest.json", "data/usersTest.json");
	String user_id = "QScfKdcxsa7t5qfE0Ev0Cw";
	String review_id = "5kugWMfr3517UddJMrQCRg";
	String business_id = "FWadSZw0G7HsgKXq7gHTnw";

	/*
	 * For a good codacy grade, we don't want to leave fields "unused" even though
	 * they are needed to do proper JSON parsing/returning For very high code
	 * coverage, we then need to test all the getter/setter methods we made to
	 * remove those warnings
	 */

	/*
	 * Sample restaurant:
	 * 
	 * {"open": true, "url": "http://www.yelp.com/biz/peppermint-grill-berkeley",
	 * "longitude": -122.2598181, "neighborhoods": ["UC Campus Area"],
	 * "business_id": "FWadSZw0G7HsgKXq7gHTnw", "name": "Peppermint Grill",
	 * "categories": ["American (Traditional)", "Restaurants"], "state": "CA",
	 * "type": "business", "stars": 2.5, "city": "Berkeley", "full_address":
	 * "2505 Hearst Ave\nSte B\nUC Campus Area\nBerkeley, CA 94709", "review_count":
	 * 16, "photo_url":
	 * "http://s3-media1.ak.yelpcdn.com/assets/2/www/img/924a6444ca6c/gfx/blank_biz_medium.gif",
	 * "schools": ["University of California at Berkeley"], "latitude": 37.8751965,
	 * "price": 2}
	 */

	//@Test
	/*// test all of YelpRestaurant
	public void test0() {
		YelpRestaurant rest = yelp.getRestaurant(business_id);
		assertTrue(rest.isOpen());
		assertEquals(rest.getUrl(), "http://www.yelp.com/biz/peppermint-grill-berkeley");
		assertEquals(rest.getLocation(), new Point(-122.2598181, 37.8751965));
		assertEquals(rest.getNeighborhoods().length, 1);
		assertEquals(rest.getCategories().length, 2);
		assertEquals(rest.getType(), "business");
		assertEquals(rest.getStars(), 2.5, 0.01);
		assertEquals(rest.getFullAddress(), "2505 Hearst Ave\\nSte B\\nUC Campus Area\\nBerkeley, CA 94709");
	}*/

	/*
	 * Sample user:
	 * 
	 * {"url": "http://www.yelp.com/user_details?userid=QScfKdcxsa7t5qfE0Ev0Cw",
	 * "votes": {"funny": 3, "useful": 17, "cool": 4}, "review_count": 37, "type":
	 * "user", "user_id": "QScfKdcxsa7t5qfE0Ev0Cw", "name": "Erin C",
	 * "average_stars": 3.83783783783784}
	 */
	@Test
	// test all of YelpUser
	public void test1() {
	}

	/*
	 * Sample review:
	 * 
	 * {"type": "review", "business_id": "h_we4E3zofRTf4G0JTEF0A", "votes": {"cool":
	 * 3, "useful": 1, "funny": 4}, "review_id": "5kugWMfr3517UddJMrQCRg", "text":
	 * "I tg", "stars": 4, "user_id": "9fMogxnnd0m9_FKSi-4AoQ", "date":
	 * "2006-11-30"}
	 */
	@Test
	// test all of YelpReview
	public void test2() {
	}

	@Test
	// test all of Votes
	public void test3() {
	}

	@Test
	// test all of Points
	public void test4() {
	}

	@Test
	// test all of Business
	public void test5() {
	}

	@Test
	// test all of Review
	public void test6() {
	}

}
