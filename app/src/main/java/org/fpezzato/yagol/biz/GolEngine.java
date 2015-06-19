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


	/*	FluentIterable.from(currentState.cellSet()).transform(new Function<Cell<Integer, Integer, Boolean>, Void>() {
			@Override
			public Void apply(Cell<Integer, Integer, Boolean> input) {
				int neighboursCount = computeNeighboursCount(currentState.toArray(Boolean.class), input);
				boolean isAlive = input.getValue() != null ? input.getValue() : false;
				boolean alive = computeRule1(isAlive, neighboursCount)
					&&
				computeRule2(isAlive, neighboursCount)
					&& computeRule3(isAlive, neighboursCount)
					&& computeRule4(isAlive, neighboursCount);
				mSwapTable.put(input.getRowKey(), input.getColumnKey(), alive);
				return null;
			}
		}).size();*/


		Boolean[][] next = new Boolean[sizeX][sizeY];  // empty board
		for (int i = 0; i < sizeX; i++) {     // loops through x-axis for computing the next generation
			for (int k = 0; k < sizeY; k++) { // loops through y-axis
				int neighboursCount = computeNeighboursCount(matrix, i, k);
				boolean isAlive = matrix[i][k] != null ? matrix[i][k] : false;
				/*boolean alive = computeRule1(isAlive, neighboursCount)
					||
					computeRule2(isAlive, neighboursCount)
					|| computeRule3(isAlive, neighboursCount)
					|| computeRule4(isAlive, neighboursCount);*/
				boolean alive = isAlive;
				if (neighboursCount > 3  ||  neighboursCount < 2)
					alive = false;
				else if (neighboursCount == 3)
					alive = true;
				/*else
					tempCells[row][col] = cells[row][col];*/
				next[i][k] = alive;
			}
		}


		return next;

	}

/*	@VisibleForTesting
	public boolean computeRule1(boolean isAlive, int neighboursCount) {
		return isAlive && !(neighboursCount < 2);
	}


	@VisibleForTesting
	public boolean computeRule2(boolean isAlive, int neighboursCount) {
		return isAlive && neighboursCount == 2 || neighboursCount == 3;
	}

	@VisibleForTesting
	public boolean computeRule3(boolean isAlive, int neighboursCount) {
		return  (isAlive && (neighboursCount > 3))?false:true;
	}


	@VisibleForTesting
	public boolean computeRule4(boolean isAlive, int neighboursCount) {
		return isAlive || (!isAlive && neighboursCount == 3);
	}*/


	@VisibleForTesting
	public int computeNeighboursCount(Boolean[][] matrix, int x, int y) {
		///TODO - optimization: when more than 3, just break. It uleless to know the exact value.
		int result = 0;
		if (matrix.length > 0 && matrix[0].length > 0) {
			for (int i = x - 1; i <= x + 1; i++) {
				for (int j = y - 1; j <= y + 1; j++) {
					if (! ( (i==x && j==y) || i < 0 || j < 0 || i > matrix.length - 1 || j > matrix[0].length - 1)) {
						result += matrix[i][j] != null && matrix[i][j]
							? 1 : 0;
					}
				}
			}
		}
		return result;
	}


}
