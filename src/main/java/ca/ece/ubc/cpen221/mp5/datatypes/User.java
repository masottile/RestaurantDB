package ca.ece.ubc.cpen221.mp5.datatypes;

public class User {

	// TODO: Make this extendable for YelpUser
	public String userID;
	public String name;
	public int reviewCount;

	User(String name, String ID) {
		this.name = name;
		this.userID = ID;
	}

}
