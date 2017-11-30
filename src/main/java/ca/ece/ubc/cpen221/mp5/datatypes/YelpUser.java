package ca.ece.ubc.cpen221.mp5.datatypes;

import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class YelpUser {
	// TODO: Add all getter/setter methods
	private String url;
	private Votes votes = new Votes();
	private int review_count = 0;
	private final String type = "user";
	private String user_id;
	private String name;
	private double average_stars = 0;

	public String getUserID() {
		return user_id;
	}

	public void setUserID(String ID) {
		user_id = ID;
	}

	public int getReviewCount() {
		return review_count;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
