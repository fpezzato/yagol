package org.fpezzato.yagol;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.test.suitebuilder.annotation.MediumTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by francesco on 19/06/2015.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 19)
@MediumTest
public class MatrixViewTest {

	MatrixView mMatrixView;

	@Before
	public void setUp() {
		Activity activity = Robolectric.buildActivity(Activity.class).create().get();
		mMatrixView = new MatrixView(activity);
	}

	@Test
	public void matrixView_drawOnePoint_zoomLeve2() throws Exception {
		Canvas canvasMock = Mockito.mock(Canvas.class);
		Paint paintMock = Mockito.mock(Paint.class);

		mMatrixView.setZoomLevel(2);
		mMatrixView.drawZoomedPoint(canvasMock, paintMock, 0, 0);
		Mockito.verify(canvasMock).drawPoint(0, 0, paintMock);
		Mockito.verify(canvasMock).drawPoint(0, 1, paintMock);
		Mockito.verify(canvasMock).drawPoint(1, 0, paintMock);
		Mockito.verify(canvasMock).drawPoint(1, 1, paintMock);
		Mockito.verify(canvasMock, Mockito.never()).drawPoint(2, 0, paintMock);
		Mockito.verify(canvasMock, Mockito.never()).drawPoint(0, 2, paintMock);
		Mockito.verify(canvasMock, Mockito.never()).drawPoint(2, 2, paintMock);
	}

	@After
	public void tearDown() throws Exception {
		mMatrixView = null;
	}
}