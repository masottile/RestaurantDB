package ca.ece.ubc.cpen221.mp5;

import java.util.Map;

public class YelpReview extends Review {

	Map<String, Integer> votes;
	double stars;
	
	YelpReview(String content) {
		super(content);
	}

}
