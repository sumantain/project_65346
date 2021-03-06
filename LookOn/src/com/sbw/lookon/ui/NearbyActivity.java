package com.sbw.lookon.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.sbw.lookon.R;
import com.sbw.lookon.adapter.AdapterNearByBusiness;
import com.sbw.lookon.asynctask.GetPlaceAsync;
import com.sbw.lookon.asynctask.GetPlaceAsync.WebCallBackGetPlace;
import com.sbw.lookon.custom.SbTextView;
import com.sbw.lookon.dataitem.AccountVendorItem;
import com.sbw.lookon.utility.GPSTracker;

public class NearbyActivity extends Activity implements OnClickListener {

	// Google Map
	private GoogleMap googleMap;
	private GPSTracker gpsTracker;

	private Context context;

	private ImageView menu;
	private SbTextView header_text;
	private ListView list_Business;
	private AdapterNearByBusiness mAdapter;

	private double latitude = 0;
	private double longitude = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nearby);
		context = NearbyActivity.this;
		initView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}

	/**
	  * 
	  */
	private void initView() {

		list_Business = (ListView) findViewById(R.id.list_business);
		setHeader();
		loadMap();
		apiCall();
		loadListView();
		list_Business.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startActivity(new Intent(NearbyActivity.this, ActivityChat.class));
				overridePendingTransition(0, 0);
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.menu:
			startActivity(new Intent(context, ActivitySettings.class));
			overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.fade_out);
			break;

		default:
			break;
		}
	}

	/**
	 * 
	 */
	private void setHeader() {
		menu = (ImageView) findViewById(R.id.menu);
		menu.setVisibility(View.VISIBLE);
		menu.setOnClickListener(this);
		header_text = (SbTextView) findViewById(R.id.header_text);
		header_text.setText(getString(R.string.find_your_business));
	}

	private void loadListView() {

		mAdapter = new AdapterNearByBusiness(context,new ArrayList<AccountVendorItem>());
		list_Business.setAdapter(mAdapter);
	}
	
	
	private void apiCall(){
		
		GetPlaceAsync mAsync=new GetPlaceAsync(context, "", String.valueOf(latitude), String.valueOf(longitude));
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			mAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		else
			mAsync.execute();
		
		
		mAsync.setmCallBack(new  WebCallBackGetPlace() {
			
			@Override
			public void onSuccess(ArrayList<AccountVendorItem> mVendorItem) {
				if (mVendorItem!=null && mVendorItem.size()>0) {
					mAdapter.reload(mVendorItem);
				}
				
			}
			
			@Override
			public void onError() {
				Toast.makeText(context, getString(R.string.something), Toast.LENGTH_SHORT).show();
			}
		});
		
	}

	/**
	 * 
	 */
	private void loadMap() {
		gpsTracker = new GPSTracker(this);
		if (gpsTracker.getIsGPSTrackingEnabled()) {

			latitude = gpsTracker.getLatitude();
			longitude = gpsTracker.getLongitude();
		} else {
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gpsTracker.showSettingsAlert();
		}
		try {
			// Loading map
			initilizeMap();
			// latitude and longitude

			// // create marker
			// MarkerOptions marker = new MarkerOptions().position(
			// new LatLng(latitude, longitude)).title("MY Location");
			// // ROSE color icon
			// marker.icon(BitmapDescriptorFactory
			// .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
			googleMap.setMyLocationEnabled(true);

			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(new LatLng(latitude, longitude)).zoom(16).build();

			googleMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));
			// adding marker
			// googleMap.addMarker(marker);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * function to load map. If map is not created it will create it for you
	 * */
	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

}
