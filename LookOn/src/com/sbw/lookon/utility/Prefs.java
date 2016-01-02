/**
 * 
 */
package com.sbw.lookon.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * @author Sumanta
 *
 */
public class Prefs {

	private Context mContext = null;

	public Prefs(Context mContext) {
		this.mContext = mContext;
	}

	private String getString(String key, String def) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(mContext);
		String s = prefs.getString(key, def);
		return s;
	}

	private void setString(String key, String val) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(mContext);
		Editor e = prefs.edit();
		e.putString(key, val);
		e.commit();
	}
	private boolean getBoolean(String key, boolean def) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(mContext);
		boolean s = prefs.getBoolean(key, def);
		return s;
	}

	private void setBoolean(String key, boolean val) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(mContext);
		Editor e = prefs.edit();
		e.putBoolean(key, val);
		e.commit();
	}

	public void clearPreference() {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(mContext);
		Editor e = prefs.edit();
		e.clear();
		e.commit();
	}

	public void setUserId(String UserId) {
		setString("UserId", UserId);
	}

	public String getUserId() {
		return getString("UserId", "");
	}

	public void setName(String Name) {
		setString("Name", Name);
	}

	public String getName() {
		return getString("Name", "");
	}

	public void setMobile(String Mobile) {
		setString("Mobile", Mobile);
	}

	public String getMobile() {
		return getString("Mobile", "");
	}
	
	public void setLoggedIn(boolean status) {
		setBoolean("LoginStatus", status);
	}

	public boolean isLoggedIn() {
		return getBoolean("LoginStatus", false);
	}
}
