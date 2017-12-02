package ca.ece.ubc.cpen221.mp5.datatypes;

import java.util.Date;

public class Review {

	// TODO: Make this extendable for YelpReview
	public String businessID;
	public String reviewID;
	public String content;
	public String userID;
	public Date date;

	Review(String content) {

		this.content = content;
		reviewID = this.createReviewID();
	}

	private String createReviewID() {
		String newID = "o";
		return newID;
	}

}
