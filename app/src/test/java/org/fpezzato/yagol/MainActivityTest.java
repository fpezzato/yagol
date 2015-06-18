package org.fpezzato.yagol;

import android.os.Bundle;
import android.test.suitebuilder.annotation.MediumTest;

import org.fpezzato.yagol.mvp.MvpState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by francesco on 17/06/2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 19)
@MediumTest
public class MainActivityTest {

	private MainActivity mActivitySubject;
	private ActivityController<MainActivity> mMainActivityActivityController;

	@Mock
	MainActivityPresenter mPresenterMock;

	@Mock
	Bundle mBundleMock;

	@Before
	public void setUp() throws Exception {
		initMocks(this);

		mMainActivityActivityController = Robolectric.buildActivity(MainActivity.class);
		mActivitySubject = mMainActivityActivityController.get();

		//inject the mock-presenter in the activity.
		mActivitySubject.withPresenter(mPresenterMock);
	}

	@Test
	public void mainActivity_initialize_presenter_TriggeredOnce() throws Exception {


		//perform the activity lifecycle until onCreate
		mMainActivityActivityController.create(mBundleMock);

		Mockito.verify(mPresenterMock, Mockito.times(1)).initialize(mActivitySubject, new MvpState(mBundleMock));
	}

	@Test
	public void mainActivity_start_presenter_TriggeredOnce() throws Exception {

		//perform the activity lifecycle until onStart
		mMainActivityActivityController.create().start();

		Mockito.verify(mPresenterMock, Mockito.times(1)).start();

	}

	@Test
	public void mainActivity_on_save_presenter_state_Triggered() throws Exception {

		//perform the activity lifecycle until OnSaveInstanceState
		mMainActivityActivityController.create().start().resume().pause().saveInstanceState(mBundleMock);

		Mockito.verify(mPresenterMock, Mockito.atLeastOnce()).onSaveInstanceState(new MvpState(mBundleMock));
	}

	@Test
	public void mainActivity_stop_presenter_Triggered() throws Exception {
		
		//perform the activity lifecycle until stop
		mMainActivityActivityController.create().start().resume().pause().saveInstanceState(mBundleMock).stop();

		Mockito.verify(mPresenterMock, Mockito.times(1)).stop();
	}

}