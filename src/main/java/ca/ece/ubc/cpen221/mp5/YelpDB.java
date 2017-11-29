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

	private Set<YelpReview> reviewSet = new HashSet<YelpReview>();

	private Map<String, YelpRestaurant> restaurantMap = new HashMap<String, YelpRestaurant>();
	private Map<String, YelpUser> userMap = new HashMap<String, YelpUser>();
	private Map<String, YelpReview> reviewMap = new HashMap<String, YelpReview>();

	public List<YelpRestaurant> restaurantList;

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

		this.restaurantList = new ArrayList<YelpRestaurant>(restaurantMap.values());
		for (YelpRestaurant r : restaurantList) {
			r.setLocation();
		}
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
		// Complete for part 5??? or something like that
		return null;
	}

	@Override
	public ToDoubleBiFunction<MP5Db<YelpRestaurant>, String> getPredictorFunction(String user) {

		YelpUser yUser = userMap.get(user);
		Set<Point> priceAndStars = new HashSet<Point>();

		ToDoubleBiFunction<MP5Db<YelpRestaurant>, String> returnFunction;

		double priceMean = 0;
		double starsMean = 0;
		double sxx = 0, syy = 0, sxy = 0;
		double b, a;
		int count = 0;

		// Picks out the reviews made by the given user and saves data on price of
		// restaurant and stars given
		for (YelpReview yr : reviewSet) {
			if (yr.getUserID().equals(user)) {
				double tempPrice = restaurantMap.get(yr.getRestaurantID()).getPrice();
				double tempStars = yr.getStars();
				priceMean += tempPrice;
				starsMean += tempStars;

				priceAndStars.add(new Point(tempPrice, tempStars));
				count++;
			}
		}

		starsMean = starsMean / count;// gotta check it isn't zero
		priceMean = priceMean / count;

		// calculates least squares
		for (Point p : priceAndStars) {
			sxx += Math.pow(p.getX(), 2);
			syy += Math.pow(p.getY(), 2);
			sxy += p.getX() * p.getY();
		}

		b = sxy / sxx;
		a = starsMean - b * priceMean;

		returnFunction = (dataBase, restaurantID) -> {
			int price = ((YelpDB) dataBase).restaurantMap.get(restaurantID).getPrice();
			return b * price + a;
		};

		return returnFunction;
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

		kMeansToJson kMeansCluster = new kMeansToJson();
		List<Set<YelpRestaurant>> source = kMeansCluster.kMeansList(k);

		Set<kMeansToJson> toBeJson = new HashSet<kMeansToJson>();
		Gson gson = new Gson();

		for (int i = 0; i < source.size(); i++) {

			for (YelpRestaurant r : source.get(i)) {
				toBeJson.add(new kMeansToJson(r.getLatitude(), r.getLongitude(), r.getName(), i));
			}
		}
		return gson.toJson(toBeJson);
	}

	// BEGINNING OF K-MEANS NESTED CLASS

	public List<Set<YelpRestaurant>> kMeansList(int k) {
		// TODO first check if k is bigger than the size of our restaurant set
		if (k > restaurantMap.size())
			throw new IllegalArgumentException();
		else {

			Map<YelpRestaurant, Point> currentState = new HashMap<YelpRestaurant, Point>();
			List<Point> centroidList = new ArrayList<Point>();
			boolean reassigned = true;

			// creates initial centroid assignment
			createInitialCentroids(k, currentState, centroidList);

			// now I want to reassign all of these
			mapToClosestCentroid(currentState, centroidList, reassigned);

			// now for recursion!!

			runKMeansAlgorithm(currentState, centroidList, reassigned);

			List<Set<YelpRestaurant>> kMeansList = new LinkedList<Set<YelpRestaurant>>();

			// convert to list of clusters
			for (Point p : kMeansMap.keySet()) {
				kMeansList.add(kMeansMap.get(p));
			}
			return kMeansList;
		}
	}

	private void createInitialCentroids(int k, Map<YelpRestaurant, Point> currentState, List<Point> centroidList) {


		// maps first k restaurants to themselves as a centroid
		for (int i = 0; i < k; i++) {
			currentState.put(restaurantList.get(i), restaurantList.get(i).getLocation());
			centroidList.add(restaurantList.get(i).getLocation());
		}
		// maps rest of restaurants to the location of the first as a centroid
		for (int i = k; i < restaurantList.size(); i++)
			currentState.put(restaurantList.get(i), restaurantList.get(0).getLocation());
	}

	// here is where we might lose centroids
	private boolean mapToClosestCentroid(Map<YelpRestaurant, Point> currentState, List<Point> centroidList, boolean reassigned) {
		for (YelpRestaurant yr : currentState.keySet()) {
			for (Point p : centroidList) {
				if (yr.distanceTo(p) < yr.distanceTo(currentState.get(yr))) {
					currentState.replace(yr, p);
					reassigned = true;
				}
			}
		}
		
		return reassigned;
	}

	private void runKMeansAlgorithm(Map<YelpRestaurant, Point> currentState, List<Point> centroidList,
			boolean reassigned) {

		// Step1, recalculate centroids

		if (!inputMap.keySet().contains(newCent)) {
			noNewCentroids = false;
		}
	}

	private void reCalculateCentroids(Map<YelpRestaurant, Point> currentState, List<Point> centroidList,
			Set<YelpRestaurant> tempYRSet) {

		// calculate new Centroids
		for (Point p : centroidList) {
			tempYRSet.clear();

			for (YelpRestaurant yr : currentState.keySet()) {
				if (currentState.get(yr).equals(p))
					tempYRSet.add(yr);
			}

			Point mean = calculateMean(tempYRSet);

			for (YelpRestaurant yr : tempYRSet)
				currentState.replace(yr, mean);
		}
		
		centroidList = currentState.values().iterator();
		
		
		// if there were no new centroids, the input map was good
		if (noNewCentroids || count == MAX_ITERATIONS) {
			return inputMap;
		}

		else {
			tryMap.clear();
			tryMap = mapToClosestCentroid(tryCentroids);
			return reEvaluate(tryMap, count);
		}
	}

	private Point calculateMean(Set<YelpRestaurant> yRSet) {
		double x = 0, y = 0;
		for (YelpRestaurant yr : yRSet) {
			x += yr.getLatitude();
			y += yr.getLongitude();
		}
		return new Point(x / yRSet.size(), y / yRSet.size());
	}

	private class kMeansToJson {
		private static final int MAX_ITERATIONS = 50;
		public ArrayList<Point> tryCentroids = new ArrayList<Point>();
		public Map<Point, Set<YelpRestaurant>> tryMap = new HashMap<Point, Set<YelpRestaurant>>();

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
		}

		public kMeansToJson() {
		}

	}
}
