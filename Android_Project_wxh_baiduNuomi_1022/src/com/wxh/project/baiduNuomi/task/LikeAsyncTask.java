package com.wxh.project.baiduNuomi.task;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.wxh.project.baiduNuomi.adapter.LikeAdapter;
import com.wxh.project.baiduNuomi.common.CommonImageHelper;
import com.wxh.project.baiduNuomi.common.CommonDialog;
import com.wxh.project.baiduNuomi.common.CommonJSONHelper;
import com.wxh.project.baiduNuomi.entity.Tuan;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

public class LikeAsyncTask extends AsyncTask<String, Void, List<Tuan>>{

	private Context context;
	private List<Tuan>  list;
	private ListView lvLike;
	private LikeAdapter adapter;
	
	public LikeAsyncTask(Context context, ListView lvLike) {
		this.context = context;
		this.lvLike = lvLike;
	}

	@Override
	protected void onPreExecute() {
		
//		CommonDialog.showProgressDialog(context, "加载中……");
	}

	@Override
	protected List<Tuan> doInBackground(String... param) {
		//进行Json解析
		String json = CommonJSONHelper.getJSON(param[0]);
		try {
			JSONObject jsonObj = new JSONObject(json);
			JSONObject jsonData = jsonObj.getJSONObject("data");
			JSONArray jsonArray = jsonData.getJSONArray("tuan_list");
			if(jsonArray != null && jsonArray.length() > 0){
				list = new ArrayList<Tuan>();
				Tuan tuan = null;
				JSONObject obj = new JSONObject();
				for (int i = 0; i < jsonArray.length(); i++) {
					obj = jsonArray.getJSONObject(i);
					
					tuan = new Tuan();
					//添加成员
					tuan.setDeal_id(obj.getString("deal_id"));
					tuan.setBrand_name(obj.getString("brand_name"));
					tuan.setGroupon_price(obj.getInt("groupon_price"));
					tuan.setImage(obj.getString("image"));
					tuan.setMarket_price(obj.getInt("market_price"));
					tuan.setSale_count(obj.getInt("sale_count"));
					tuan.setShort_title(obj.getString("short_title"));
					tuan.setBitmap(CommonImageHelper.getBitmap(tuan.getImage()));
//					Log.i(TAG, tuan.getBrand_name());
					
					list.add(tuan);//加入集合中
				}

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	protected void onPostExecute(List<Tuan> result) {
		adapter = new LikeAdapter(context, result);
		lvLike.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		CommonDialog.closeCustomProgressDialog();
	}

	public LikeAdapter getAdapter() {
		return adapter;
	}

	public void setAdapter(LikeAdapter adapter) {
		this.adapter = adapter;
	}

	public List<Tuan> getList() {
		return list;
	}
	
}
