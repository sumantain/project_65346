package com.sbw.lookon.ui;

import com.sbw.lookon.R;
import com.sbw.lookon.custom.SbTextView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivitySettings extends Activity implements OnClickListener {

	private Context context;

	private ImageView back;

	private SbTextView header_text;
	private TextView txt_About;
	private TextView txt_Profile;
	private TextView txt_NotificationSound;
	private TextView txt_ClaimYourBusiness;
	private TextView txt_SendFeedBack;
	private TextView txt_RateUs;
	private TextView txt_FAQ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		context = ActivitySettings.this;
		initView();
	}

	/**
	 * 
	 */
	private void initView() {

		txt_About = (TextView) findViewById(R.id.txt_about);
		txt_Profile = (TextView) findViewById(R.id.txt_profile);
		txt_NotificationSound = (TextView) findViewById(R.id.txt_notification_sound);
		txt_ClaimYourBusiness = (TextView) findViewById(R.id.txt_claim_your_business);
		txt_SendFeedBack = (TextView) findViewById(R.id.txt_send_feedback);
		txt_RateUs = (TextView) findViewById(R.id.txt_rate_us);
		txt_FAQ = (TextView) findViewById(R.id.txt_faq);
		setHeader();
		initClickListener();
	}

	/**
	 * 
	 */
	private void setHeader() {
		back = (ImageView) findViewById(R.id.back);
		back.setVisibility(View.VISIBLE);
		back.setOnClickListener(this);
		header_text = (SbTextView) findViewById(R.id.header_text);
		header_text.setText(getString(R.string.settings));
	}

	/**
	 * 
	 */
	private void initClickListener() {

		txt_About.setOnClickListener(this);
		txt_Profile.setOnClickListener(this);
		txt_NotificationSound.setOnClickListener(this);
		txt_ClaimYourBusiness.setOnClickListener(this);
		txt_SendFeedBack.setOnClickListener(this);
		txt_RateUs.setOnClickListener(this);
		txt_FAQ.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.back:
			finish();
			break;

		case R.id.txt_about:
			Toast.makeText(context, "Work is in progress", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.txt_profile:
			startActivity(new Intent(context, ActivityMyProfile.class));
			overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.fade_out);
			break;
		case R.id.txt_notification_sound:
			Toast.makeText(context, "Work is in progress", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.txt_claim_your_business:
			startActivity(new Intent(context, ActivityClaim.class));
			overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.fade_out);
			break;
		case R.id.txt_send_feedback:
			Toast.makeText(context, "Work is in progress", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.txt_rate_us:
			Toast.makeText(context, "Work is in progress", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.txt_faq:
			Toast.makeText(context, "Work is in progress", Toast.LENGTH_SHORT)
					.show();
			break;
		default:
			break;
		}
	}
}
