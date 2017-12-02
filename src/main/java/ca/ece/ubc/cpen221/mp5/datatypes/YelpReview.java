package ca.ece.ubc.cpen221.mp5.datatypes;

public class YelpReview {

	private String type;
	private String business_id;
	private String review_id;
	private Votes votes;
	private String text;
	private double stars;
	private String user_id;
	private String date;

	public String getRestaurantID() {
		return business_id; 
	}

	public String getReviewID() {
		return review_id;
	}

	public String getUserID() {
		return user_id;
	}

	public double getStars() {
		return stars;
	}

	public void setReviewID(String ID) {
		review_id = ID;
	}

	public String getDate() {
		return date;
	}

	public String getText() {
		return text;
	}

	public Votes getVotes() {
		return votes;
	}

	public String getType() {
		return type;
	}
}
