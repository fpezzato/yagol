package org.fpezzato.yagol;

import android.os.Bundle;
import android.test.suitebuilder.annotation.MediumTest;

import org.fpezzato.yagol.mvp.MvpState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

/**
 * Created by francesco on 17/06/2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 19)
@MediumTest
public class MainActivityTest {

	private MainActivity mMainActivity;
	private ActivityController<MainActivity> mMainActivityActivityController;

	@Before
	public void setUp() throws Exception {
		mMainActivityActivityController = Robolectric.buildActivity(MainActivity.class);
		mMainActivity = mMainActivityActivityController.get();
	}

	@Test
	public void mainActivity_initialize_presenter_TriggeredOnce() throws Exception {
		MainActivityPresenter mockPresenter = Mockito.mock(MainActivityPresenter.class);

		mMainActivity.withPresenter(mockPresenter);
		Bundle bundleMock = Mockito.mock(Bundle.class);

		mMainActivityActivityController.create(bundleMock);

		Mockito.verify(mockPresenter, Mockito.times(1)).initialize(mMainActivity, new MvpState(bundleMock));
	}

	@Test
	public void mainActivity_start_presenter_TriggeredOnce() throws Exception {
		MainActivityPresenter mockPresenter = Mockito.mock(MainActivityPresenter.class);

		mMainActivity.withPresenter(mockPresenter);
		mMainActivityActivityController.create().start().visible();

		Mockito.verify(mockPresenter, Mockito.times(1)).start();

	}


	@Test
	public void mainActivity_on_save_presenter_state_Triggered() throws Exception {
		MainActivityPresenter mockPresenter = Mockito.mock(MainActivityPresenter.class);

		mMainActivity.withPresenter(mockPresenter);
		Bundle bundleMock = Mockito.mock(Bundle.class);

		mMainActivityActivityController.create().start().resume().pause().saveInstanceState(bundleMock);

		Mockito.verify(mockPresenter, Mockito.atLeastOnce()).onSaveInstanceState(new MvpState(bundleMock));
	}

	@Test
	public void mainActivity_stop_presenter_Triggered() throws Exception {
		MainActivityPresenter mockPresenter = Mockito.mock(MainActivityPresenter.class);

		mMainActivity.withPresenter(mockPresenter);
		Bundle bundleMock = Mockito.mock(Bundle.class);

		mMainActivityActivityController.create().start().resume().pause().saveInstanceState(bundleMock).stop();

		Mockito.verify(mockPresenter, Mockito.times(1)).stop();
	}

}