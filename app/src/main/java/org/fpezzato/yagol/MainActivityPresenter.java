package org.fpezzato.yagol;

import org.fpezzato.yagol.mvp.MvpPresenter;
import org.fpezzato.yagol.mvp.MvpState;
import org.fpezzato.yagol.mvp.MvpView;

/**
 * Created by francesco on 17/06/2015.
 */
public interface MainActivityPresenter<VIEW extends MvpView, STATE extends MvpState> extends MvpPresenter<VIEW, STATE> {

	void playGame();

	void pauseGame();

	void resetGame();

	void injectRPentomino();

	void injectGlider();

	void injectDiehard();

	void injectAcorn();

}
