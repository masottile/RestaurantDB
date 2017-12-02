package ca.ece.ubc.cpen221.mp5.datatypes;

public class YelpRestaurant {
	// since YelpRestaurant doesn't have any subclasses do we want to just change
	// this to private?
	private boolean open;
	private String url;
	private double longitude;
	private String[] neighborhoods;
	private String business_id;
	private String name;
	private String[] categories;
	private String state;
	private String type;
	private double stars;
	private String city;
	private String full_address;
	private int review_count = 0;
	private String photo_url = "";
	private String[] schools;
	private double latitude;
	private int price;

	protected Point location;

	public double distanceTo(Point p) {
		return this.location.distanceTo(p);
	}

	public void setLocation() {
		location = new Point(latitude, longitude);
	}

	public Point getLocation() {
		return this.location;
	}

	public void setBusinessID(String ID) {
		business_id = ID;
	}

	public String getUrl() {
		return url;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public String getBusinessID() {
		return business_id;
	}

	public String getName() {
		return name;
	}

	public String[] getCategories() {
		return categories;
	}

	public int getPrice() {
		return price;
	}

	public String[] getNeighborhoods() {
		return neighborhoods;
	}

	public boolean isOpen() {
		return open;
	}

	public String getState() {
		return state;
	}

	public String getType() {
		return type;
	}

	public void addReview() {
		review_count++;
	}

	public double getStars() {
		return stars;
	}

	public String getCity() {
		return city;
	}

	public String getFull_address() {
		return full_address;
	}

	public int getReview_count() {
		return review_count;
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public String[] getSchools() {
		return schools;
	}

}
