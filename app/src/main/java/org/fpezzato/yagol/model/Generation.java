package org.fpezzato.yagol.model;

/**
 * Created by francesco on 21/06/2015.
 */
public class Generation {

	private Boolean[][] mMatrix;
	private boolean mAtLeastOneAlive;

	public Generation(Boolean[][] matrix, boolean atLeastOneAlive) {
		mMatrix = matrix;
		mAtLeastOneAlive = atLeastOneAlive;
	}

	public Boolean[][] getMatrix() {
		return mMatrix;
	}

	public boolean isAtLeastOneAlive() {
		return mAtLeastOneAlive;
	}
}
