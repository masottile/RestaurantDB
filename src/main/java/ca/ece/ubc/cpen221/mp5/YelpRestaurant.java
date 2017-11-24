package ca.ece.ubc.cpen221.mp5;

public class YelpRestaurant{
	
	boolean open;
	String url;
	double longitude;
	String[] neighborhoods;
	String business_id;
	String name;
	String[] categories;
	String state;
	String type;
	double stars;
	String city;
	String full_address;
	int review_count;
	String photo_url;
	String[] schools;
	double latitude;
	int price;
	
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
	
	public String[] getCategories(){
		return categories;
	}

	
}

