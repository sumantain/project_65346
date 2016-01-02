package com.sbw.lookon.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sbw.lookon.R;
import com.sbw.lookon.custom.SbTextView;
import com.sbw.lookon.utility.Prefs;

public class FragmentMyProfile extends Fragment {

	private SbTextView txt_Name;
	private SbTextView txt_info;
	private SbTextView txt_Mobile;

	private Prefs mPrefs;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_myprofile,
				container, false);

		initView(rootView);

		return rootView;
	}

	private void initView(View rootView) {
		mPrefs = new Prefs(getActivity());
		txt_Name = (SbTextView) rootView.findViewById(R.id.txt_myprofile_name);
		txt_info = (SbTextView) rootView.findViewById(R.id.txt_myprofile_info);
		txt_Mobile = (SbTextView) rootView
				.findViewById(R.id.txt_myprofile_phone);
		txt_Name.setText(mPrefs.getName());
		txt_Mobile.setText(mPrefs.getMobile());
	}

}
