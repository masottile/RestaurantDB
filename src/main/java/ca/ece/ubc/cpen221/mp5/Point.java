package ca.ece.ubc.cpen221.mp5;

public class Point {
	private double x;
	private double y;

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public double distanceTo(Point p) {
		double xDiffsq = Math.pow(this.x - p.x, 2);
		double yDiffsq = Math.pow(this.y - p.y, 2);

		return Math.sqrt(xDiffsq + yDiffsq);
	}
}
