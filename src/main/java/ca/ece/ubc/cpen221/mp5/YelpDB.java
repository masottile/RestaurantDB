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

import ca.ece.ubc.cpen221.mp5.datatypes.*;

public class YelpDB implements MP5Db<YelpRestaurant> {

	private Map<String, YelpRestaurant> restaurantMap = new HashMap<String, YelpRestaurant>();
	private Map<String, YelpUser> userMap = new HashMap<String, YelpUser>();
	private Map<String, YelpReview> reviewMap = new HashMap<String, YelpReview>();
	public Map<YelpRestaurant, Point> currentState = new HashMap<YelpRestaurant, Point>();

	public List<YelpRestaurant> restaurantList = new ArrayList<YelpRestaurant>();

	long IDcount = 0;

	Gson gson;
	JsonParser parser;

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

		gson = new Gson();
		parser = new JsonParser();

		Scanner restaurantScan = new Scanner(new File(restaurantFile));
		Scanner reviewScan = new Scanner(new File(reviewFile));
		Scanner userScan = new Scanner(new File(userFile));

		while (restaurantScan.hasNext()) {
			JsonObject obj = (JsonObject) parser.parse(restaurantScan.nextLine());
			YelpRestaurant yr = gson.fromJson(obj, YelpRestaurant.class);
			restaurantList.add(yr);
			yr.setLocation();
			restaurantMap.put(yr.getBusinessID(), yr);
		}

		while (reviewScan.hasNext()) {
			JsonObject obj = (JsonObject) parser.parse(reviewScan.nextLine());
			YelpReview yr = gson.fromJson(obj, YelpReview.class);
			reviewMap.put(yr.getReviewID(), yr);
		}

