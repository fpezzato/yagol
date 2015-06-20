package org.fpezzato.yagol;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.IntRange;
import android.support.annotation.VisibleForTesting;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.google.common.base.Preconditions;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by francesco on 18/06/2015.
 */
public class MatrixView extends View {

	private Boolean[][] mData;
	private Paint mPaint;
	private Paint mGuideliinesPaint;
	private int mZoomLevel = 7;

	public MatrixView(Context context) {
		super(context);
	}

	public MatrixView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	{
		//TODO - set the color from the outside.
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(getResources().getColor(android.R.color.black));

		mGuideliinesPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mGuideliinesPaint.setColor(getResources().getColor(R.color.primary_dark));
		mGuideliinesPaint.setStrokeWidth(2);


	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		Preconditions.checkNotNull(mData);
		Preconditions.checkArgument(mData.length > 0, "Cannot accept a matrix with no rows");
		Preconditions.checkArgument(mData[0].length > 0, "Cannot accept a matrix with no columns");

		setMeasuredDimension(mData.length * mZoomLevel, mData[0].length * mZoomLevel);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawGuidelines(canvas);
		Boolean current;
		for (int i = 0; i < mData.length - 1; i++) {
			for (int j = 0; j < mData[i].length - 1; j++) {
				current = mData[i][j];
				if (current != null && current) {
					drawZoomedPoint(canvas, mPaint, i, j);
				}
			}
		}
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		setAutoZoomLevel();
	}

	private void drawGuidelines(Canvas canvas) {
		canvas.drawLine(0, 0, canvas.getWidth(), 0, mGuideliinesPaint);
		canvas.drawLine(0, 0, 0, canvas.getHeight(), mGuideliinesPaint);
		canvas.drawLine(0, canvas.getWidth(), canvas.getHeight(), canvas.getWidth(), mGuideliinesPaint);
		canvas.drawLine(canvas.getHeight(), 0, canvas.getHeight(), canvas.getWidth(), mGuideliinesPaint);
	}

	@VisibleForTesting
	public void drawZoomedPoint(Canvas canvas, Paint paint, int x, int y) {

		for (int i = x * mZoomLevel; i < x * mZoomLevel + mZoomLevel; i++) {
			for (int j = y * mZoomLevel; j < y * mZoomLevel + mZoomLevel; j++) {
				canvas.drawPoint(i, j, paint);
			}
		}
	}

	public void setData(Boolean[][] data) {
		mData = data;
		invalidate();
	}

	public void setZoomLevel(@IntRange(from = 1, to = 10) int zoomLevel) {
		mZoomLevel = zoomLevel;
	}

	public void setAutoZoomLevel() {
		mZoomLevel = 1; //default
		if (mData != null && mData.length > 0) {

			WindowManager wm = (WindowManager) getContext().getSystemService(WINDOW_SERVICE);
			final DisplayMetrics displayMetrics = new DisplayMetrics();
			wm.getDefaultDisplay().getMetrics(displayMetrics);
			int screenHeight = displayMetrics.heightPixels;
			int screenWidth = displayMetrics.widthPixels;

			int dataHeight = mData.length;
			int dataWidth = mData[0].length;

			//Just try to zomm only if the screen is not too small.
			int totalPaddingHeight = getPaddingTop() + getPaddingBottom();
			int totalPaddingWidth = getPaddingLeft() + getPaddingRight();
			if (!(screenHeight < dataHeight + totalPaddingHeight || screenWidth < dataWidth + totalPaddingWidth)) {
				while (screenHeight > (dataHeight * mZoomLevel) + totalPaddingHeight
					&& screenWidth > (dataWidth * mZoomLevel) + totalPaddingWidth) {
					mZoomLevel++;
				}
			}
		}
	}
}
