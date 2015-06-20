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

		/*Observable.range(0,matrix.length)
			.f
			.flatMap(new Func1<Integer, Observable<?>>() {
			@Override
			public Observable<?> call(Integer integer) {
				return Observable.from(matrix[integer]);
			}
		});*/

		/*Observable.range(0, matrix.length)
			.map(new Func1<Integer, Boolean[]>() {
				@Override
				public Boolean[] call(Integer currentRowIndex) {
					return new Boolean[currentRowIndex];
				}
			}).flatMap(new Func1<Boolean[], Observable<Integer>>() {
			@Override
			public Observable<Integer> call(Boolean[] currentRow) {
				return Observable.range(0, currentRow.length);
			}
		});
*/

		/*Observable.from(matrix).concatMap(new Func1<Boolean[], Observable<?>>() {
			@Override
			public Observable<?> call(Boolean[] booleans) {
				return Observable.from(booleans).map(new Func1<Boolean, Object>() {
					@Override
					public Object call(Boolean aBoolean) {
						return Cell.create(0, 0, aBoolean);
					}
				});
			}
		});*/
/*
		return Observable.range(0, matrix.length)
			.map(new Func1<Integer, Pair<Integer, Boolean[]>>() {
			@Override
			public Pair<Integer, Boolean[]> call(final Integer rowIdx) {
				Observable.range(0, matrix[rowIdx].length).map(new Func1<Integer, Cell>() {
					@Override
					public Cell call(Integer columnIdx) {
						return Cell.create(rowIdx, columnIdx, matrix[rowIdx][columnIdx]);
					}
				});
			}
		});*/

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
