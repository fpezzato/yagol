package org.fpezzato.yagol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.common.base.Preconditions;

import org.fpezzato.yagol.mvp.MvpState;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements MainActivityView {

	private MainActivityPresenter mPresenter;

	@InjectView(R.id.activity_main_matrix)
	MatrixView mMatrixView;

	@InjectView(R.id.activity_main_toolbar)
	Toolbar mToolbar;

	@InjectView(R.id.controller_play)
	View mPlay;

	@InjectView(R.id.controller_pause)
	View mPause;

	@InjectView(R.id.controller_reset)
	View mReset;

	public MainActivity withPresenter(MainActivityPresenter presenter) {
		this.mPresenter = presenter;
		return this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (mPresenter == null) {
			mPresenter = new MainActivityPresenterImpl();
		}

		ButterKnife.inject(this);
		mPlay.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getPresenter().playGame();
			}
		});
		mPause.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getPresenter().pauseGame();
			}
		});

		mReset.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getPresenter().resetGame();
			}
		});

		getPresenter().initialize(this, new MvpState(savedInstanceState));

		setSupportActionBar(mToolbar);

	}

	@Override
	protected void onStart() {
		super.onStart();
		Preconditions.checkNotNull(mPresenter, "please call withPresenter()");
		getPresenter().start();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getPresenter().onSaveInstanceState(new MvpState(outState));
	}

	@Override
	protected void onStop() {
		getPresenter().stop();
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public MainActivityPresenter getPresenter() {
		return mPresenter;
	}


	@Override
	public void drawMatrix(Boolean[][] matrix) {
		mMatrixView.setData(matrix);
	}


}
