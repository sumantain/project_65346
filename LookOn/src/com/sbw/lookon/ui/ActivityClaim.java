package com.sbw.lookon.ui;

import com.sbw.lookon.R;
import com.sbw.lookon.adapter.AdapterNearByBusiness;
import com.sbw.lookon.adapter.ClaimAdapter;
import com.sbw.lookon.custom.SbTextView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ActivityClaim extends Activity implements OnClickListener{

	private Context context;
	private ImageView back;
	private SbTextView header_text;
	private ListView list_Business;
	private ClaimAdapter mAdapter;

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
		list_Business.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startActivity(new Intent(ActivityClaim.this, ActivityChat.class));
				overridePendingTransition(0, 0);
			}
		});
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
		mAdapter = new ClaimAdapter(context);
		list_Business.setAdapter(mAdapter);
	}

}
