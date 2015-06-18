package org.fpezzato.yagol;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.google.common.base.Preconditions;

/**
 * Created by francesco on 18/06/2015.
 */
public class MatrixView extends View {

	Boolean[][] mData;
	Paint mPaint;

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
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		Preconditions.checkNotNull(mData);
		Preconditions.checkArgument(mData.length > 0, "Cannot accept a matrix with no rows");
		Preconditions.checkArgument(mData[0].length > 0, "Cannot accept a matrix with no columns");

		setMeasuredDimension(mData.length, mData[0].length);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Boolean current;
		for (int i = 0; i < mData.length -1; i++) {
			for (int j = 0; j < mData[i].length -1; j++) {
				current = mData[i][j];
				if (current != null && current) {
					canvas.drawPoint(i, j, mPaint);
				}
			}
		}
	}

	public void setData(Boolean[][] data) {
		mData = data;
		invalidate();
	}
}
