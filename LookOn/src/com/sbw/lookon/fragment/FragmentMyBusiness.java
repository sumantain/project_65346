package com.sbw.lookon.fragment;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sbw.lookon.R;
import com.sbw.lookon.asynctask.AccountAsync;
import com.sbw.lookon.asynctask.AccountAsync.WebCallBackAccount;
import com.sbw.lookon.custom.SbTextView;
import com.sbw.lookon.dataitem.AccountVendorItem;
import com.sbw.lookon.utility.Utility;

public class FragmentMyBusiness extends Fragment {

	private GoogleMap googleMap;

	private double latitude = 2.721159;
	private double longitude = 101.902454;

	private SbTextView txt_BusinessName;
	private SbTextView txt_ContactNo;
	private SbTextView txt_Address;

	private AccountVendorItem mVendorItem = new AccountVendorItem();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_mybusiness,
				container, false);
		initView(rootView);
		apiCall();
		return rootView;
	}

	public static Fragment newInstance() {
		FragmentMyBusiness myFragment = new FragmentMyBusiness();
		return myFragment;
	}

	private void initView(View rootView) {
		txt_BusinessName = (SbTextView) rootView
				.findViewById(R.id.txt_mybusiness_name);
		txt_ContactNo = (SbTextView) rootView
				.findViewById(R.id.txt_mybusiness_number);
		txt_Address = (SbTextView) rootView
				.findViewById(R.id.txt_mybusiness_address);
		
	}

	private void apiCall() {
		if (Utility.isOnline(getActivity())) {
			AccountAsync mAsync = new AccountAsync(getActivity(), "");

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
				mAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			else
				mAsync.execute();

			mAsync.setmCallBack(new WebCallBackAccount() {

				@Override
				public void onSucces(AccountVendorItem mItem) {
					if (mItem != null) {
						mVendorItem = mItem;
						setData();
					}

				}

				@Override
				public void onError() {
					Toast.makeText(getActivity(),
							getString(R.string.something), Toast.LENGTH_SHORT)
							.show();
				}
			});

		} else {
			Toast.makeText(getActivity(),
					getString(R.string.msg_connect_network), Toast.LENGTH_SHORT)
					.show();
		}
	}

	private void setData() {
		if (!TextUtils.isEmpty(mVendorItem.getName())) {
			txt_BusinessName.setText(mVendorItem.getName().toUpperCase());
		}
		if (!TextUtils.isEmpty(mVendorItem.getAddress())) {
			txt_Address.setText(mVendorItem.getAddress());
		}
		if (!TextUtils.isEmpty(mVendorItem.getLatitude())) {
			latitude = Double.parseDouble(mVendorItem.getLatitude());
		}
		if (!TextUtils.isEmpty(mVendorItem.getLongitude())) {
			longitude = Double.parseDouble(mVendorItem.getLongitude());
		}
		loadMap();
	}

	/**
	 * 
	 */
	private void loadMap() {

		try {
			// Loading map
			initilizeMap();
			// latitude and longitude

			// create marker
			MarkerOptions marker = new MarkerOptions().position(
					new LatLng(latitude, longitude)).title("MY Location");
			// ROSE color icon
			marker.icon(BitmapDescriptorFactory
					.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
			googleMap.setMyLocationEnabled(true);

			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(new LatLng(latitude, longitude)).zoom(16).build();

			googleMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));
			// adding marker
			googleMap.addMarker(marker);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * function to load map. If map is not created it will create it for you
	 * */
	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getActivity().getFragmentManager()
					.findFragmentById(R.id.map)).getMap();

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getActivity().getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}
}
