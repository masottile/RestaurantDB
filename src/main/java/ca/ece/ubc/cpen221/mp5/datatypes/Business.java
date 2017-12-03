package ca.ece.ubc.cpen221.mp5.datatypes;

import java.util.HashSet;
import java.util.Set;

public class Business {

	private String businessID;
	private String name;
	private Set<String> category;

	/*
	 * Abstraction Function: a business with an ID, a name, and a set of categories
	 * it falls under
	 * 
	 * Rep Invariant: no fields are null
	 */

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

	public Set<String> getCategories() {
		return new HashSet<String>(category);
	}

}
