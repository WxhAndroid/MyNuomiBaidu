package com.wxh.project.baiduNuomi.adapter;


import java.text.DecimalFormat;
import java.util.List;

import com.example.android_wxh_project_baidunuomi.R;
import com.wxh.project.baiduNuomi.entity.Tuan;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class NearAdapter extends BaseAdapter{
	private Context context;
	private List<Tuan> tuanList;
	private DecimalFormat df = new DecimalFormat("#.00"); // 数字格式化

	private ViewHolder viewHolder; // 预定义控件对象
	public NearAdapter(Context context, List<Tuan> tuanList){
		this.context = context;
		this.tuanList = tuanList;
	}
	/**
	 * 数据项的笔数
	 */
	public int getCount() {
		return tuanList.size();
	}

	/**
	 * 获取数据项
	 */
	public Object getItem(int position) {
		return tuanList.get(position);
	}
	/**
	 * 获取数据项的ID
	 */
	public long getItemId(int position) {
		return position;
	}
	/**
	 * 获得控件，在这里绑定各种数据
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		//ListView中的项为空，就绑定。
		if(convertView==null){
			// 如果没有绑定过布局，则将自定义布局绑定到当前项的视图
			convertView = LayoutInflater.from(context).inflate(R.layout.list_like, parent, false);
			// 初次绑定视图时，初始化视图中的全部控件
			viewHolder = new ViewHolder();
			viewHolder.imgIcon = (ImageView) convertView.findViewById(R.id.img_icon);
			viewHolder.txBrandName = (TextView) convertView.findViewById(R.id.tx_brand_name);
			viewHolder.txGrouponPrice = (TextView) convertView.findViewById(R.id.tx_groupon_price);
			viewHolder.txMarketPrice = (TextView) convertView.findViewById(R.id.tx_market_price);
			// 为TextView加入删除线
			viewHolder.txMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			viewHolder.txSaleCount = (TextView) convertView.findViewById(R.id.tx_sale_count);
			viewHolder.txShortTitle = (TextView) convertView.findViewById(R.id.tx_short_title);
			
			// 将视图和控件捆绑
			convertView.setTag(viewHolder);
		}else{
			// 由于复用了convertView，所以可以同时复用布局中的控件
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		// 为控件中的布局赋值
		Tuan tuan = tuanList.get(position);
		viewHolder.imgIcon.setImageBitmap(tuan.getBitmap());
		viewHolder.txBrandName.setText(tuan.getBrand_name());
		viewHolder.txGrouponPrice.setText(String.format("￥%s", String.valueOf(df.format(tuan.getGroupon_price() / 100.0))));
		viewHolder.txMarketPrice.setText(String.valueOf(df.format(tuan.getMarket_price() / 100.0)));
		viewHolder.txSaleCount.setText(String.format("已售%s", tuan.getSale_count()));
		viewHolder.txShortTitle.setText(tuan.getShort_title());
		
		return convertView;
	}
	/**
	 * 将自定义布局中的控件封装成一个对象
	 * @author Administrator
	 *
	 */
	class ViewHolder {
		public ImageView imgIcon;
		public TextView txBrandName;
		public TextView txShortTitle;
		public TextView txGrouponPrice;
		public TextView txMarketPrice;
		public TextView txSaleCount;
	}

}
