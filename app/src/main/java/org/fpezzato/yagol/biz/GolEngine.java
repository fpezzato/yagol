package org.fpezzato.yagol.biz;


import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

import static com.google.common.base.Optional.fromNullable;

/**
 * Created by francesco on 17/06/2015.
 */
public class GolEngine {


	//public GolEngine( ) {
		//mMatrix = create(ContiguousSet.create(Range.closed(0, rows), integers()), ContiguousSet.create(Range.closed(0, columns), integers()));
	//}

	//public void setAliveCell(int row, int column) {
		//mMatrix.put(row, column, true);
	//}

	/*

	{
		Range<Integer> rows = Range.closed(0, 100);
		Range<Integer> columns = Range.closed(0, 100);

		mMatrix = ArrayTable.create(create(rows, integers()), create(columns, integers()));
	}
*/

	/**
	 * Any live cell with fewer than two live neighbours dies, as if caused by under-population.
	 * Any live cell with two or three live neighbours lives on to the next generation.
	 * Any live cell with more than three live neighbours dies, as if by overcrowding.
	 * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
	 */

	public boolean computeRule1(boolean isAlive, int neighboursCount) {
		return isAlive && !(neighboursCount < 2);
	}

	public boolean computeRule2(boolean isAlive, int neighboursCount) {
		return isAlive && (neighboursCount == 2 || neighboursCount == 3);
	}

	public boolean computeRule3(boolean isAlive, int neighboursCount) {
		return isAlive && !(neighboursCount > 3);
	}

	public boolean computeRule4(boolean isAlive, int neighboursCount) {
		return !isAlive && neighboursCount == 3;
	}

	public int computeNeighboursCount(Table<Integer, Integer, Boolean> matrix, Cell<Integer, Integer, Boolean> cell) {
		int count = 0;
		for (int i = cell.getRowKey() - 1; i <= cell.getRowKey() + 1; i++) {
			for (int j = cell.getColumnKey() - 1; j <= cell.getColumnKey() + 1; j++) {
				if (!(i < 0 || j < 0)) {
					count += fromNullable(matrix.get(i, j)).or(false)
						? 1 : 0;
				}
			}
		}
		return count;
	}

}
