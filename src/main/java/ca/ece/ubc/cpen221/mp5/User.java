package ca.ece.ubc.cpen221.mp5;

public class User {

	// TODO: Make this extendable for YelpUser
	String userID;
	String name;
	int reviewCount;

	User(String name, String ID) {
		this.name = name;
		this.userID = ID;
	}

}
