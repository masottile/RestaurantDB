package ca.ece.ubc.cpen221.mp5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;
import com.google.gson.*;

public class YelpDB implements MP5Db<YelpRestaurant> {

	private Set<YelpRestaurant> restaurantSet = new HashSet<YelpRestaurant>();
	private Set<YelpUser> userSet = new HashSet<YelpUser>();
	private Set<YelpReview> reviewSet = new HashSet<YelpReview>();

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
			restaurantSet.add(gson.fromJson(obj, YelpRestaurant.class));
		}

		while (reviewScan.hasNext()) {
			JsonObject obj = (JsonObject) new JsonParser().parse(reviewScan.nextLine());
			reviewSet.add(gson.fromJson(obj, YelpReview.class));
		}

		while (userScan.hasNext()) {
			JsonObject obj = (JsonObject) new JsonParser().parse(userScan.nextLine());
			userSet.add(gson.fromJson(obj, YelpUser.class));
		}

		restaurantScan.close();
		reviewScan.close();
		userScan.close();
	}

	/**
	 * @return copy of restaurantSet
	 */
	public Set<YelpRestaurant> getRestaurants() {
		return new HashSet<YelpRestaurant>(restaurantSet);
	}

	/**
	 * @return copy of reviewSet
	 */
	public Set<YelpReview> getReviews() {
		return new HashSet<YelpReview>(reviewSet);
	}

	/**
	 * @return copy of userSet
	 */
	public Set<YelpUser> getUsers() {
		return new HashSet<YelpUser>(userSet);
	}
	@Override
	public Set<YelpRestaurant> getMatches(String queryString) {
		// TODO Auto-generated method stub
		// Complete for part 4??? or something like that
		return null;
	}

	@Override
	public String kMeansClusters_json(int k) {
		// TODO Check this, also very important part: actually write kMeansList method
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
		Set<Point> centroidSet = new HashSet<Point>();
		boolean firstTime = true;// only do initial centroid thing once
		if (firstTime) {
			for (int i = 0; i < k; i++) {
				// TODO: dank method to pick initial centroids. should be somewhat based on
				// max/min x/y values so to minimize steps needed
				centroidSet.add(new Point(1, 2));
			}
			firstTime = false;
		}

		// TODO: finish this method...

		return kMeansList;

	}

	@Override
	public ToDoubleBiFunction getPredictorFunction(String user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class kMeans {
		// Makes it easier to convert to JSON in correct format later
		//TODO make sure this is a necessary addition
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
	}

}
