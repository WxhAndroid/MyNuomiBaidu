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
	private DecimalFormat df = new DecimalFormat("#.00"); // ���ָ�ʽ��

	private ViewHolder viewHolder; // Ԥ����ؼ�����
	public NearAdapter(Context context, List<Tuan> tuanList){
		this.context = context;
		this.tuanList = tuanList;
	}
	/**
	 * ������ı���
	 */
	public int getCount() {
		return tuanList.size();
	}

	/**
	 * ��ȡ������
	 */
	public Object getItem(int position) {
		return tuanList.get(position);
	}
	/**
	 * ��ȡ�������ID
	 */
	public long getItemId(int position) {
		return position;
	}
	/**
	 * ��ÿؼ���������󶨸�������
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		//ListView�е���Ϊ�գ��Ͱ󶨡�
		if(convertView==null){
			// ���û�а󶨹����֣����Զ��岼�ְ󶨵���ǰ�����ͼ
			convertView = LayoutInflater.from(context).inflate(R.layout.list_like, parent, false);
			// ���ΰ���ͼʱ����ʼ����ͼ�е�ȫ���ؼ�
			viewHolder = new ViewHolder();
			viewHolder.imgIcon = (ImageView) convertView.findViewById(R.id.img_icon);
			viewHolder.txBrandName = (TextView) convertView.findViewById(R.id.tx_brand_name);
			viewHolder.txGrouponPrice = (TextView) convertView.findViewById(R.id.tx_groupon_price);
			viewHolder.txMarketPrice = (TextView) convertView.findViewById(R.id.tx_market_price);
			// ΪTextView����ɾ����
			viewHolder.txMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			viewHolder.txSaleCount = (TextView) convertView.findViewById(R.id.tx_sale_count);
			viewHolder.txShortTitle = (TextView) convertView.findViewById(R.id.tx_short_title);
			
			// ����ͼ�Ϳؼ�����
			convertView.setTag(viewHolder);
		}else{
			// ���ڸ�����convertView�����Կ���ͬʱ���ò����еĿؼ�
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		// Ϊ�ؼ��еĲ��ָ�ֵ
		Tuan tuan = tuanList.get(position);
		viewHolder.imgIcon.setImageBitmap(tuan.getBitmap());
		viewHolder.txBrandName.setText(tuan.getBrand_name());
		viewHolder.txGrouponPrice.setText(String.format("��%s", String.valueOf(df.format(tuan.getGroupon_price() / 100.0))));
		viewHolder.txMarketPrice.setText(String.valueOf(df.format(tuan.getMarket_price() / 100.0)));
		viewHolder.txSaleCount.setText(String.format("����%s", tuan.getSale_count()));
		viewHolder.txShortTitle.setText(tuan.getShort_title());
		
		return convertView;
	}
	/**
	 * ���Զ��岼���еĿؼ���װ��һ������
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
