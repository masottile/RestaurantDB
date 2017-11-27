package ca.ece.ubc.cpen221.mp5;

import java.util.Map;

public class YelpReview {
	// TODO: Add all getter/setter methods
	
	private String type;
	private String business_id;
	private String review_id;
	private Votes votes;
	private String text;
	private double stars;
	private String user_id;
	private String date;
	
	public String getRetaurantID() {
		return business_id;
	}
	
	public String getReviewID() {
		return review_id;
	}
	
	public String getUserID() {
		return user_id;
	}

}
