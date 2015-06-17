package org.fpezzato.yagol.mvp;

/**
 * Created by francesco on 17/06/2015.
 */
public interface MvpPresenter<VIEW extends MvpView, STATE extends MvpState> {

	void initialize(VIEW view, STATE state);

	void onSaveInstanceState(STATE mvpState);

	void start();

	void stop();
}
