package ca.ece.ubc.cpen221.mp5.datatypes;

public class YelpReview extends Review {

	private String type = "review";
	private String business_id;
	private String review_id;
	private Votes votes;
	private String text;
	private double stars;
	private String user_id;
	private String date;

	/*
	 * Abstraction Function: A review, with the id of the business it is reviewing,
	 * its own review id, the id of the user that created it, votes given to the
	 * review, the text or contents of the review, the stars or rating the user
	 * gave, the date the review was created
	 * 
	 * Rep Invariant: fields are not null. The type is "review". stars is between 1
	 * and 5 inclusive. date is a valid Date.
	 */

	// SETTER METHOD
	public void setReviewID(String ID) {
		review_id = ID;
	}

	// GETTER METHODS

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
