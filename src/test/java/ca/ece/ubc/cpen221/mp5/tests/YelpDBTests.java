package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.gson.Gson;

import ca.ece.ubc.cpen221.mp5.YelpDB;
import ca.ece.ubc.cpen221.mp5.datatypes.Point;
import ca.ece.ubc.cpen221.mp5.datatypes.YelpRestaurant;

public class YelpDBTests {

	static Gson gson = new Gson();
	static String preFix = "data/";
	YelpDB aiya;

	@Test
	public void test0() throws FileNotFoundException {

		String preFix = "data/";
		YelpDB aiya = new YelpDB(preFix + "restaurants.json", preFix + "reviews.json", preFix + "users.json");

		assertEquals(135, aiya.getRestaurants().size());
		assertEquals(8556, aiya.getUsers().size());
		assertEquals(17396, aiya.getReviews().size());
	}

	@Test
	// outputting some voronoi stuff
	public void test1() throws FileNotFoundException {

		String preFix = "data/";
		YelpDB aiya = new YelpDB(preFix + "restaurants.json", preFix + "reviews.json", preFix + "users.json");

		assertEquals(5, aiya.kMeansList(5).size()); 

		// System.out.println("k-means cluster, k = 5: ");
		// System.out.println(aiya.kMeansClusters_json(5));
		// System.out.println("k-means cluster, k = 5: ");
		// System.out.println(aiya.kMeansClusters_json(5));
		// System.out.println("k-means cluster, k = 3: ");
		// System.out.println(aiya.kMeansClusters_json(3));
	}

	@Test
	// tests that no restaurant is closer to another centroid
	public void test2() throws FileNotFoundException {

		String preFix = "data/";
		YelpDB aiya = new YelpDB(preFix + "restaurants.json", preFix + "reviews.json", preFix + "users.json");
		boolean clustersAreGood = true;

		aiya.kMeansClusters_json(45);
		List<Point> centroids = new ArrayList<Point>();

		for (YelpRestaurant res : aiya.restaurantList) {
			for (int i = 0; i < centroids.size(); i++) {
				if (res.distanceTo(centroids.get(i)) < res.distanceTo(aiya.currentState.get(res))
						&& i != centroids.indexOf(aiya.currentState.get(res)))
					clustersAreGood = false;
			}
		}
		assertTrue(clustersAreGood);
	}

	@Test
	// tests that no cluster is empty i.e. no set corresponding to a centroid is
	// empty
	public void test3() throws FileNotFoundException {

		String preFix = "data/";
		YelpDB aiya = new YelpDB(preFix + "restaurants.json", preFix + "reviews.json", preFix + "users.json");
		boolean noEmpty = true;

		LinkedList<Set<YelpRestaurant>> list = aiya.kMeansList(45);

		for (int i = 0; i < list.size(); i++) {
			noEmpty = (!list.get(i).isEmpty());
		}
		assertTrue(noEmpty);
	}

	@Test
	// tests that if k > number of restaurants, throw an exception
	public void test4() throws FileNotFoundException {

		String preFix = "data/";
		YelpDB aiya = new YelpDB(preFix + "restaurants.json", preFix + "reviews.json", preFix + "users.json");

		try {
			aiya.kMeansList(136);
			fail();
		} catch (IllegalArgumentException e) {
		}

	}

	@Test
	// testing the yROfNonLonelyCentroid or something like that
	public void test5() throws FileNotFoundException {

		String preFix = "data/";
		YelpDB aiya = new YelpDB(preFix + "leastLonelyRestTest.json", preFix + "reviews.json", preFix + "users.json");

		boolean noEmpty = true;

		LinkedList<Set<YelpRestaurant>> list = aiya.kMeansList(3);

		for (int i = 0; i < list.size(); i++) {
			noEmpty = (!list.get(i).isEmpty());
		}
		assertTrue(noEmpty);
	}

}
