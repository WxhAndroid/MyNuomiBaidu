package com.wxh.project.baiduNuomi.near;

import java.text.DecimalFormat;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_wxh_project_baidunuomi.R;

class NearAdapter extends BaseExpandableListAdapter{
	private Context context;
	private NearData nearData;
	private ExpandableListView lvNear;
	private DecimalFormat df = new DecimalFormat("#.00"); // 数字格式化

	public NearAdapter(Context context, NearData nearData, ExpandableListView lvNear) {
		super();
		this.context = context;
		this.nearData = nearData;
		this.lvNear = lvNear;
	}

	@Override
	public int getGroupCount() {
		return nearData.getPoi_list().size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return nearData.getPoi_list().get(groupPosition).getTuan_list().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return nearData.getPoi_list().get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return nearData.getPoi_list().get(groupPosition).getTuan_list().get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		GroupHolder groupHolder = null;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.list_near_group, parent, false);
			groupHolder = new GroupHolder();
			groupHolder.txPoiName = (TextView) convertView.findViewById(R.id.tx_poi_name);
			groupHolder.txBizareaTitle = (TextView) convertView.findViewById(R.id.tx_bizarea_title);
			groupHolder.txPoiDistance = (TextView) convertView.findViewById(R.id.tx_poi_distance);
			convertView.setTag(groupHolder);
		}else{
			groupHolder = (GroupHolder) convertView.getTag();
		}
		PoiList poiList = nearData.getPoi_list().get(groupPosition);
		groupHolder.txPoiName.setText(poiList.getPoi_name());
		groupHolder.txBizareaTitle.setText(poiList.getBizarea_title());
		groupHolder.txPoiDistance.setText(poiList.getPoi_distance());//有问题 id那时取错了 注意命名的规范性
//		if(isExpanded){
//			lvNear.setBackgroundResource(R.drawable.tuan_details_arrows_red_down);
//		}else{
//			lvNear.setBackgroundResource(R.drawable.tuan_details_arrows_red_right);
//		}
		//默认展开
		lvNear.expandGroup(groupPosition);
//		lvNear.setGroupIndicator(null);//设置箭头图标不显示

		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		ItemHolder itemHolder = null;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.list_near_child, parent, false);
			itemHolder = new ItemHolder();
			itemHolder.image = (ImageView) convertView.findViewById(R.id.img_near_image);
			itemHolder.txShortTitle = (TextView) convertView.findViewById(R.id.tx_near_short_title);
			itemHolder.txMarketPrice = (TextView) convertView.findViewById(R.id.tx_near_market_price);
			itemHolder.txGrouponPrice = (TextView) convertView.findViewById(R.id.tx_near_groupon_price);
			itemHolder.txSaleOut = (TextView) convertView.findViewById(R.id.tx_near_sale_count);

			convertView.setTag(itemHolder);
		}else{
			itemHolder = (ItemHolder) convertView.getTag();
		}
		TuanList tuanList = nearData.getPoi_list().get(groupPosition).getTuan_list().get(childPosition);
		itemHolder.image.setImageBitmap(tuanList.getBitmap());//得到图片
		itemHolder.txShortTitle.setText(tuanList.getShort_title());
		itemHolder.txMarketPrice.setText(String.format("￥%s", String.valueOf(df.format(tuanList.getMarket_price() / 100.0))));
		itemHolder.txMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		itemHolder.txGrouponPrice.setText(String.format("￥%s", String.valueOf(df.format(tuanList.getGroupon_price() / 100.0))));
		itemHolder.txSaleOut.setText(String.format("已售%s", tuanList.getSale_count()));
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}

class GroupHolder {
	public TextView txPoiName;
	public TextView txBizareaTitle;
	public TextView txPoiDistance;
}

class ItemHolder {
	public ImageView image;
	public TextView txShortTitle;
	public TextView txMarketPrice;
	public TextView txGrouponPrice;//优惠团购价
	public TextView txSaleOut;
}