		while (userScan.hasNext()) {
			JsonObject obj = (JsonObject) parser.parse(userScan.nextLine());
			YelpUser yu = gson.fromJson(obj, YelpUser.class);
			userMap.put(yu.getUserID(), yu);
		}
		restaurantScan.close();
		reviewScan.close();
		userScan.close();
	}

	// BEGINNING OF SERVER FUNCTIONS FOR PART 4

	public String getNewID() {
		IDcount++;
		return "Bitch#" + Long.toString(IDcount);
	}

	public String newBusinessURL(String ID) {
		return "http://www.yelp.com/biz/" + ID;
	}

	public String newUserURL(String ID) {
		return "http://www.yelp.com/user_details?userid=" + ID;
	}

	public String getRestNameFromId(String businessID) {
		return restaurantMap.get(businessID).getName();
	}

	public void addRestaurant(String s) {
		YelpRestaurant yr = gson.fromJson((JsonObject) parser.parse(s), YelpRestaurant.class);
		yr.setBusinessID(getNewID());
		restaurantMap.put(yr.getBusinessID(), yr);
		restaurantList.add(yr);
	}

	public String addUser(String s) {
		YelpUser user = gson.fromJson((JsonObject) parser.parse(s), YelpUser.class);
		user.setUserID(getNewID());
		user.setUrl(newUserURL(user.getUserID()));
		userMap.put(user.getUserID(), user);
		return gson.toJson(user);
	}

	public void addReview(String s) {
		YelpReview rev = gson.fromJson((JsonObject) parser.parse(s), YelpReview.class);
		rev.setReviewID(getNewID());
		reviewMap.put(rev.getReviewID(), rev);
		restaurantMap.get(rev.getRestaurantID()).addReview();
		userMap.get(rev.getUserID()).addReview();
	}
	// END OF SERVER FUNCTIONS FOR PART 4

	/**
	 * @return set of restaurants
	 */
	public Set<YelpRestaurant> getRestaurants() {
		return new HashSet<YelpRestaurant>(restaurantMap.values());
	}

	/**
	 * @return set of reviews
	 */
	public Set<YelpReview> getReviews() {
		return new HashSet<YelpReview>(reviewMap.values());
	}

	/**
	 * @return set of users
	 */
	public Set<YelpUser> getUsers() {
		return new HashSet<YelpUser>(userMap.values());
	}

	@Override
	public Set<YelpRestaurant> getMatches(String queryString) {
		// example query string
		// in(Telegraph Ave) && (category(Chinese) || category(Italian)) && price <= 2
		return null;
	}

	@Override
	public ToDoubleBiFunction<MP5Db<YelpRestaurant>, String> getPredictorFunction(String user) {

		Set<Point> priceAndStars = new HashSet<Point>();

		ToDoubleBiFunction<MP5Db<YelpRestaurant>, String> returnFunction;

		double priceMean = 0;
		double starsMean = 0;
		double sxx = 0;
		double sxy = 0;
		double b, a;
		int count = 0;

		// Picks out the reviews made by the given user and saves data on price of
		// restaurant and stars given
		for (YelpReview yr : reviewMap.values()) {
			if (yr.getUserID().equals(user)) {

				double tempPrice = restaurantMap.get(yr.getRestaurantID()).getPrice();
				double tempStars = yr.getStars();
				priceMean += tempPrice;
				starsMean += tempStars;

				priceAndStars.add(new Point(tempPrice, tempStars));
				count++;
			}
		}

		if (count == 0)
			throw new IllegalArgumentException();
		else {

			priceMean = priceMean / count;
			starsMean = starsMean / count;

			// calculates least squares
			for (Point p : priceAndStars) {
				sxx += Math.pow(p.getX() - priceMean, 2);
				sxy += (p.getX() - priceMean) * (p.getY() - starsMean);
			}

			if (sxx == 0)
				throw new IllegalArgumentException();
			else {

				b = sxy / sxx;
				a = starsMean - b * priceMean;

				returnFunction = (dataBase, restaurantID) -> {
					int price = ((YelpDB) dataBase).restaurantMap.get(restaurantID).getPrice();
					return Math.min(5, Math.max(1, b * price + a));
				};

				return returnFunction;
			}
		}
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

		List<Set<YelpRestaurant>> source = kMeansList(k);
		Set<kMeansToJson> toBeJson = new HashSet<kMeansToJson>();
		Gson gson = new Gson();

		for (int i = 0; i < source.size(); i++) {

			for (YelpRestaurant r : source.get(i)) {
				toBeJson.add(new kMeansToJson(r.getLatitude(), r.getLongitude(), r.getName(), i));
			}
		}
		return gson.toJson(toBeJson);
	}

	public LinkedList<Set<YelpRestaurant>> kMeansList(int k) {
		// check if k is out of bounds
		if (k > restaurantMap.size())
			throw new IllegalArgumentException();
		else {

			Set<Point> centroidSet = new HashSet<Point>();
			Set<YelpRestaurant> tempYRSet = new HashSet<YelpRestaurant>();
			boolean reassigned = true;

			// creates initial centroid assignment
			createInitialCentroids(k, currentState, centroidSet);

			// now I want to reassign all of these
			mapToClosestCentroid(currentState, centroidSet, reassigned);

			// now for recursion!!
			currentState = runKMeansAlgorithm(currentState, centroidSet, tempYRSet);

			// now we need to put this in the correct format
			LinkedList<Set<YelpRestaurant>> kMeansList = new LinkedList<Set<YelpRestaurant>>();

			// convert to list of clusters
			// convert to Map from a centroid to a yelpRestaurant

			Map<Point, Set<YelpRestaurant>> kMeansMap = new HashMap<Point, Set<YelpRestaurant>>();
			for (Point p : currentState.values()) {
				if (!kMeansMap.containsKey(p))
					kMeansMap.put(p, new HashSet<YelpRestaurant>());
			}
			for (YelpRestaurant res : currentState.keySet()) {
				kMeansMap.get(currentState.get(res)).add(res);
			}
			for (Point p : kMeansMap.keySet()) {
				kMeansList.add(kMeansMap.get(p));
			}
			return kMeansList;
		}
	}

	private void createInitialCentroids(int k, Map<YelpRestaurant, Point> currentState, Set<Point> centroidSet) {

		// maps first k restaurants to themselves as a centroid
		for (int i = 0; i < k; i++) {
			currentState.put(restaurantList.get(i), restaurantList.get(i).getLocation());
			centroidSet.add(restaurantList.get(i).getLocation());
		}
		// maps rest of restaurants to the location of the first as a centroid
		for (int i = k; i < restaurantList.size(); i++)
			currentState.put(restaurantList.get(i), restaurantList.get(0).getLocation());
	}

	// here is where we might lose centroids
	private boolean mapToClosestCentroid(Map<YelpRestaurant, Point> currentState, Set<Point> centroidSet,
			boolean reassigned) {

		for (YelpRestaurant yr : currentState.keySet()) {
			for (Point p : centroidSet) {
				if (yr.distanceTo(p) < yr.distanceTo(currentState.get(yr))) {
					currentState.replace(yr, p);
					reassigned = true;
				}
			}
		}
		if (reassigned) {
			for (Point p : centroidSet) {
				if (!currentState.containsValue(p))
					currentState.replace(yROfNonLonelyCentroid(currentState), p);
			}
		}
		return reassigned;
	}

	// returns some yelp restaurant which a centroid can afford to lose
	private YelpRestaurant yROfNonLonelyCentroid(Map<YelpRestaurant, Point> currentState) {

		Set<Point> centroidCount = new HashSet<Point>();
		for (Point p : currentState.values()) {
			if (centroidCount.contains(p)) { // we have previously come across p, meaning more than one yelpRestaurant
												// maps to that centroid
				for (YelpRestaurant yr : currentState.keySet()) { // we now just want to return any yelpRestaurant that
																	// maps to that centroid
					if (currentState.get(yr).equals(p))
						return yr;
				}
			} else // we haven't seen this centroid before, we want to acknowledge we have seen it
				centroidCount.add(p);
		}
		// this should never happen
		return null;
	}

	private Map<YelpRestaurant, Point> runKMeansAlgorithm(Map<YelpRestaurant, Point> currentState,
			Set<Point> centroidSet, Set<YelpRestaurant> tempYRSet) {

		// Step1, recalculate centroids
		reCalculateCentroids(currentState, centroidSet, tempYRSet);
		// Step 2, resassign and evaluate
		if (mapToClosestCentroid(currentState, centroidSet, false))
			// we reassigned some points
			return runKMeansAlgorithm(currentState, centroidSet, tempYRSet);
		else
			// we didn't reassign any points
			return currentState;

	}

	private void reCalculateCentroids(Map<YelpRestaurant, Point> currentState, Set<Point> centroidSet,
			Set<YelpRestaurant> tempYRSet) {

		// calculate new Centroids
		for (Point p : centroidSet) {
			tempYRSet.clear();

			for (YelpRestaurant yr : currentState.keySet()) {
				if (currentState.get(yr).equals(p))
					tempYRSet.add(yr);
			}

			Point mean = calculateMean(tempYRSet);

			for (YelpRestaurant yr : tempYRSet)
				currentState.replace(yr, mean);
		}

		centroidSet.clear();
		centroidSet.addAll(currentState.values()); // except this list has repeats
	}

	/**
	 * Given a set of restaurants, calculates the mean of their locations
	 * 
	 * @param yRSet
	 *            has at least one element
	 * @return the mean location
	 */
	private Point calculateMean(Set<YelpRestaurant> yRSet) {
		double x = 0, y = 0;
		for (YelpRestaurant yr : yRSet) {
			x += yr.getLatitude();
			y += yr.getLongitude();
		}
		return new Point(x / yRSet.size(), y / yRSet.size());
	}

	private class kMeansToJson {
		private double x;
		private double y;
		private String name;
		private int cluster;
		private final double weight = 1.0;

		kMeansToJson(double x, double y, String name, int cluster) {
			this.x = x;
			this.y = y;
			this.name = name;
			this.cluster = cluster;
			this.stopTheWarnings();
		}

		public String stopTheWarnings() {
			return Double.toString(x) + Double.toString(y) + name + Integer.toString(cluster) + Double.toString(weight);
		}
	}
}
