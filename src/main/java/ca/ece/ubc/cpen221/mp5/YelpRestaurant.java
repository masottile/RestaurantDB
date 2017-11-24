package ca.ece.ubc.cpen221.mp5;

public class YelpRestaurant {
	// TODO: Add all getter/setter methods
	private boolean open;
	protected String url;
	protected double longitude;
	protected String[] neighborhoods;
	protected String business_id;
	protected String name;
	protected String[] categories;
	protected String state;
	protected String type;
	protected double stars;
	protected String city;
	protected String full_address;
	protected int review_count;
	protected String photo_url;
	protected String[] schools;
	protected double latitude;
	protected int price;

	private Point location;

	public YelpRestaurant() {
		location = new Point(latitude, longitude);
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

}
