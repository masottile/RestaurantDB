package ca.ece.ubc.cpen221.mp5.datatypes;

public class YelpRestaurant {
	// TODO: Add all getter/setter methods

	// since YelpRestaurant doesn't have any subclasses do we want to just change
	// this to private?
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

	protected Point location;

	public double distanceTo(Point p) {
		return this.location.distanceTo(p);
	}

	public YelpRestaurant() {
		location = new Point(latitude, longitude);
	}

	public Point getLocation() {
		return this.location;
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

}
