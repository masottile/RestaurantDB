package ca.ece.ubc.cpen221.mp5.datatypes;

import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class YelpUser {
	// TODO: Add all getter/setter methods

	private String url;
	private Votes votes;
	private int review_count;
	private String type;
	private String user_id;
	private String name;
	private double average_stars;
	
	public String getUserID() {
		return user_id;
	}
	
	public int getReviewCount() {
		return review_count;
	}
	
}
