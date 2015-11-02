package com.sbw.lookon.ui;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.sbw.lookon.R;
import com.sbw.lookon.custom.SbTextView;

public class ActivitySignUp extends Activity implements OnClickListener {

	private ImageView menu;
	private SbTextView header_text;
	private SbTextView bt_continue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		initialize();
	}

	private void initialize() {

		menu = (ImageView) findViewById(R.id.menu);
		menu.setVisibility(View.VISIBLE);
		header_text = (SbTextView) findViewById(R.id.header_text);
		bt_continue = (SbTextView) findViewById(R.id.bt_continue);

		header_text.setText("Sign Up");

	}

	@Override
	protected void onStart() {
		super.onStart();

		menu.setOnClickListener(this);
		bt_continue.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.menu:
			startActivity(new Intent(ActivitySignUp.this,
					ActivitySettings.class));
			overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.fade_out);
			break;
		case R.id.bt_continue:

			startActivity(new Intent(ActivitySignUp.this, NearbyActivity.class));
			overridePendingTransition(0, 0);
			finish();
			break;

		default:
			break;
		}
	}
}