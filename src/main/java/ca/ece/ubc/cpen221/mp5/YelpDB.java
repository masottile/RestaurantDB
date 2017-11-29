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

		kMeans kMeansCluster = new kMeans();
		List<Set<YelpRestaurant>> source = kMeansCluster.kMeansList(k);

		Set<kMeans> toBeJson = new HashSet<kMeans>();
		Gson gson = new Gson();

		for (int i = 0; i < source.size(); i++) {

			for (YelpRestaurant r : source.get(i)) {
				toBeJson.add(new kMeans(r.getLatitude(), r.getLongitude(), r.getName(), i));
			}
		}
		return gson.toJson(toBeJson);
	}

	// BEGINNING OF K-MEANS NESTED CLASS

	private class kMeans {
		private static final int MAX_ITERATIONS = 50;
		public ArrayList<Point> tryCentroids = new ArrayList<Point>();
		public Map<Point, Set<YelpRestaurant>> tryMap = new HashMap<Point, Set<YelpRestaurant>>();

		private double x;
		private double y;
		private String name;
		private int cluster;
		private final double weight = 1.0;

		kMeans(double x, double y, String name, int cluster) {
			this.x = x;
			this.y = y;
			this.name = name;
			this.cluster = cluster;
		}

		public kMeans() {
		}

		public List<Set<YelpRestaurant>> kMeansList(int k) {

			List<Set<YelpRestaurant>> kMeansList = new LinkedList<Set<YelpRestaurant>>();

			// getting first k restaurants, setting them as k initial centroids
			for (int i = 0; i < k; i++) {
				tryCentroids.add(restaurantList.get(i).getLocation());
			}
			// map initial centroids
			tryMap = mapToClosestCentroid(tryCentroids);
			Map<Point, Set<YelpRestaurant>> kMeansMap = reEvaluate(tryMap, 0);

			// convert to list of clusters
			for (Point p : kMeansMap.keySet()) {
				kMeansList.add(kMeansMap.get(p));
			}
			return kMeansList;
		}

		private Map<YelpRestaurant, Point> mapToClosestCentroid(ArrayList<Point> centroids) {
			// initialize map with the list of centroids
			for (YelpRestaurant res : restaurantList) {
				Map<YelpRestaurant, Point> findCentroid = new HashMap<YelpRestaurant, Point>();

				// arbitrarily map it to the first centroid
				findCentroid.put(res, centroids.get(0));

				// see if any other centroids are closer
				for (int i =1; i < centroids.size(); i++) {
					if (res.distanceTo(centroids.get(i)) < res.distanceTo(findCentroid.get(res))) {
						findCentroid.replace(res, centroids.get(i));
					}
				}
				// map restaurant to its closest centroid
				Point theCentroid = findCentroid.get(res);
				tryMap.get(theCentroid).add(res);
			}
			return tryMap;
		}

		private Map<Point, Set<YelpRestaurant>> reEvaluate(Map<Point, Set<YelpRestaurant>> map, int count) {
			Map<Point, Set<YelpRestaurant>> inputMap = new HashMap<Point, Set<YelpRestaurant>>(map);
			boolean noNewCentroids = true;
			count++;

			// calculate new Centroids
			for (Point p : inputMap.keySet()) {

				double totalX = 0;
				double totalY = 0;

				for (YelpRestaurant res : inputMap.get(p)) {
					totalX = totalX + res.getLatitude();
					totalY = totalY + res.getLongitude();
				}
				Point newCent = new Point(totalX / inputMap.size(), totalY / inputMap.size());

				if (!inputMap.keySet().contains(newCent)) {
					noNewCentroids = false;
				}
				tryCentroids.add(newCent);
			}
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
	}
}
