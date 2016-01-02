package com.sbw.lookon.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sbw.lookon.R;
import com.sbw.lookon.dataitem.AccountVendorItem;
import com.sbw.lookon.utility.Utility;

public class AdapterNearByBusiness extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater = null;
	private ArrayList<AccountVendorItem> mArrayList;
	private int type=0;

	public AdapterNearByBusiness(Context context,
			ArrayList<AccountVendorItem> mArrayList) {
		this.context = context;
		this.mArrayList = mArrayList;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void reload(ArrayList<AccountVendorItem> mArrayList) {
		if (mArrayList!=null) {
			this.mArrayList.addAll(mArrayList);
			notifyDataSetChanged();
		}
	}

	@Override
	public int getCount() {
		return (mArrayList == null) ? 0 : mArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return mArrayList.get(position);
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
		if (!TextUtils.isEmpty(mArrayList.get(position).getName())) {
			viewHolder.txt_Header.setText(mArrayList.get(position).getName());
		}else {
			viewHolder.txt_Header.setText("No name found");
		}
		if (!TextUtils.isEmpty(mArrayList.get(position).getAddress())) {
			viewHolder.txt_Body.setText(mArrayList.get(position).getAddress());
		}else {
			viewHolder.txt_Body.setText("No address found");
		}
		if (!TextUtils.isEmpty(mArrayList.get(position).getType())) {
			type=Integer.parseInt(mArrayList.get(position).getType());
			
			if (type==Utility.venderType.GROSSARY.ordinal()) {
				viewHolder.img_Logo.setImageResource(R.drawable.resta);
			}
			if (type==Utility.venderType.JEWELLERY.ordinal()) {
				viewHolder.img_Logo.setImageResource(R.drawable.face);
			}
			if (type==Utility.venderType.SALOON.ordinal()) {
				viewHolder.img_Logo.setImageResource(R.drawable.face);
			}
			if (type==Utility.venderType.THEATER.ordinal()) {
				viewHolder.img_Logo.setImageResource(R.drawable.icons);
			}
		}else {
			
		}
		

		return v;
	}

	public class BusinessViewHolder {
		private TextView txt_Header;
		private TextView txt_Body;
		private TextView txt_Distance;
		private ImageView img_Logo;

		public BusinessViewHolder(View base) {

			txt_Header = (TextView) base.findViewById(R.id.txt_business_header);
			txt_Body = (TextView) base.findViewById(R.id.txt_business_body);
			txt_Distance = (TextView) base
					.findViewById(R.id.txt_business_distance);
			img_Logo = (ImageView) base.findViewById(R.id.img_business_logo);
		}

	}

}
