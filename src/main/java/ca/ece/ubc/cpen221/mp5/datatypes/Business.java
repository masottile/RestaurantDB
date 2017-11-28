package ca.ece.ubc.cpen221.mp5.datatypes;

import java.util.HashSet;
import java.util.Set;

public class Business {
	// TODO: Make this extendable for YelpRestaurant
	private String businessID;
	private String name;
	private Set<String> category;
	
	public Business() {
		
		businessID = "";
		name = "";
		category = new HashSet<String>();
	}
	
	public String getBusinessID() {
		return businessID;
	}
	
	public String getName() {
		return name;
	}
	
	public Set<String> getCategories(){
		return new HashSet<String>(category);
	}

}
