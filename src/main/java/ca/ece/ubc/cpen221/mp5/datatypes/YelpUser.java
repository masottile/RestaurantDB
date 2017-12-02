package ca.ece.ubc.cpen221.mp5.datatypes;

public class YelpUser {
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

	public void addReview() {
		review_count++;
	}

	public double getAverageStars() {
		return average_stars;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public Votes getVotes() {
		return votes;
	}

}
