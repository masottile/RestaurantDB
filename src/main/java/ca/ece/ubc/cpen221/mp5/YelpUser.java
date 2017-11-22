package ca.ece.ubc.cpen221.mp5;

import java.net.URL;
import java.util.Map;

public class YelpUser extends User {

	private URL url;
	protected Map<String, Integer> votes;
	int reviewCount;
	double averageStars;

	YelpUser(String name, String ID) {
		super(name, ID);
	}

	
	
}
