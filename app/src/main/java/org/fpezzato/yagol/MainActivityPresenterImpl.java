package org.fpezzato.yagol;

import android.os.Handler;
import android.util.Log;

import org.fpezzato.yagol.biz.ArrayUtils;
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

	private Timer mTimer;

	private final static int MATRIX_HEIGHT = 100;
	private final static int MATRIX_WIDTH = 100;

	final Handler handler = new Handler();
	private TimerTask mTimerTask;


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

			mMatrix = ArrayUtils.getEmptyMatrix(MATRIX_HEIGHT, MATRIX_WIDTH);
			/*Arrays.fill(mMatrix[0],false);

			Arrays.fill(mMatrix[1],false);
			Arrays.fill(mMatrix[2],false); */
			//pulsar
			/*mMatrix[0][1] =  true;
			mMatrix[1][1] =  true;
			mMatrix[2][1] =  true;
*/
		/*	//Glider
			mMatrix[0][0] = true;
			mMatrix[0][2] = true;
			mMatrix[1][1] = true;
			mMatrix[1][2] = true;
			mMatrix[2][1] = true;
*/

			//The R-pentomino
			mMatrix[30][30] = false;
			mMatrix[30][31] = true;
			mMatrix[30][32] = true;

			mMatrix[31][30] = true;
			mMatrix[31][31] = true;
			mMatrix[31][32] = false;

			mMatrix[32][30] = false;
			mMatrix[32][31] = true;
			mMatrix[32][32] = false;

			/*

			/*Random r= new Random();
			for(int i = 0; i < 100;i++){

				int i1 = r.nextInt(180);
				int i2 = r.nextInt(180);
				int i3 = i1+r.nextInt(2);
				int i4 = i2+r.nextInt(2);
				int i5 = i1+r.nextInt(2);
				int i6 = i2+r.nextInt(2);


				mMatrix[i1][i2] = true;
				mMatrix[i3][i4] = true;
				mMatrix[i5][i6] = true;
			}*/
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

		//timer.schedule(timerTask, INTERVAL, INTERVAL);
	}

	@Override
	public void stop() {

	}

	@Override
	public void playGame() {
		pauseGame();
		mTimer = new Timer(false);

		mTimerTask = new TimerTask() {
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

		mTimer.schedule(mTimerTask, INTERVAL, INTERVAL);
	}

	@Override
	public void pauseGame() {
		if (mTimer != null) {
			mTimer.cancel();
			mTimer.purge();
			mTimerTask.cancel();
		}
	}

	@Override
	public void resetGame() {
		pauseGame();
		mMatrix = ArrayUtils.getEmptyMatrix(MATRIX_HEIGHT, MATRIX_WIDTH);
	}
}
