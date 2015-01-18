package com.wxh.project.baiduNuomi.adapter;

import com.example.android_wxh_project_baidunuomi.R;
import com.wxh.project.baiduNuomi.entity.Category;
import com.wxh.project.baiduNuomi.entity.HomeCategory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CategoryAdapter extends BaseAdapter{
	private Context context;
	private HomeCategory homeCategory;
	
	private ViewHolder viewHolder=null; //控件预定义。
	
	public CategoryAdapter(Context context, HomeCategory homeCategory) {
		super();
		this.context = context;
		this.homeCategory = homeCategory;
	}

	public int getCount() {
		return homeCategory.getCategoryList().size();
	}

	public Object getItem(int position) {
		return homeCategory.getCategoryList().get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.grid_category, parent, false);
			
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.img_gv_icon);
			viewHolder.textView = (TextView) convertView.findViewById(R.id.tx_gv_title);
			
			convertView.setTag(viewHolder);
			
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
			
		}
		
		//为控件赋值
		Category category = homeCategory.getCategoryList().get(position);
		viewHolder.imageView.setImageBitmap(category.getBitmap());
		viewHolder.textView.setText(category.getCategory_name());
		
		return convertView;
	}
	
	class ViewHolder{
		public ImageView imageView;
		public TextView textView;
	}

}
