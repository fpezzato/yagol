package org.fpezzato.yagol.biz;

import junit.framework.Assert;

import org.fpezzato.yagol.model.Cell;
import org.junit.Test;

/**
 * Created by francesco on 20/06/2015.
 */
public class ArrayUtilsTest {


	@Test
	public void testSerializeMatrix_lastCellCorrect() throws Exception {

		//Given a empty matrix
		Boolean[][] matrix = new Boolean[4][4];
		//with only the bottom right element  = true
		matrix[3][3] = true;

		//When scan the matrix and take the last result
		Cell last = ArrayUtils.getCells(matrix).toBlocking().last();

		Assert.assertTrue(last.getIsAlive());
	}

	@Test
	public void testSerializeMatrix_firstCellCorrect() throws Exception {

		//Given a empty matrix
		Boolean[][] matrix = new Boolean[4][4];

		//When scan the matrix and take the last result
		Cell last = ArrayUtils.getCells(matrix).toBlocking().first();
		Boolean isAlive = last.getIsAlive();
		Assert.assertTrue(isAlive == null || !isAlive);
	}

	@Test
	public void testSerializeMatrix_allCellCorrect() throws Exception {

		//Given a empty matrix
		Boolean[][] matrix = new Boolean[4][4];

		//When scan the matrix and count all the cell
		int count = ArrayUtils.getCells(matrix).count().toBlocking().first();

		//Than count should be 16 (4 x 4).
		int expectedCount = 16;
	    Assert.assertEquals(expectedCount,count);
	}

}