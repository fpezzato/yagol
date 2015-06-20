package org.fpezzato.yagol.biz;

import java.util.Arrays;

/**
 * Created by francesco on 20/06/2015.
 */
public class ArrayUtils {

	public static Boolean[][] getEmptyMatrix(int sizeX, int sizeY) {
		Boolean[][] matrix = new Boolean[sizeX][sizeY];
		if (sizeX > 0) {
			for (int i = 0; i < matrix.length; i++) {
				Arrays.fill(matrix[i], false);
			}
		}

		return matrix;
	}
}
