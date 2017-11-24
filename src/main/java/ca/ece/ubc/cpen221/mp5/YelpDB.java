package ca.ece.ubc.cpen221.mp5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;
import com.google.gson.*;

public class YelpDB implements MP5Db {

	public Set<YelpRestaurant> resSet = new HashSet<YelpRestaurant>();
	public Set<YelpUser> userSet = new HashSet<YelpUser>();
	public Set<YelpReview> revSet = new HashSet<YelpReview>();

	public YelpDB(String restaurantList, String reviewList, String userList) throws FileNotFoundException {

		Gson gson = new Gson();
		Scanner restScan = new Scanner(new File("C:/Users/jessw/f17-mp5-gejessicama_masottile/data/restaurants.json"));
		Scanner userScan = new Scanner(new File("C:/Users/jessw/f17-mp5-gejessicama_masottile/data/users.json"));
		Scanner revScan = new Scanner(new File("C:/Users/jessw/f17-mp5-gejessicama_masottile/data/reviews.json"));

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ToDoubleBiFunction getPredictorFunction(String user) {
		// TODO Auto-generated method stub
		return null;
	}

}
