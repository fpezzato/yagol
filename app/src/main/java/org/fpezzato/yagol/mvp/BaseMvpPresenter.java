package org.fpezzato.yagol.mvp;

/**
 * Created by francesco on 17/06/2015.
 */
public abstract class BaseMvpPresenter<VIEW extends MvpView, STATE extends MvpState> implements MvpPresenter<VIEW, STATE> {

	VIEW mMvpView;

	@Override
	public void initialize(VIEW view, STATE state) {
		mMvpView = view;
	}

	@Override
	public VIEW getMvpView() {
		return mMvpView;
	}
}
