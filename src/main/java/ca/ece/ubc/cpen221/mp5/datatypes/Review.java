package ca.ece.ubc.cpen221.mp5.datatypes;

import java.util.Date;

public class Review {

	// TODO: Make this extendable for YelpReview
	String businessID;
	String reviewID;
	String content;
	String userID;
	Date date;

	Review(String content) {

		this.content = content;
		reviewID = this.createReviewID();
	}

	private String createReviewID() {
		String newID = "o";
		return newID;
	}

}
