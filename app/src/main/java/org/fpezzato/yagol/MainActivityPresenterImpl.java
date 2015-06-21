package org.fpezzato.yagol;

import android.os.Handler;
import android.util.Log;

import org.fpezzato.yagol.biz.ArrayUtils;
import org.fpezzato.yagol.biz.GolEngine;
import org.fpezzato.yagol.model.Generation;
import org.fpezzato.yagol.mvp.BaseMvpPresenter;
import org.fpezzato.yagol.mvp.MvpState;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by francesco on 17/06/2015.
 */
public class MainActivityPresenterImpl extends BaseMvpPresenter<MainActivityView, MvpState> implements MainActivityPresenter<MainActivityView, MvpState> {

	private static final String KEY_MATRIX = MainActivityPresenterImpl.class.getCanonicalName() + ";KEY_MATRIX";
	private static final String KEY_GAME_STATE = MainActivityPresenterImpl.class.getCanonicalName() + ";KEY_GAME_STATE";
	private static final int GAME_STATE_RUNNING = 1;
	private static final int GAME_STATE_PAUSED = 0;

	private Boolean[][] mMatrix;
	private int mCurrentGameState = GAME_STATE_RUNNING;

	private GolEngine mGolEngine;

	private static final int INTERVAL = 100; //100 millisec clock tick.

	private Timer mTimer;

	private final static int MATRIX_HEIGHT = 200;
	private final static int MATRIX_WIDTH = 200;

	final Handler handler = new Handler();
	private TimerTask mTimerTask;


	@Override
	public void initialize(MainActivityView view, MvpState state) {
		super.initialize(view, state);
		mGolEngine = new GolEngine();
		if (state.containsKey(KEY_MATRIX)) {
			try {
				mMatrix = (Boolean[][]) state.getSerializable(KEY_MATRIX);
			} catch (ClassCastException ex) {
				//TODO - investigate around the potential exception.
				mMatrix = ArrayUtils.getEmptyMatrix(MATRIX_HEIGHT, MATRIX_WIDTH);
			}
		} else {
			mMatrix = ArrayUtils.getEmptyMatrix(MATRIX_HEIGHT, MATRIX_WIDTH);
			injectRPentomino();
		}
		if (state.containsKey(KEY_GAME_STATE)) {
			mCurrentGameState = state.getInt(KEY_GAME_STATE);
		}
	}

	@Override
	public void onSaveInstanceState(MvpState mvpState) {
		if (mMatrix != null) {
			mvpState.putSerializable(KEY_MATRIX, mMatrix);
		}
		mvpState.putInt(KEY_GAME_STATE, mCurrentGameState);
	}

	@Override
	public void start() {
		getMvpView().drawMatrix(mMatrix);

		if (mCurrentGameState == GAME_STATE_RUNNING) {
			playGame();
		}
	}

	@Override
	public void stop() {
		pauseGame();
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
						Generation nextGen = mGolEngine.computeGeneration(mMatrix);
						if(!nextGen.isAtLeastOneAlive()){
							pauseGame();
						}
						else {
							mMatrix = nextGen.getMatrix();
							getMvpView().drawMatrix(mMatrix);
						}
					}
				});
			}
		};

		mTimer.schedule(mTimerTask, INTERVAL, INTERVAL);
		mCurrentGameState = GAME_STATE_RUNNING;

	}


	@Override
	public void pauseGame() {
		mCurrentGameState = GAME_STATE_PAUSED;
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

	@Override
	public void injectRPentomino() {
		resetGame();
		int averageMid = getMidPoint();

		mMatrix[averageMid + 0][averageMid + 0] = false;
		mMatrix[averageMid + 0][averageMid + 1] = true;
		mMatrix[averageMid + 0][averageMid + 2] = true;

		mMatrix[averageMid + 1][averageMid + 0] = true;
		mMatrix[averageMid + 1][averageMid + 1] = true;
		mMatrix[averageMid + 1][averageMid + 2] = false;

		mMatrix[averageMid + 2][averageMid + 0] = false;
		mMatrix[averageMid + 2][averageMid + 1] = true;
		mMatrix[averageMid + 2][averageMid + 2] = false;
		getMvpView().drawMatrix(mMatrix);
	}

	@Override
	public void injectGlider() {
		resetGame();
		//Glider
		mMatrix[0][0] = true;
		mMatrix[0][2] = true;
		mMatrix[1][1] = true;
		mMatrix[1][2] = true;
		mMatrix[2][1] = true;
		getMvpView().drawMatrix(mMatrix);

	}

	@Override
	public void injectDiehard() {
		resetGame();
		int averageMid = getMidPoint();
		mMatrix[averageMid + 0][averageMid + 6] = true;
		mMatrix[averageMid + 1][averageMid + 0] = true;
		mMatrix[averageMid + 1][averageMid + 1] = true;
		mMatrix[averageMid + 2][averageMid + 1] = true;
		mMatrix[averageMid + 2][averageMid + 5] = true;
		mMatrix[averageMid + 2][averageMid + 6] = true;
		mMatrix[averageMid + 2][averageMid + 7] = true;

		getMvpView().drawMatrix(mMatrix);
	}

	private int getMidPoint() {
		return Math.min(MATRIX_HEIGHT, MATRIX_WIDTH) / 2;
	}
}
