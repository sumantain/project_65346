package com.sbw.lookon.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.sbw.lookon.utility.GPSTracker;

public class FragmentMyBusiness extends Fragment {

	private GoogleMap googleMap;

	private double latitude = 2.721159;
	private double longitude = 101.902454;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_mybusiness,
				container, false);
		initView();
		return rootView;
	}

	public static Fragment newInstance() {
		FragmentMyBusiness myFragment = new FragmentMyBusiness();
		return myFragment;
	}

	private void initView() {
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
