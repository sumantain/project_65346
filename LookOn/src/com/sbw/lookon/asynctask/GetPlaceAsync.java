package com.sbw.lookon.asynctask;

import java.util.ArrayList;

import org.json.JSONArray;
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

public class GetPlaceAsync extends
		AsyncTask<Void, Void, ArrayList<AccountVendorItem>> {

	private ProgressDialog pDialog;

	private Context context;
	private WebCallBackGetPlace mCallBack;

	private String str_DeviceID = "";
	private String requestCode = "";
	private String str_Latitude = "";
	private String str_Longitude = "";

	private JSONObject requestObject;

	public GetPlaceAsync(Context context, String str_DeviceID,
			String str_Latitude, String str_Longitude) {
		this.context = context;
		this.str_DeviceID = str_DeviceID;
		this.str_Latitude = str_Latitude;
		this.str_Longitude = str_Longitude;
		this.pDialog = new ProgressDialog(context);
	}

	/**
	 * 
	 */
	private void setParams() {
		try {
			requestObject = new JSONObject();
			requestObject.put(context.getString(R.string.api_access),
					ApiUrls.GETPLACE);
			requestObject.put(context.getString(R.string.api_ApiKey),
					ApiUrls.Api_key);
			requestObject.put(context.getString(R.string.api_DeviceId),
					"Adhvuegvuhkoip-dcjycbwdh6teu7ytedhebxhe7tHUE734gtGYFC");
			requestObject.put(context.getString(R.string.api_Latitude),
					"88.665787676");
			requestObject.put(context.getString(R.string.api_Longitude),
					"93.66565656");

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
	protected ArrayList<AccountVendorItem> doInBackground(Void... params) {
		setParams();
		String responseData = ApiRestClient.postData(ApiUrls.BASE_URL,
				requestObject.toString());
		return parseJson(responseData);
	}
	
	
	@Override
	protected void onPostExecute(ArrayList<AccountVendorItem> result) {
		super.onPostExecute(result);
		
		
		if (pDialog != null) {
			pDialog.dismiss();
		}
		if (mCallBack == null) {
			return;
		}

		if (requestCode.equalsIgnoreCase("200")) {
			mCallBack.onSuccess(result);
		} else {
			mCallBack.onError();
		}
	}

	/**
	 * 
	 * @param responseData
	 * @return
	 */
	private ArrayList<AccountVendorItem> parseJson(String responseData) {
		ArrayList<AccountVendorItem> mArrayList=new ArrayList<AccountVendorItem>();
		AccountVendorItem mItem;
		try {
			JSONObject mObject = new JSONObject(responseData);
			if (mObject.has(context.getString(R.string.api_RequestCode))) {
				requestCode = mObject.optString(context
						.getString(R.string.api_RequestCode));
			}
			if (mObject.has(context.getString(R.string.api_ResponseData))) {
				JSONArray mArray = mObject.optJSONArray(context
						.getString(R.string.api_ResponseData));
				
				for (int i = 0; i < mArray.length(); i++) {
					mItem=new AccountVendorItem();
					JSONObject mVendorbject = mArray.optJSONObject(i);
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
					
					mArrayList.add(mItem);
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		return mArrayList;
	}

	
	
	
	public WebCallBackGetPlace getmCallBack() {
		return mCallBack;
	}

	public void setmCallBack(WebCallBackGetPlace mCallBack) {
		this.mCallBack = mCallBack;
	}




	/**
	 * 
	 * 
	 *
	 */
	public interface WebCallBackGetPlace {
		public void onSuccess(ArrayList<AccountVendorItem> mVendorItem);

		public void onError();
	}

}
