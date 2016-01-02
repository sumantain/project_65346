package com.sbw.lookon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sbw.lookon.R;

public class ClaimAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater = null;

	public ClaimAdapter(Context context) {
		this.context = context;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return 10;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;
		BusinessViewHolder viewHolder;
		if (convertView == null) {

			v = inflater.inflate(R.layout.inflate_business_item, null);
			viewHolder = new BusinessViewHolder(v);
			v.setTag(viewHolder);
		} else {
			viewHolder = (BusinessViewHolder) v.getTag();
		}

		return v;
	}

	public class BusinessViewHolder {
		private TextView txt_header;

		public BusinessViewHolder(View base) {

		}

	}

}