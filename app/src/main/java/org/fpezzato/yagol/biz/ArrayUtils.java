package org.fpezzato.yagol.biz;

import org.fpezzato.yagol.model.Cell;

import java.util.Arrays;

import rx.Observable;
import rx.functions.Func1;

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

	public static Observable<Cell> getCells(final Boolean[][] matrix) {

		return Observable.range(0, matrix.length)
			.flatMap(new Func1<Integer, Observable<Cell>>() {
				@Override
				public Observable<Cell> call(final Integer rowIdx) {
					return Observable.range(0, matrix[rowIdx].length).map(new Func1<Integer, Cell>() {
						@Override
						public Cell call(Integer columnIdx) {
							return Cell.create(rowIdx, columnIdx, matrix[rowIdx][columnIdx]);
						}
					});
				}
			});
	}

}
