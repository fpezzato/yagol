package org.fpezzato.yagol;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.fpezzato.yagol.mvp.MvpState;

public class MainActivity extends AppCompatActivity implements MainActivityView {

	MainActivityPresenter mPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mPresenter = new MainActivityPresenterImpl();
		mPresenter.initialize(this, new MvpState(savedInstanceState));
	}

	@Override
	protected void onStart() {
		super.onStart();
		mPresenter.start();
	}

	@Override
	public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
		super.onSaveInstanceState(outState, outPersistentState);
		mPresenter.onSaveInstanceState(new MvpState(outState));
	}

	@Override
	protected void onStop() {
		mPresenter.stop();
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

	@Override
	protected void onDestroy() {
		mPresenter = null;
		super.onDestroy();
	}
}
