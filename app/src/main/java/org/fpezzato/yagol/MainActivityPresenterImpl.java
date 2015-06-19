package org.fpezzato.yagol;

import android.os.Handler;
import android.util.Log;

import org.fpezzato.yagol.biz.GolEngine;
import org.fpezzato.yagol.mvp.BaseMvpPresenter;
import org.fpezzato.yagol.mvp.MvpState;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by francesco on 17/06/2015.
 */
public class MainActivityPresenterImpl extends BaseMvpPresenter<MainActivityView, MvpState> implements MainActivityPresenter<MainActivityView, MvpState> {

	public static final String KEY_MATRIX = MainActivityPresenterImpl.class.getCanonicalName() + ";KEY_MATRIX";

	private Boolean[][] mMatrix;

	private GolEngine mGolEngine;

	private static final int INTERVAL = 100; //100 millisec clock tick.

	final Handler handler = new Handler();
	Timer timer = new Timer(false);
	TimerTask timerTask = new TimerTask() {
		@Override
		public void run() {
			handler.post(new Runnable() {
				@Override
				public void run() {
					Log.d("->MainActivityP", "tick");
					mMatrix = mGolEngine.computeGeneration(mMatrix);
					getMvpView().drawMatrix(mMatrix);
				}
			});
		}
	};

	@Override
	public void initialize(MainActivityView view, MvpState state) {
		super.initialize(view, state);
		mGolEngine = new GolEngine();
		if (state.containsKey(KEY_MATRIX)) {
			//TODO: not the pourpose of this app. Working with serializable to save dev-time.
			//mMatrix = (ArrayTable<Integer, Integer, Boolean>) state.getSerializable(KEY_MATRIX);
		} else {
			//FIXME - just demo code
			//Range<Integer> rows = Range.closed(0, 100);
			//Range<Integer> columns = Range.closed(0, 100);
			//mMatrix = ArrayTable.create(ContiguousSet.create(rows, DiscreteDomain.integers()), ContiguousSet.create(columns, DiscreteDomain.integers()));

			mMatrix = new Boolean[100][100];
			/*Arrays.fill(mMatrix[0],false);

			Arrays.fill(mMatrix[1],false);
			Arrays.fill(mMatrix[2],false); */
/*
			//pulsar
			mMatrix[0][1] =  true;
			mMatrix[1][1] =  true;
			mMatrix[2][1] =  true;*/

			//Glider
			mMatrix[0][0] =  true;
			mMatrix[0][2] =  true;
			mMatrix[1][1] =  true;
			mMatrix[1][2] =  true;
			mMatrix[2][1] =  true;



		}
	}

	@Override
	public void onSaveInstanceState(MvpState mvpState) {
		if (mMatrix != null) {
			mvpState.putSerializable(KEY_MATRIX, mMatrix);
		}
	}

	@Override
	public void start() {
		getMvpView().drawMatrix(mMatrix);

		timer.schedule(timerTask, INTERVAL, INTERVAL);
	}

	@Override
	public void stop() {

	}


}
