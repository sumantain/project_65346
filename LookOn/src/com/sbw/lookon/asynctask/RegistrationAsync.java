package com.sbw.lookon.asynctask;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.os.AsyncTask;

import com.sbw.lookon.R;
import com.sbw.lookon.helper.ApiRestClient;
import com.sbw.lookon.helper.ApiUrls;
import com.sbw.lookon.utility.Prefs;

public class RegistrationAsync extends AsyncTask<Void, Void, String> {

	private ProgressDialog pDialog;

	private Context context;
	private String str_FName = "";
	private String str_LName = "";
	private String str_Mobile = "";
	private String str_Latitude = "";
	private String str_Lonitude = "";
	private String str_DeviceID = "";

	private WebCallBackRegistration mCallBack;
	private JSONObject requestObject;
	private Prefs mPrefs;

	public RegistrationAsync(Context context, String str_FName,
			String str_LName, String str_Mobile, String str_Latitude,
			String str_Lonitude, String str_DeviceID) {
		this.context = context;
		this.str_FName = str_FName;
		this.str_LName = str_LName;
		this.str_Mobile = str_Mobile;
		this.str_Latitude=str_Latitude;
		this.str_Lonitude=str_Lonitude;
		this.str_DeviceID=str_DeviceID;
		this.mPrefs = new Prefs(context);
		this.pDialog = new ProgressDialog(context);
	}

	private void setParams() {
		try {
			requestObject = new JSONObject();
			requestObject.put(context.getString(R.string.api_access),
					ApiUrls.REGISTER);
			requestObject.put(context.getString(R.string.api_ApiKey),
					ApiUrls.Api_key);
			requestObject.put(context.getString(R.string.api_DeviceId),
					"Adhvuegvuhkoip-dcjycbwdh6teu7ytedhebxhe7tHUE734gtGYFC");
			requestObject.put(context.getString(R.string.api_FName), str_FName);
			requestObject.put(context.getString(R.string.api_LName), str_LName);
			requestObject.put(context.getString(R.string.api_PhoneNo),
					str_Mobile);
			requestObject.put(context.getString(R.string.api_Latitude),
					str_Latitude);
			requestObject.put(context.getString(R.string.api_Longitude),
					str_Lonitude);

		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (pDialog != null) {
			pDialog.setMessage("Loading... ");
			pDialog.setCancelable(false);
			pDialog.show();
		}
	}

	@Override
	protected String doInBackground(Void... params) {
		setParams();
		String responseData = ApiRestClient.postData(ApiUrls.BASE_URL,
				requestObject.toString());
		return parseJson(responseData);
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if (pDialog != null) {
			pDialog.dismiss();
		}
		if (mCallBack == null) {
			return;
		}

		if (result.equalsIgnoreCase("200")) {
			mCallBack.onSucces();
		} else {
			mCallBack.onError();
		}
	}

	private String parseJson(String responseData) {
		String requestCode = "";
		try {
			JSONObject mObject = new JSONObject(responseData);
			if (mObject.has(context.getString(R.string.api_RequestCode))) {
				requestCode = mObject.optString(context
						.getString(R.string.api_RequestCode));
			}
			if (mObject.has(context.getString(R.string.api_ResponseData))) {
				JSONObject mSuObject = mObject.optJSONObject(context
						.getString(R.string.api_ResponseData));
				if (mSuObject.has(context.getString(R.string.api_UserId))) {
					mPrefs.setUserId(mSuObject.optString(context
							.getString(R.string.api_UserId)));
				}
				if (mSuObject.has(context.getString(R.string.api_Name))) {
					mPrefs.setName(mSuObject.optString(context
							.getString(R.string.api_Name)));
				}
				if (mSuObject.has(context.getString(R.string.api_PhoneNo))) {
					mPrefs.setMobile(mSuObject.optString(context
							.getString(R.string.api_PhoneNo)));
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return requestCode;
	}

	public WebCallBackRegistration getmCallBack() {
		return mCallBack;
	}

	public void setmCallBack(WebCallBackRegistration mCallBack) {
		this.mCallBack = mCallBack;
	}

	public interface WebCallBackRegistration {
		public void onSucces();

		public void onError();
	}

}
