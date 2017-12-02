package ca.ece.ubc.cpen221.mp5.datatypes;

public class Votes {
	public int cool = 0;
	public int useful = 0;
	public int funny = 0;

	public void addCool() {
		cool++;
	}

	public void addFunny() {
		funny++;
	}

	public void addUseful() {
		useful++;
	}

	public int getCool() {
		return cool;
	}

	public int getUseful() {
		return useful;
	}

	public int getFunny() {
		return funny;
	}
}