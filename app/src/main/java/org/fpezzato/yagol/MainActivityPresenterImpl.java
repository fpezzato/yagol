package org.fpezzato.yagol;

import com.google.common.collect.ArrayTable;

import org.fpezzato.yagol.mvp.BaseMvpPresenter;
import org.fpezzato.yagol.mvp.MvpState;
import org.fpezzato.yagol.mvp.MvpView;

/**
 * Created by francesco on 17/06/2015.
 */
public class MainActivityPresenterImpl extends BaseMvpPresenter implements MainActivityPresenter {

	public static final String KEY_MATRIX = MainActivityPresenterImpl.class.getCanonicalName() + ";KEY_MATRIX";

	ArrayTable<Integer, Integer, Boolean> mMatrix;

	@Override
	public void initialize(MvpView view, MvpState state) {
		if (state.containsKey(KEY_MATRIX)) {
			//TODO: not the pourpose of this app. Working with serializable to save dev-time.
			mMatrix = (ArrayTable<Integer, Integer, Boolean>) state.getSerializable(KEY_MATRIX);
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

	}

	@Override
	public void stop() {

	}
}
