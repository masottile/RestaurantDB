package ca.ece.ubc.cpen221.mp5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;
import com.google.gson.*;

public class YelpDB implements MP5Db<YelpRestaurant> {

	private Map<String, YelpRestaurant> restaurantMap = new HashMap<String, YelpRestaurant>();
	private Map<String, YelpUser> userMap = new HashMap<String, YelpUser>();
	private Map<String, YelpReview> reviewMap = new HashMap<String, YelpReview>();

	private List<YelpRestaurant> restaurantList = new ArrayList<YelpRestaurant>(restaurantMap.values());

	/**
	 * Creator method for YelpDB
	 * 
	 * @param restaurantFile
	 *            json file name containing all the restaurants
	 * @param reviewFile
	 *            json file name containing all the reviews
	 * @param userFile
	 *            json file name containing all the users
	 * @throws FileNotFoundException
	 *             if the method cannot find files from the given file names
	 */
	public YelpDB(String restaurantFile, String reviewFile, String userFile) throws FileNotFoundException {

		Gson gson = new Gson();

		Scanner restaurantScan = new Scanner(new File(restaurantFile));
		Scanner reviewScan = new Scanner(new File(reviewFile));
		Scanner userScan = new Scanner(new File(userFile));

		while (restaurantScan.hasNext()) {
			JsonObject obj = (JsonObject) new JsonParser().parse(restaurantScan.nextLine());
			YelpRestaurant yr = gson.fromJson(obj, YelpRestaurant.class);
			restaurantMap.put(yr.getBusinessID(), yr);
		}

		while (reviewScan.hasNext()) {
			JsonObject obj = (JsonObject) new JsonParser().parse(reviewScan.nextLine());
			YelpReview yr = gson.fromJson(obj, YelpReview.class);
			reviewMap.put(yr.getReviewID(), yr);
		}

		while (userScan.hasNext()) {
			JsonObject obj = (JsonObject) new JsonParser().parse(userScan.nextLine());
			YelpUser yu = gson.fromJson(obj, YelpUser.class);
			userMap.put(yu.getUserID(), yu);
		}

		restaurantScan.close();
		reviewScan.close();
		userScan.close();
	}

	/**
	 * @return copy of restaurantSet
	 */
	public Set<YelpRestaurant> getRestaurants() {
		return new HashSet<YelpRestaurant>(restaurantMap.values());
	}

	/**
	 * @return copy of reviewSet
	 */
	public Set<YelpReview> getReviews() {
		return new HashSet<YelpReview>(reviewMap.values());
	}

	/**
	 * @return copy of userSet
	 */
	public Set<YelpUser> getUsers() {
		return new HashSet<YelpUser>(userMap.values());
	}

	@Override
	public Set<YelpRestaurant> getMatches(String queryString) {
		// TODO Auto-generated method stub
		// Complete for part 4??? or something like that
		return null;
	}

	/**
	 * Creates a JSON string in the same format as voronoi.json Calls on kMeansList
	 * method which takes in k and creates a List of clusters
	 * 
	 * @param k
	 *            number of clusters
	 * @return String in JSON format
	 */
	@Override
	public String kMeansClusters_json(int k) {
		List<Set<YelpRestaurant>> source = this.kMeansList(k);
		Set<kMeans> toBeJson = new HashSet<kMeans>();

		for (int i = 0; i < source.size(); i++) {
			for (YelpRestaurant r : source.get(i)) {
				toBeJson.add(new kMeans(r.latitude, r.longitude, r.name, i));
			}
		}
		Gson gson = new Gson();
		return gson.toJson(toBeJson);

	}

	public List<Set<YelpRestaurant>> kMeansList(int k) {
		List<Set<YelpRestaurant>> kMeansList = new LinkedList<Set<YelpRestaurant>>();
		Map<Point, Set<YelpRestaurant>> centroidMap = new HashMap<Point, Set<YelpRestaurant>>();

		// I'm just going to set the centroids as the first five restaurants. hope this
		// isn't an issue. will ensure all clusters of size k are the same ones, if
		// that's what we want. this is fairly easy to change if needed. can just use
		// random int generator, my only concern is duplicates but I'm sure we can avoid
		// that too
		for (int i = 0; i < k; i++) {
			centroidMap.put(restaurantList.get(i).getLocation(), new HashSet<YelpRestaurant>());
		}

		Map<Point, Set<YelpRestaurant>> mapOfThing = recursiveThing(centroidMap);
		for (Point p : mapOfThing.keySet()) {
			kMeansList.add(mapOfThing.get(p));
		}

		return kMeansList;
	}

	private Map<Point, Set<YelpRestaurant>> recursiveThing(Map<Point, Set<YelpRestaurant>> clusterMap) {

		Map<Point, Set<YelpRestaurant>> mapOfThing = new HashMap<Point, Set<YelpRestaurant>>();
		boolean madeChanges = false;

		// loop to find shortest distance and map the thing
		for (YelpRestaurant res : restaurantList) {
			for (Point p : clusterMap.keySet()) {
				res.location.distanceTo(p);

				// change madeChanges boolean once a re-mapping has been done
				// if madeChanges = still false, return the existing map
			}

		}

		// loop to recalcuate the centroids

		if (madeChanges) {
			return mapOfThing;
		} else {
			return recursiveThing(mapOfThing);
		}

	}

	@Override
	public ToDoubleBiFunction<MP5Db<YelpRestaurant>, String> getPredictorFunction(String user) {

		// TODO Auto-generated method stub
		Map<Double, Integer> starsToPrice = new HashMap<Double, Integer>();
		double starsMean = 0;
		double priceMean = 0;

		// first make a set of points of all their reviews,
		// for(YelpReview yr: reviewSet){
		// if(yr.getUserID().equals(user)) {
		// starsToPrice.put(yr., arg1)
		// }
		// }
		// then preform least squares regression
		// then make a function to return that takes a resutaurant id and a database

		return null;
	}

	private class kMeans {
		// Makes it easier to convert to JSON in correct format later
		// TODO make sure this is a necessary addition
		private double x;
		private double y;
		private String name;
		private int cluster;
		private final double weight = 1.0;

		private kMeans(double x, double y, String name, int cluster) {
			this.x = x;
			this.y = y;
			this.name = name;
			this.cluster = cluster;
		}

		private double getWeight() {
			return this.weight;
		}
	}

}
