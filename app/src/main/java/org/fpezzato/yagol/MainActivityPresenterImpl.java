package org.fpezzato.yagol;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;

import org.fpezzato.yagol.mvp.BaseMvpPresenter;
import org.fpezzato.yagol.mvp.MvpState;

/**
 * Created by francesco on 17/06/2015.
 */
public class MainActivityPresenterImpl extends BaseMvpPresenter<MainActivityView, MvpState> implements MainActivityPresenter<MainActivityView, MvpState> {

	public static final String KEY_MATRIX = MainActivityPresenterImpl.class.getCanonicalName() + ";KEY_MATRIX";

	ArrayTable<Integer, Integer, Boolean> mMatrix;

	@Override
	public void initialize(MainActivityView view, MvpState state) {
		super.initialize(view, state);
		if (state.containsKey(KEY_MATRIX)) {
			//TODO: not the pourpose of this app. Working with serializable to save dev-time.
			mMatrix = (ArrayTable<Integer, Integer, Boolean>) state.getSerializable(KEY_MATRIX);
		} else {

			//FIXME - just demo code
			Range<Integer> rows = Range.closed(0, 1000);
			Range<Integer> columns = Range.closed(0, 1000);
			mMatrix = ArrayTable.create(ContiguousSet.create(rows, DiscreteDomain.integers()), ContiguousSet.create(columns, DiscreteDomain.integers()));

			for(int i = 0; i < 90;i++){
				mMatrix.put(i,i,true);
			}
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
	}

	@Override
	public void stop() {

	}
}
