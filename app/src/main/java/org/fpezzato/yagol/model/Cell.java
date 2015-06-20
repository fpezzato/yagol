package org.fpezzato.yagol.model;

/**
 * Created by francesco on 20/06/2015.
 */
public class Cell {

	private int mX;
	private int mY;
	private Boolean mIsAlive;

	public Cell(int x, int y, Boolean state) {
		mX = x;
		mY = y;
		mIsAlive = state;
	}

	public static Cell createAlive(int x, int y) {
		return new Cell(x, y, true);
	}

	public static Cell createDead(int x, int y) {
		return new Cell(x, y, false);
	}

	public static Cell create(int x, int y, Boolean state) {
		return new Cell(x, y, state);
	}


	public int getX() {
		return mX;
	}

	public int getY() {
		return mY;
	}

	public Boolean getIsAlive() {
		return mIsAlive;
	}
}
