package com.sbw.lookon.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.sbw.lookon.R;
import com.sbw.lookon.asynctask.RegistrationAsync;
import com.sbw.lookon.asynctask.RegistrationAsync.WebCallBackRegistration;
import com.sbw.lookon.custom.SbTextView;
import com.sbw.lookon.utility.GPSTracker;
import com.sbw.lookon.utility.Prefs;
import com.sbw.lookon.utility.Utility;

public class ActivitySignUp extends Activity implements OnClickListener {

	private SbTextView header_text;
	private SbTextView bt_continue;
	private EditText edt_FirstName;
	private EditText edt_LastName;
	private EditText edt_Mobile;

	private String str_FName = "";
	private String str_LName = "";
	private String str_Mobile = "";
	private String str_Latitude = "";
	private String str_Lonitude = "";
	private String str_DeviceID = "";

	private Context context;
	private GPSTracker gpsTracker;
	private Prefs mPrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		context = ActivitySignUp.this;
		initialize();
	}

	private void initialize() {
		mPrefs=new Prefs(context);
		header_text = (SbTextView) findViewById(R.id.header_text);
		bt_continue = (SbTextView) findViewById(R.id.bt_continue);
		edt_FirstName = (EditText) findViewById(R.id.edt_firstname);
		edt_LastName = (EditText) findViewById(R.id.edt_lastname);
		edt_Mobile = (EditText) findViewById(R.id.edt_mobile);

		header_text.setText("Sign Up");

	}

	@Override
	protected void onStart() {
		super.onStart();

		bt_continue.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.bt_continue:

			performRegistration();
			break;

		default:
			break;
		}
	}

	private void performRegistration() {

		str_FName = edt_FirstName.getText().toString().trim();
		str_LName = edt_LastName.getText().toString().trim();
		str_Mobile = edt_Mobile.getText().toString().trim();

		gpsTracker = new GPSTracker(this);

		if (gpsTracker.getIsGPSTrackingEnabled()) {
			str_Latitude = String.valueOf(gpsTracker.getLatitude());
			str_Lonitude = String.valueOf(gpsTracker.getLongitude());
		} else {
			gpsTracker.showSettingsAlert();
			return;
		}

		if (TextUtils.isEmpty(str_FName)) {
			Toast.makeText(context, "Please enter first name",
					Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(str_LName)) {
			Toast.makeText(context, "Please enter last name",
					Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(str_Mobile)) {
			Toast.makeText(context, "Please enter mobile", Toast.LENGTH_SHORT)
					.show();
			return;
		}

		apiCall();

	}

	private void apiCall() {
		if (Utility.isOnline(context)) {
			RegistrationAsync mAsync = new RegistrationAsync(context,
					str_FName, str_LName, str_Mobile, str_Latitude,
					str_Lonitude, str_DeviceID);

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
				mAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			else
				mAsync.execute();

			mAsync.setmCallBack(new WebCallBackRegistration() {

				@Override
				public void onSucces() {
					mPrefs.setLoggedIn(true);
					Toast.makeText(context, new Prefs(context).getName(),
							Toast.LENGTH_SHORT).show();
					startActivity(new Intent(context, NearbyActivity.class));
					overridePendingTransition(0, 0);
					finish();

				}

				@Override
				public void onError() {
					Toast.makeText(context, getString(R.string.something),
							Toast.LENGTH_SHORT).show();
				}
			});
		} else {
			Toast.makeText(context, getString(R.string.msg_connect_network),
					Toast.LENGTH_SHORT).show();
		}
	}
}
