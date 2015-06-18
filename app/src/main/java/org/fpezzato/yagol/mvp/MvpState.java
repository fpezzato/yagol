package org.fpezzato.yagol.mvp;

import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by francesco on 17/06/2015.
 */
public class MvpState {

	private Bundle mOutState;

	protected Bundle getOutState() {
		if (mOutState == null) {
			mOutState = new Bundle();
		}
		return mOutState;
	}

	public boolean containsKey(String key) {
		return getOutState().containsKey(key);
	}

	public MvpState(Bundle outState) {
		mOutState = outState;
	}

	public void putInt(String key, int value) {
		getOutState().putInt(key, value);
	}

	public int getInt(String key) {
		return getOutState().getInt(key);
	}

	public int getInt(String key, int defaultValue) {
		return getOutState().getInt(key, defaultValue);
	}

	public void putString(String key, String value) {
		getOutState().putString(key, value);
	}

	public String getString(String key) {
		return getOutState().getString(key);
	}

	public String getString(String key, String defaultValue) {
		return getOutState().getString(key, defaultValue);
	}

	public void putSerializable(String key, Serializable obj) {
		//terrible things following....(the sake of this small app is not to efficently put a parceable)/
		mOutState.putSerializable(key, obj);

	}

	public Serializable getSerializable(String key) {
		//terrible things following....(the sake of this small app is not to efficently put a parceable)/
		return mOutState.getSerializable(key);
	}

	@Override
	public boolean equals(Object o) {
		return  ((MvpState)o).getOutState().equals(getOutState());
	}

	@Override
	public int hashCode() {
		return getOutState().hashCode();
	}
}
