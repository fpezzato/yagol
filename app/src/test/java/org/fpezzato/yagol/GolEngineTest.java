package org.fpezzato.yagol;

import android.test.suitebuilder.annotation.SmallTest;

import org.fpezzato.yagol.biz.GolEngine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

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

	/*@Test
	public void rule1_live_cellshould_die_tooFewNeighbours0() {
		assertFalse(mSubject.computeRule1(true, 0));
	}

	@Test
	public void rule1_live_cellshould_die_tooFewNeighbours1() {
		assertFalse(mSubject.computeRule1(true, 1));
	}

	@Test
	public void rule2_live_cellshould_live_two_live_neighbours() {
		assertTrue(mSubject.computeRule2(true, 2));
	}

	@Test
	public void rule2_live_cellshould_live_three_live_neighbours() {
		assertTrue(mSubject.computeRule2(true, 3));
	}

	@Test
	public void rule3_live_cellshould_die_too_many_neighbours() {
		assertFalse(mSubject.computeRule3(true, 4));
	}

	@Test
	public void rule3_deal_cellshould_die_too_many_neighbours() {
		assertFalse(mSubject.computeRule3(true, 4));
	}

	@Test
	public void rule4_deal_cellshould_live_three_live_neighbours() {
		assertTrue(mSubject.computeRule4(false, 3));
	}

	@Test
	public void rule4_deal_cellshould_die_four_live_neighbours() {
		assertFalse(mSubject.computeRule4(false, 4));
	}*/
/*
	private Table.Cell<Integer, Integer, String> getLiveCell(int row, int column) {
		Table.Cell liveCell = mock(Table.Cell.class);
		when(liveCell.getRowKey()).thenReturn(1);
		when(liveCell.getColumnKey()).thenReturn(1);
		when(liveCell.getValue()).thenReturn(true);
		return liveCell;
	}*/

	@Test
	public void neighbours_reveal_upperleft_neighbour() {
		//Table table = mock(Table.class);

		//given a [1,1] cell
		//Table.Cell cell = getLiveCell(1, 1);

		//when(table.get(0, 0)).thenReturn(true);

		//than
		Boolean[][] mock = new Boolean[3][3];
		 mock[0][1]=true;
		 mock[1][1]=true;
		 mock[2][1]=true;

		assertEquals(2, mSubject.computeNeighboursCount(mock, 0,0));
	}

	@Test
	public void neighbours_reveal_upperleft_neighbour2() {
		//Table table = mock(Table.class);

		//given a [1,1] cell
		//Table.Cell cell = getLiveCell(1, 1);

		//when(table.get(0, 0)).thenReturn(true);

		//than
		Boolean[][] mock = new Boolean[3][3];
		mock[0][1]=true;
		mock[1][1]=true;
		mock[2][1]=true;

		assertEquals(1, mSubject.computeNeighboursCount(mock, 2,1));
	}

	@Test
	public void neighbours_reveal_upperleft_neighbour3() {
		//Table table = mock(Table.class);

		//given a [1,1] cell
		//Table.Cell cell = getLiveCell(1, 1);

		//when(table.get(0, 0)).thenReturn(true);

		//than
		Boolean[][] mock = new Boolean[3][3];
		mock[0][1]=true;
		mock[1][1]=true;
		mock[2][1]=true;

		assertEquals(1, mSubject.computeNeighboursCount(mock, 0,1));
	}


/*	@Test
	public void neighbours_reveal_upperleft_neighbour4() {
		//Table table = mock(Table.class);

		//given a [1,1] cell
		//Table.Cell cell = getLiveCell(1, 1);

		//when(table.get(0, 0)).thenReturn(true);

		//than


		assertFalse( mSubject.computeRule3(true,4));
	}*/

	@Test
	public void neighbours_reveal_upperleft_neighbour5() {
		//Table table = mock(Table.class);

		//given a [1,1] cell
		//Table.Cell cell = getLiveCell(1, 1);

		//when(table.get(0, 0)).thenReturn(true);

		//than
		Boolean[][] mock = new Boolean[3][3];
		mock[0][1]=true;
		mock[1][1]=true;
		mock[2][1]=true;

		assertEquals(1, mSubject.computeNeighboursCount(mock, 0, 1));
	}

	/*@Test
	public void neighbours_reveal_upper_neighbour() {
		Table table = mock(Table.class);

		//given a [1,1] cell
		Table.Cell cell = getLiveCell(1, 1);

		when(table.get(0, 1)).thenReturn(true);

		//than
		assertEquals(1, mSubject.computeNeighboursCount(table, cell));
	}

	@Test
	public void neighbours_reveal_upperright_neighbour() {
		Table table = mock(Table.class);

		//given a [1,1] cell
		Table.Cell cell = getLiveCell(1, 1);

		when(table.get(0, 2)).thenReturn(true);

		//than
		assertEquals(1, mSubject.computeNeighboursCount(table, cell));
	}

	@Test
	public void neighbours_reveal_left_neighbour() {
		Table table = mock(Table.class);

		//given a [1,1] cell
		Table.Cell cell = getLiveCell(1, 1);

		when(table.get(1, 0)).thenReturn(true);

		//than
		assertEquals(1, mSubject.computeNeighboursCount(table, cell));
	}

	@Test
	public void neighbours_reveal_right_neighbour() {
		Table table = mock(Table.class);

		//given a [1,1] cell
		Table.Cell cell = getLiveCell(1, 1);

		when(table.get(1, 2)).thenReturn(true);

		//than
		assertEquals(1, mSubject.computeNeighboursCount(table, cell));
	}

	@Test
	public void neighbours_reveal_bottomleft_neighbour() {
		Table table = mock(Table.class);

		//given a [1,1] cell
		Table.Cell cell = getLiveCell(1, 1);

		when(table.get(2, 0)).thenReturn(true);

		//than
		assertEquals(1, mSubject.computeNeighboursCount(table, cell));
	}

	@Test
	public void neighbours_reveal_bottom_neighbour() {
		Table table = mock(Table.class);

		//given a [1,1] cell
		Table.Cell cell = getLiveCell(1, 1);

		when(table.get(2, 1)).thenReturn(true);

		//than
		assertEquals(1, mSubject.computeNeighboursCount(table, cell));
	}

	@Test
	public void neighbours_reveal_bottomright_neighbour() {
		Table table = mock(Table.class);

		//given a [1,1] cell
		Table.Cell cell = getLiveCell(1, 1);

		when(table.get(2, 2)).thenReturn(true);

		//than
		assertEquals(1, mSubject.computeNeighboursCount(table, cell));
	}

	@Test
	public void neighbours_count_full_neighbour() {
		Table table = mock(Table.class);

		//given a [1,1] cell
		Table.Cell cell = getLiveCell(1, 1);

		when(table.get(0, 0)).thenReturn(true);
		when(table.get(0, 1)).thenReturn(true);
		when(table.get(0, 2)).thenReturn(true);
		when(table.get(1, 0)).thenReturn(true);
		when(table.get(1, 2)).thenReturn(true);
		when(table.get(2, 0)).thenReturn(true);
		when(table.get(2, 1)).thenReturn(true);
		when(table.get(2, 2)).thenReturn(true);

		//than
		assertEquals(8, mSubject.computeNeighboursCount(table, cell));
	}

	@Test
	public void neighbours_count_no_neighbour() {
		Table table = mock(Table.class);

		//given a [1,1] cell
		Table.Cell cell = getLiveCell(1, 1);

		//than
		assertEquals(0, mSubject.computeNeighboursCount(table, cell));
	}*/

}