package com.sbw.lookon.asynctask;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.os.AsyncTask;

import com.sbw.lookon.R;
import com.sbw.lookon.dataitem.AccountVendorItem;
import com.sbw.lookon.helper.ApiRestClient;
import com.sbw.lookon.helper.ApiUrls;
import com.sbw.lookon.utility.Prefs;

public class AccountAsync extends AsyncTask<Void, Void, AccountVendorItem> {

	private ProgressDialog pDialog;

	private Context context;

	private String str_DeviceID = "";
	private String requestCode = "";

	private WebCallBackAccount mCallBack;
	private JSONObject requestObject;
	private Prefs mPrefs;

	public AccountAsync(Context context, String str_DeviceID) {
		this.context = context;

		this.str_DeviceID = str_DeviceID;
		this.mPrefs = new Prefs(context);
		this.pDialog = new ProgressDialog(context);
	}

	private void setParams() {
		try {
			requestObject = new JSONObject();
			requestObject.put(context.getString(R.string.api_access),
					ApiUrls.ACCOUNT);
			requestObject.put(context.getString(R.string.api_ApiKey),
					ApiUrls.Api_key);
			requestObject.put(context.getString(R.string.api_DeviceId),
					"Adhvuegvuhkoip-dcjycbwdh6teu7ytedhebxhe7tHUE734gtGYFC");

			/*requestObject.put(context.getString(R.string.api_UserId),
					mPrefs.getUserId());*/
			requestObject.put(context.getString(R.string.api_UserId),
					"2");

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
	protected AccountVendorItem doInBackground(Void... params) {
		setParams();
		String responseData = ApiRestClient.postData(ApiUrls.BASE_URL,
				requestObject.toString());
		return parseJson(responseData);
	}

	@Override
	protected void onPostExecute(AccountVendorItem result) {
		super.onPostExecute(result);
		if (pDialog != null) {
			pDialog.dismiss();
		}
		if (mCallBack == null) {
			return;
		}

		if (requestCode.equalsIgnoreCase("200")) {
			mCallBack.onSucces(result);
		} else {
			mCallBack.onError();
		}
	}

	private AccountVendorItem parseJson(String responseData) {
		AccountVendorItem mItem = new AccountVendorItem();
		try {
			JSONObject mObject = new JSONObject(responseData);
			if (mObject.has(context.getString(R.string.api_RequestCode))) {
				requestCode = mObject.optString(context
						.getString(R.string.api_RequestCode));
			}
			if (mObject.has(context.getString(R.string.api_ResponseData))) {
				JSONObject mSuObject = mObject.optJSONObject(context
						.getString(R.string.api_ResponseData));
				if (mSuObject.has(context.getString(R.string.api_venderInfo))) {
					JSONObject mVendorbject = mSuObject.optJSONObject(context
							.getString(R.string.api_venderInfo));
					
					
					if (mVendorbject.has(context.getString(R.string.api_Name))) {
						mItem.setName(mVendorbject.optString(context.getString(R.string.api_Name)));
					}
					if (mVendorbject.has(context.getString(R.string.api_Addres))) {
						mItem.setAddress(mVendorbject.optString(context.getString(R.string.api_Addres)));
					}
					if (mVendorbject.has(context.getString(R.string.api_PlaceId))) {
						mItem.setPlaceId(mVendorbject.optString(context.getString(R.string.api_PlaceId)));
					}
					if (mVendorbject.has(context.getString(R.string.api_type))) {
						mItem.setType(mVendorbject.optString(context.getString(R.string.api_type)));
					}
					if (mVendorbject.has(context.getString(R.string.api_Latitude))) {
						mItem.setLatitude(mVendorbject.optString(context.getString(R.string.api_Latitude)));
					}
					if (mVendorbject.has(context.getString(R.string.api_Longitude))) {
						mItem.setLongitude(mVendorbject.optString(context.getString(R.string.api_Longitude)));
					}
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return mItem;
	}

	public WebCallBackAccount getmCallBack() {
		return mCallBack;
	}

	public void setmCallBack(WebCallBackAccount mCallBack) {
		this.mCallBack = mCallBack;
	}

	public interface WebCallBackAccount {
		public void onSucces(AccountVendorItem mItem);

		public void onError();
	}
}