package ca.ece.ubc.cpen221.mp5.datatypes;

public class YelpRestaurant {
	// TODO: Add all getter/setter methods

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
	private int review_count;
	private String photo_url;
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
	
	public void setBusinessID (String ID) {
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

}
