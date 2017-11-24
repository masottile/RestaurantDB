package ca.ece.ubc.cpen221.mp5;

public class kMeans {
	// Makes it easier to convert to JSON in correct format later
	double x;
	double y;
	String name;
	int cluster;
	private final double weight = 1.0;

	kMeans(double x, double y, String name, int cluster) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.cluster = cluster;
	}

	// don't need this: only to get rid of yellow lines
	public double getWeight() {
		return weight;
	}

}
