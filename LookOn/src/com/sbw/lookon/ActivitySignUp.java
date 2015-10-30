package com.sbw.lookon;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.sbw.lookon.custom.SbTextView;

public class ActivitySignUp extends Activity implements OnClickListener {

	private ImageView back;
	private SbTextView header_text, bt_continue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		initialize();
	}

	private void initialize() {

		back = (ImageView) findViewById(R.id.back);

		header_text = (SbTextView) findViewById(R.id.header_text);
		bt_continue = (SbTextView) findViewById(R.id.bt_continue);
		
		
		
		
		header_text.setText("Sign Up");
		
	}

	@Override
	protected void onStart() {
		super.onStart();

		back.setOnClickListener(this);
		bt_continue.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			Toast.makeText(ActivitySignUp.this, "Work is in progress", Toast.LENGTH_SHORT).show();
			break;
		case R.id.bt_continue:
			Toast.makeText(ActivitySignUp.this, "Work is in progress", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}
}
