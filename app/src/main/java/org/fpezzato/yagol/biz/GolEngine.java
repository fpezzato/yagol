package org.fpezzato.yagol.biz;


import android.support.annotation.VisibleForTesting;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayTable;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Table.Cell;

import static com.google.common.base.Optional.fromNullable;

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

	ArrayTable<Integer, Integer, Boolean> mSwapTable;

	public ArrayTable<Integer, Integer, Boolean> computeGeneration(final ArrayTable<Integer, Integer, Boolean> currentState) {
		Preconditions.checkNotNull(currentState);
		if (mSwapTable == null) {
			mSwapTable = ArrayTable.create(currentState);
		}
		FluentIterable.from(currentState.cellSet()).transform(new Function<Cell<Integer, Integer, Boolean>, Void>() {
			@Override
			public Void apply(Cell<Integer, Integer, Boolean> input) {
				int neighboursCount = computeNeighboursCount(currentState, input);
				boolean isAlive = input.getValue() != null ? input.getValue() : false;
				boolean alive = computeRule1(isAlive, neighboursCount)
					&& computeRule2(isAlive, neighboursCount)
					&& computeRule3(isAlive, neighboursCount)
					&& computeRule4(isAlive, neighboursCount);
				mSwapTable.put(input.getRowKey(), input.getColumnKey(), alive);
				return null;
			}
		}).size();

		return mSwapTable;

	}


	@VisibleForTesting
	public boolean computeRule1(boolean isAlive, int neighboursCount) {
		return isAlive && !(neighboursCount < 2);
	}


	@VisibleForTesting
	public boolean computeRule2(boolean isAlive, int neighboursCount) {
		return isAlive && (neighboursCount == 2 || neighboursCount == 3);
	}


	@VisibleForTesting
	public boolean computeRule3(boolean isAlive, int neighboursCount) {
		return isAlive && !(neighboursCount > 3);
	}


	@VisibleForTesting
	public boolean computeRule4(boolean isAlive, int neighboursCount) {
		return !isAlive && neighboursCount == 3;
	}


	@VisibleForTesting
	public int computeNeighboursCount(ArrayTable<Integer, Integer, Boolean> matrix, Cell<Integer, Integer, Boolean> cell) {
		int count = 0;
		for (int i = cell.getRowKey() - 1; i <= cell.getRowKey() + 1; i++) {
			for (int j = cell.getColumnKey() - 1; j <= cell.getColumnKey() + 1; j++) {
				if (!(i < 0 || j < 0)) {
					count += fromNullable(matrix.at(i, j)).or(false)
						? 1 : 0;
				}
			}
		}
		return count;
	}

}
