package com.sbw.lookon.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.sbw.lookon.R;
import com.sbw.lookon.custom.PagerSlidingTabStrip;
import com.sbw.lookon.custom.SbTextView;
import com.sbw.lookon.fragment.FragmentMyBusiness;
import com.sbw.lookon.fragment.FragmentMyProfile;

public class ActivityMyProfile extends FragmentActivity implements
		OnClickListener {

	private Context context;
	private PagerSlidingTabStrip tabs;
	private ImageView back;
	private SbTextView header_text;
	private ViewPager pager;
	private MyPagerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myprofile);
		context = ActivityMyProfile.this;
		initView();

	}

	/**
	 * 
	 */
	private void initView() {
		setHeader();
		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		pager = (ViewPager) findViewById(R.id.pager_myprofile);
		adapter = new MyPagerAdapter(getSupportFragmentManager());

		pager.setAdapter(adapter);
		tabs.setIndicatorColorResource(R.color.color_app_header);
		tabs.setAllCaps(false);
		tabs.setUnderlineColorResource(R.color.color_app_header);
		tabs.setViewPager(pager);

	}

	/**
	 * 
	 */
	private void setHeader() {
		back = (ImageView) findViewById(R.id.back);
		back.setVisibility(View.VISIBLE);
		back.setOnClickListener(this);
		header_text = (SbTextView) findViewById(R.id.header_text);
		header_text.setText(getString(R.string.profile));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;

		default:
			break;
		}
	}

	public class MyPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = { "My Profile", "My Business" };

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				return new FragmentMyProfile();
			case 1:
				return new FragmentMyBusiness();

			}

			return null;
		}

	}

}
