package org.fpezzato.yagol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.common.base.Preconditions;

import org.fpezzato.yagol.mvp.MvpState;

public class MainActivity extends AppCompatActivity implements MainActivityView {

	private MainActivityPresenter mPresenter;

	public MainActivity withPresenter(MainActivityPresenter presenter) {
		this.mPresenter = presenter;
		return this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if(mPresenter == null){
			mPresenter = new MainActivityPresenterImpl();
		}

		getPresenter().initialize(this, new MvpState(savedInstanceState));
	}

	@Override
	protected void onStart() {
		super.onStart();
		Preconditions.checkNotNull(mPresenter, "please call withPresenter()");
		getPresenter().start();
	}

	@Override
	public void onSaveInstanceState(Bundle outState ) {
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


}
