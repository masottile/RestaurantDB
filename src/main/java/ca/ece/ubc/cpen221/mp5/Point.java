package ca.ece.ubc.cpen221.mp5;

public class Point {
	double x;
	double y;

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	/**
	 * @param p
	 * 		a point that is not null
	 * @return the distance from this to p
	 */
	public double distanceTo(Point p) {
		double xDiffsq = Math.pow(this.x - p.x, 2);
		double yDiffsq = Math.pow(this.y - p.y, 2);

		return Math.sqrt(xDiffsq + yDiffsq);
	}
}
