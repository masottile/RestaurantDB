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

public class YelpDB implements MP5Db {
	// TODO: change these to something more useful like a Map<Name, Restaurant>
	public Set<YelpRestaurant> resSet = new HashSet<YelpRestaurant>();
	public Set<YelpUser> userSet = new HashSet<YelpUser>();
	public Set<YelpReview> revSet = new HashSet<YelpReview>();

	public YelpDB(String restaurantList, String reviewList, String userList) throws FileNotFoundException {

		Gson gson = new Gson();
		// TODO: change it to a relative path - need to look at build path vs. folder
		// path, change to something like ../../../tests maybe?
		String fixThis = "C:/Users/jessw/f17-mp5-gejessicama_masottile/data/";

		Scanner restScan = new Scanner(new File(fixThis + restaurantList));
		Scanner userScan = new Scanner(new File(fixThis + userList));
		Scanner revScan = new Scanner(new File(fixThis + reviewList));

		while (restScan.hasNext()) {
			JsonObject obj = (JsonObject) new JsonParser().parse(restScan.nextLine());
			resSet.add(gson.fromJson(obj, YelpRestaurant.class));
		}

		while (userScan.hasNext()) {
			JsonObject obj = (JsonObject) new JsonParser().parse(userScan.nextLine());
			userSet.add(gson.fromJson(obj, YelpUser.class));
		}

		while (revScan.hasNext()) {
			JsonObject obj = (JsonObject) new JsonParser().parse(revScan.nextLine());
			revSet.add(gson.fromJson(obj, YelpReview.class));
		}

	}

	@Override
	public Set getMatches(String queryString) {
		// TODO Auto-generated method stub
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

}
