package com.wxh.project.baiduNuomi.adapter;

import com.example.android_wxh_project_baidunuomi.R;
import com.wxh.project.baiduNuomi.entity.HomeCategory;
import com.wxh.project.baiduNuomi.entity.Special;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class SpecialAdapter extends BaseAdapter{
	private Context context;
	private ViewHolder viewHolder;
	private HomeCategory data;


	public SpecialAdapter(Context context, HomeCategory data) {
		this.context = context;
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.getSpecialList().size();
	}

	@Override
	public Object getItem(int position) {
		return data.getSpecialList().get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.grid_special, parent, false);

			viewHolder = new ViewHolder();
			viewHolder.imgIcon = (ImageView) convertView.findViewById(R.id.img_special);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		Special special = data.getSpecialList().get(position);
		viewHolder.imgIcon.setImageBitmap(special.getBitmap());

		return convertView;
	}

	class ViewHolder {
		public ImageView imgIcon;
	}

}
