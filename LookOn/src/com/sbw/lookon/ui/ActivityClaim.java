package com.sbw.lookon.ui;

import com.sbw.lookon.R;
import com.sbw.lookon.adapter.AdapterNearByBusiness;
import com.sbw.lookon.custom.SbTextView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class ActivityClaim extends Activity implements OnClickListener{

	private Context context;
	private ImageView back;
	private SbTextView header_text;
	private ListView list_Business;
	private AdapterNearByBusiness mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claim);
		context = ActivityClaim.this;
		initView();
	}

	/**
	 * 
	 */
	private void initView() {		

		list_Business = (ListView) findViewById(R.id.list_business);
		setHeader();
		populateListItem();
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
	
	/**
	 * 
	 */
	private void setHeader() {
		back = (ImageView) findViewById(R.id.back);
		back.setVisibility(View.VISIBLE);
		back.setOnClickListener(this);
		header_text = (SbTextView) findViewById(R.id.header_text);
		header_text.setText(getString(R.string.claim_your_business));
	}

	/**
	 * 
	 */
	private void populateListItem() {
		mAdapter = new AdapterNearByBusiness(context);
		list_Business.setAdapter(mAdapter);
	}

}
