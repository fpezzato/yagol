package org.fpezzato.yagol.biz;


import android.support.annotation.VisibleForTesting;

import com.google.common.base.Preconditions;

/**
 * Created by francesco on 17/06/2015.
 */
public class GolEngine {

	/**
	 * Any live cell with fewer than two live neighbours dies, as if caused by under-population.
	 * Any live cell with two or three live neighbours lives on to the next generation.
	 * Any live cell with more than three live neighbours dies, as if by overcrowding.
	 * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
	 */

	public Boolean[][] computeGeneration(final Boolean[][] matrix) {
		Preconditions.checkNotNull(matrix);

		int sizeX = matrix.length;
		int sizeY = matrix[0].length;

		Boolean[][] nextGen = new Boolean[sizeX][sizeY];  // empty board
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				int neighboursCount = computeNeighboursCount(matrix, i, j);
				boolean isAlive = matrix[i][j] != null ? matrix[i][j] : false;
				boolean alive = isAlive;
				if (neighboursCount > 3 || neighboursCount < 2) {
					alive = false;
				} else if (neighboursCount == 3) {
					alive = true;
				}
				nextGen[i][j] = alive;
			}
		}
		return nextGen;
	}

	@VisibleForTesting
	public int computeNeighboursCount(Boolean[][] matrix, int x, int y) {
		///TODO - optimization: when more than 3, just break. It's useless to know the exact value.
		int result = 0;
		if (matrix.length > 0 && matrix[0].length > 0) {
			for (int i = x - 1; i <= x + 1; i++) {
				for (int j = y - 1; j <= y + 1; j++) {
					if (!((i == x && j == y) || i < 0 || j < 0 || i > matrix.length - 1 || j > matrix[0].length - 1)) {
						result += matrix[i][j] != null && matrix[i][j]
							? 1 : 0;
					}
				}
			}
		}
		return result;
	}


}
