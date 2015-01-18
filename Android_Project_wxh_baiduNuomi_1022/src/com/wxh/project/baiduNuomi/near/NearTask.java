package com.wxh.project.baiduNuomi.near;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.android_wxh_project_baidunuomi.R;
import com.wxh.project.baiduNuomi.common.CommonDialog;
import com.wxh.project.baiduNuomi.common.CommonImageHelper;
import com.wxh.project.baiduNuomi.common.CommonJSONHelper;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

public class NearTask extends AsyncTask<String, Void, NearData>{
	private Context context;
	private NearData data;
	private NearAdapter adapter;
	private ExpandableListView lvNear;
	
	public NearTask(Context context, ExpandableListView lvNear) {
		super();
		this.context = context;
		this.lvNear	 = lvNear;
	}
	

	public NearAdapter getAdapter() {
		return adapter;
	}

	public NearData getData() {
		return data;
	}

	@Override
	protected void onPreExecute() {
		CommonDialog.showCustomProgressDialog(context, "附近页面详情加载中");
	}

	@Override
	protected NearData doInBackground(String... params) {
		data = new NearData();
		try {
			String json = CommonJSONHelper.getJSON(params[0]);
			Log.i("地址", json);
			JSONObject jsonObj = new JSONObject(json);
			JSONObject jsonData = jsonObj.getJSONObject("data");
			Log.i("地址", jsonData.toString());
			if(jsonData != null && jsonData.length() > 0){
//				Log.i("进入解析", "yes");
				data.setPoi_num(jsonData.getInt("poi_num"));
				data.setTuan_num(jsonData.getInt("tuan_num"));
				data.setPoi_list(parsePoiList(jsonData.getJSONArray("poi_list")));
//				Log.i("poi_name", String.valueOf(jsonData.getInt("poi_num")));
//				Log.i("tuan_num", String.valueOf(jsonData.getInt("tuan_num")));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return data;
	}


	/**
	 * 解析 PoiList
	 * @param jsonArray
	 * @return
	 * @throws JSONException 
	 */
	private List<PoiList> parsePoiList(JSONArray jsonArray) throws JSONException {
		Log.i("进入解析 PoiList", "yes");
		List<PoiList> list = new ArrayList<PoiList>();
		if(jsonArray != null && jsonArray.length() > 0){
			PoiList poiList = null;
			JSONObject obj = null;
			for(int i = 0;i < jsonArray.length(); i ++){
				obj = jsonArray.getJSONObject(i);
				poiList = new PoiList();
				poiList.setBizarea_title(obj.getString("bizarea_title"));
				poiList.setLat(obj.getInt("lat"));
				poiList.setPoi_distance(obj.getString("poi_distance"));
				poiList.setPoi_name(obj.getString("poi_name"));
				poiList.setTuan_more(obj.getString("tuan_more"));
				poiList.setTuan_num(obj.getInt("tuan_num"));

				poiList.setTuan_list(parseTuanList(obj.getJSONArray("tuan_list")));
				
//				Log.i("poi_name", obj.getString("poi_name"));//打印内容头部
//				Log.i("bizarea_title", obj.getString("bizarea_title"));
				Log.i("距离poi_distance", obj.getString("poi_distance"));
				
				list.add(poiList);
			}
		}
		return list;
	}


	/**
	 * 解析TuanList
	 * @param jsonArray
	 * @return
	 * @throws JSONException
	 */
	private List<TuanList> parseTuanList(JSONArray jsonArray) throws JSONException {
		Log.i("进入解析TuanList", "yes");
		List<TuanList> list = new ArrayList<TuanList>();
		if(jsonArray != null && jsonArray.length() > 0){
			TuanList tuanList = null;
			JSONObject obj = null;
			for (int i = 0; i < jsonArray.length(); i++) {
				obj = jsonArray.getJSONObject(i);
				tuanList = new TuanList();
				Log.i("进入解析TuanList对象", "yes");
				tuanList.setAppoint(obj.getString("appoint"));
				tuanList.setBizarea(obj.getString("bizarea"));
				tuanList.setBought_weekly(obj.getInt("bought_weekly"));
				tuanList.setBrand_name(obj.getString("brand_name"));
				tuanList.setComment_num(obj.getInt("comment_num"));
				tuanList.setDeal_id(obj.getString("deal_id"));
				tuanList.setDistance(obj.getString("distance"));
				tuanList.setGroupon_price(obj.getInt("groupon_price"));
				tuanList.setGroupon_type(obj.getInt("groupon_type"));
				tuanList.setIfvirtual(obj.getString("ifvirtual"));
				tuanList.setImage(obj.getString("image"));//设置图片
				tuanList.setBitmap(CommonImageHelper.getBitmap(tuanList.getImage()));//设置图片显示
				tuanList.setIs_flash(obj.getString("is_flash"));
				tuanList.setIs_latest(obj.getString("is_latest"));
				tuanList.setMarket_price(obj.getInt("market_price"));
				tuanList.setNew_groupon(obj.getInt("new_groupon"));
				tuanList.setPay_end_time(obj.getLong("pay_end_time"));
				tuanList.setPay_start_time(obj.getLong("pay_start_time"));
				tuanList.setReason(obj.getString("reason"));
				tuanList.setS(obj.getString("s"));
				tuanList.setSale_count(obj.getInt("sale_count"));
				tuanList.setSale_out(obj.getInt("sale_out"));
				tuanList.setScore(obj.getInt("score"));
				tuanList.setShort_title(obj.getString("short_title"));
				tuanList.setVirtual_redirect_url(obj.getString("virtual_redirect_url"));

//				tuanList.setFavour_list(parseFavourList(obj.getJSONArray("favour_list")));
				//
//				Log.i("附近：deal_id", obj.getString("deal_id"));
//				Log.i("image地址", obj.getString("image"));
//				Log.i("short_title", obj.getString("short_title"));
				Log.i("市场价market_price", String.valueOf(obj.getInt("market_price")));
				Log.i("groupon_price", String.valueOf(obj.getInt("groupon_price")));
				Log.i("sale_out", String.valueOf(obj.getInt("sale_count")));
				list.add(tuanList);
			}
		}
		return list;
	}

	/**
	 * 解析 FavourList
	 * @param jsonArray
	 * @return
	 * @throws JSONException 
	 */
//	private List<FavourList> parseFavourList(JSONArray jsonArray) throws JSONException {
//		List<FavourList> list = new ArrayList<FavourList>();
//		if(jsonArray != null && jsonArray.length() > 0){
//			FavourList favourList = null;
//			JSONObject obj = null;
//			for (int i = 0; i < jsonArray.length(); i++) {
//				obj = jsonArray.getJSONObject(i);
//				favourList = new FavourList();
//				favourList.setDeal_id(obj.getString("deal_id"));
//				favourList.setList_text(obj.getString("list_text"));
//				favourList.setPrice(obj.getInt("price"));
//				favourList.setPrice_tag_id(obj.getString("price_tag_id"));
//				favourList.setReductionAmount(obj.getInt("reductionAmount"));
//
//				list.add(favourList);
//			}
//		}
//		return list;
//	}

	@Override
	protected void onPostExecute(NearData result) {
		adapter = new NearAdapter(context, result, lvNear);
		lvNear.setAdapter(adapter);
		adapter.notifyDataSetChanged();//通知数据发生改变
		CommonDialog.closeCustomProgressDialog();
		
	}
	


}
