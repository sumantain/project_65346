package com.sbw.lookon.ui;

import com.sbw.lookon.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ActivitySplash extends Activity {

	private static int DELAYTIME = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		initialize();

	}

	private void initialize() {

	}

	@Override
	protected void onStart() {
		super.onStart();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent mIntent = new Intent(ActivitySplash.this,
						ActivitySignUp.class);
				startActivity(mIntent);
				finish();
			}
		}, DELAYTIME);
	}
}
