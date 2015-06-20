package org.fpezzato.yagol;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BulletSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.common.base.Preconditions;

import org.fpezzato.yagol.mvp.MvpState;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.graphics.Typeface.ITALIC;

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

	@InjectView(R.id.activity_main_game_description)
	TextView mGameDescription;

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

		mGameDescription.setMovementMethod(LinkMovementMethod.getInstance());
		mGameDescription.setText(formatGameDescription());

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

	private SpannableStringBuilder formatGameDescription() {
		final StyleSpan italic = new StyleSpan(ITALIC);

		SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
		String s0 = getString(R.string.main_activity_game_description_0);
		stringBuilder.append(s0);
		String s1 = getString(R.string.main_activity_game_description_1);
		stringBuilder.append(s1);
		stringBuilder.setSpan(italic, s0.length(), stringBuilder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

		String s2 = getString(R.string.main_activity_game_description_2);
		stringBuilder.append(s2);

		String s3 = getString(R.string.main_activity_game_description_3);
		stringBuilder.append(s3);
		stringBuilder.setSpan(new URLSpan(getString(R.string.main_activity_game_description_3_url))
			, stringBuilder.length() - s3.length(), stringBuilder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

		stringBuilder.append("\n\n\n");
		String s4 = getString(R.string.main_activity_game_description_4);
		stringBuilder.append(s4);

		addBulletSpan(stringBuilder,
			R.string.main_activity_game_description_5_li1,
			R.string.main_activity_game_description_5_li2,
			R.string.main_activity_game_description_5_li3,
			R.string.main_activity_game_description_5_li4);


		return stringBuilder;

	}

	private void addBulletSpan(SpannableStringBuilder stringBuilder, @StringRes int...values){

		for(int value : values) {
			stringBuilder.append("\n");
			String s = getString(value);
			stringBuilder.append(s);
			stringBuilder.setSpan(new BulletSpan(), stringBuilder.length() - s.length(), stringBuilder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

		}

	}

}
