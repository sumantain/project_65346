package com.sbw.lookon.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sbw.lookon.R;
import com.sbw.lookon.utility.Prefs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class ActivitySplash extends Activity {

	private static int DELAYTIME = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		initialize();

	}

	@SuppressLint("SimpleDateFormat")
	private void initialize() {
		String givenDateString = "20/01/2016";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date mDate = sdf.parse(givenDateString);
			long timeInMilliseconds = mDate.getTime();
			if (System.currentTimeMillis() < timeInMilliseconds) {
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						if (new Prefs(ActivitySplash.this).isLoggedIn()) {
							Intent mIntent = new Intent(ActivitySplash.this,
									NearbyActivity.class);
							startActivity(mIntent);
							finish();
						} else {
							Intent mIntent = new Intent(ActivitySplash.this,
									ActivitySignUp.class);
							startActivity(mIntent);
							finish();
						}

					}
				}, DELAYTIME);
			} else {
				Toast.makeText(ActivitySplash.this, "Please suscribe this App",
						Toast.LENGTH_LONG).show();
				finish();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onStart() {
		super.onStart();

	}
}
