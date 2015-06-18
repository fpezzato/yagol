package org.fpezzato.yagol;

import com.google.common.collect.ArrayTable;

import org.fpezzato.yagol.mvp.MvpView;

/**
 * Created by francesco on 17/06/2015.
 */
public interface MainActivityView extends MvpView {

	void drawMatrix(ArrayTable<Integer, Integer, Boolean> matrix);
}
