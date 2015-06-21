package org.fpezzato.yagol;

import android.test.suitebuilder.annotation.SmallTest;

import org.fpezzato.yagol.biz.GolEngine;
import org.fpezzato.yagol.model.Generation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by francesco on 17/06/2015.
 */
@SmallTest
@RunWith(MockitoJUnitRunner.class)
public class GolEngineTest {
	GolEngine mSubject;

	/**
	 * Any live cell with fewer than two live neighbours dies, as if caused by under-population.
	 * Any live cell with two or three live neighbours lives on to the next generation.
	 * Any live cell with more than three live neighbours dies, as if by overcrowding.
	 * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
	 */

	@Before
	public void setUp() throws Exception {
		mSubject = new GolEngine();
	}

	private Boolean[][] getFullLiveMatrix(int sizeX, int sizeY) {
		Boolean[][] result = new Boolean[sizeX][sizeY];
		for (int i = 0; i < sizeX; i++) {
			Arrays.fill(result[i], true);
		}
		return result;
	}

	@Test
	public void neighbours_count_topleft_fullmatrix() {
		//Given a full alive 3x3 matrix
		Boolean[][] matrix = getFullLiveMatrix(3, 3);

		//When compute number of Neighbours of cell 0,0
		int count = mSubject.computeBestEffortNeighboursCount(matrix, 0, 0);

		//Then count should be 3
		Assert.assertEquals(count, 3);
	}

	@Test
	public void neighbours_count_topmid_fullmatrix() {
		//Given a full alive 3x3 matrix
		Boolean[][] matrix = getFullLiveMatrix(3, 3);

		//When compute number of Neighbours of cell 0,1
		int count = mSubject.computeBestEffortNeighboursCount(matrix, 0, 1);

		//Then count should be >=3
		Assert.assertTrue(count > 3);
	}

	@Test
	public void neighbours_count_topright_fullmatrix() {
		//Given a full alive 3x3 matrix
		Boolean[][] matrix = getFullLiveMatrix(3, 3);

		//When compute number of Neighbours of cell 0,2
		int count = mSubject.computeBestEffortNeighboursCount(matrix, 0, 2);

		//Then count should be 3
		Assert.assertEquals(count, 3);
	}

	@Test
	public void neighbours_count_midleft_fullmatrix() {
		//Given a full alive 3x3 matrix
		Boolean[][] matrix = getFullLiveMatrix(3, 3);

		//When compute number of Neighbours of cell 1,0
		int count = mSubject.computeBestEffortNeighboursCount(matrix, 1, 0);
		//Then count should be >=3
		Assert.assertTrue(count > 3);
	}

	@Test
	public void neighbours_count_mid_fullmatrix() {
		//Given a full alive 3x3 matrix
		Boolean[][] matrix = getFullLiveMatrix(3, 3);

		//When compute number of Neighbours of cell 1,1
		int count = mSubject.computeBestEffortNeighboursCount(matrix, 1, 1);

		//Then count should be >=3
		Assert.assertTrue(count > 3);
	}

	@Test
	public void neighbours_count_midright_fullmatrix() {
		//Given a full alive 3x3 matrix
		Boolean[][] matrix = getFullLiveMatrix(3, 3);

		//When compute number of Neighbours of cell 1,2
		int count = mSubject.computeBestEffortNeighboursCount(matrix, 1, 2);

		//Then count should be >=3
		Assert.assertTrue(count > 3);
	}

	@Test
	public void neighbours_count_bottomleft_fullmatrix() {
		//Given a full alive 3x3 matrix
		Boolean[][] matrix = getFullLiveMatrix(3, 3);

		//When compute number of Neighbours of cell 2,0
		int count = mSubject.computeBestEffortNeighboursCount(matrix, 2, 0);

		//Then count should be 3
		Assert.assertEquals(count, 3);
	}

	@Test
	public void neighbours_count_midbottom_fullmatrix() {
		//Given a full alive 3x3 matrix
		Boolean[][] matrix = getFullLiveMatrix(3, 3);

		//When compute number of Neighbours of cell 2,1
		int count = mSubject.computeBestEffortNeighboursCount(matrix, 2, 1);

		//Then count should be >=3
		Assert.assertTrue(count > 3);
	}

	@Test
	public void neighbours_count_bottomright_fullmatrix() {
		//Given a full alive 3x3 matrix
		Boolean[][] matrix = getFullLiveMatrix(3, 3);

		//When compute number of Neighbours of cell 2,2
		int count = mSubject.computeBestEffortNeighboursCount(matrix, 2, 2);

		//Then count should be 3
		Assert.assertEquals(count, 3);
	}

	@Test
	public void neighbours_count_topleft_centra_row_full() {

		///Given an 3x3 matrix with central vertical row alive
		Boolean[][] matrix = new Boolean[3][3];
		matrix[0][1] = true;
		matrix[1][1] = true;
		matrix[2][1] = true;

		//When compute neighbour count
		int count = mSubject.computeBestEffortNeighboursCount(matrix, 0, 0);

		//Then count should be 2
		assertEquals(2, count);
	}

	@Test
	public void neighbours_reveal_bottomleft_neighbour() {
		///Given an 3x3 matrix with central vertical row alive
		Boolean[][] matrix = new Boolean[3][3];
		matrix[0][1] = true;
		matrix[1][1] = true;
		matrix[2][1] = true;

		//When compute neighbour count
		int count = mSubject.computeBestEffortNeighboursCount(matrix, 2, 1);

		//Then count should be 1
		assertEquals(1, count);
	}

	@Test
	public void neighbours_reveal_uppermid_neighbour3() {
		///Given an 3x3 matrix with central vertical row alive
		Boolean[][] matrix = new Boolean[3][3];
		matrix[0][1] = true;
		matrix[1][1] = true;
		matrix[2][1] = true;

		//When compute neighbour count
		int count = mSubject.computeBestEffortNeighboursCount(matrix, 0, 1);

		//Then count should be 1
		assertEquals(1, count);
	}


	@Test
	public void neighbours_count_no_neighbour() {
		///Given an 3x3 matrix  empty
		Boolean[][] matrix = new Boolean[3][3];
		//When compute neighbour count for any cell
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				assertEquals(0, mSubject.computeBestEffortNeighboursCount(matrix, i, j));
			}
		}
	}

	@Test
	public void cellCreationTest_vertical_pulsar_to_horizontal_pulsar(){
		///Given an 3x3 matrix
		Boolean[][] matrix = new Boolean[3][3];
		matrix[0][1] = true;
		matrix[1][1] = true;
		matrix[2][1] = true;

		//When compute one generation
		Generation nextGen = mSubject.computeGeneration(matrix);

		assertEquals(nextGen.getMatrix()[0][0],false);
		assertEquals(nextGen.getMatrix()[0][1],false);
		assertEquals(nextGen.getMatrix()[0][2],false);
		assertEquals(nextGen.getMatrix()[1][0],true);
		assertEquals(nextGen.getMatrix()[1][1],true);
		assertEquals(nextGen.getMatrix()[1][2],true);
		assertEquals(nextGen.getMatrix()[2][0],false);
		assertEquals(nextGen.getMatrix()[2][1],false);
		assertEquals(nextGen.getMatrix()[2][2],false);
	}
}