/**
 * 
 */
package com.sbw.lookon.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utility {

	public enum venderType {
		DEFAULT, GROSSARY, SALOON, JEWELLERY, DEFAULT1, THEATER
	}

	/**
	 * Purpose: internet checking
	 */
	public static boolean isOnline(Context mContext) {
		ConnectivityManager cm = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo info = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		// test for connection for WIFI
		if (info != null && info.isAvailable() && info.isConnected()) {
			return true;
		}
		info = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		// test for connection for Mobile
		if (info != null && info.isAvailable() && info.isConnected()) {
			return true;
		}
		return false;
	}
}
