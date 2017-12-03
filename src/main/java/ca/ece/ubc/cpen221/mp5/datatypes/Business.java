package ca.ece.ubc.cpen221.mp5.datatypes;

public class Business {

	private String businessID;
	private String namee;
	private String[] categorie;

	/*
	 * Abstraction Function: a business with an ID, a name, and a set of categories
	 * it falls under
	 * 
	 * Rep Invariant: businessID and name are not null
	 */

	public Business() {
		businessID = "";
		namee = "";
	}

	public String getBusinessID() {
		return businessID;
	}

	public String getName() {
		return namee;
	}

	public String[] getCategories() {
		return categorie;
	}

}
