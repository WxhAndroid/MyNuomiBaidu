package com.wxh.project.baiduNuomi.task;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.wxh.project.baiduNuomi.adapter.LikeAdapter;
import com.wxh.project.baiduNuomi.common.CommonDialog;
import com.wxh.project.baiduNuomi.common.CommonImageHelper;
import com.wxh.project.baiduNuomi.common.CommonJSONHelper;
import com.wxh.project.baiduNuomi.entity.Tuan;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

public class AllTuanAsyncTask extends AsyncTask<String, Void, Void>{
	private Context context;
	private ListView lvTuan;
	private List<Tuan> list;
	private LikeAdapter adapter;
	
	

	public AllTuanAsyncTask(Context context, ListView lvTuan, List<Tuan> list, LikeAdapter adapter) {
		super();
		this.context = context;
		this.lvTuan = lvTuan;
		this.list = list;
		this.adapter = adapter;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
//		CommonDialog.showProgressDialog(context, "玩命加载中");
		CommonDialog.showCustomProgressDialog(context, "网络不好，表怪我^_^");
	}

	@Override
	protected Void doInBackground(String... params) {
		
		try {
			String json = CommonJSONHelper.getJSON(params[0]);
			JSONObject jsonObject = new JSONObject(json);
			JSONObject jsonData = jsonObject.getJSONObject("data");
			JSONArray arrayTuanList = jsonData.getJSONArray("tuan_list");
			if (arrayTuanList != null && arrayTuanList.length() > 0) {
				Tuan tuan = null;
				JSONObject obj = null;
				for (int i = 0; i < arrayTuanList.length(); i++) {
					obj = arrayTuanList.getJSONObject(i);

					tuan = new Tuan();
					tuan.setDeal_id(obj.getString("deal_id"));
					tuan.setBrand_name(obj.getString("brand_name"));
					tuan.setGroupon_price(obj.getInt("groupon_price"));
					tuan.setImage(obj.getString("image"));
					tuan.setMarket_price(obj.getInt("market_price"));
					tuan.setSale_count(obj.getInt("sale_count"));
					tuan.setShort_title(obj.getString("short_title"));
					tuan.setBitmap(CommonImageHelper.getBitmap(tuan.getImage()));

					list.add(tuan);
				}

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	@Override
	protected void onPostExecute(Void result) {
		adapter.notifyDataSetChanged();//通知数据发生改变
//		CommonDialog.closeProgressDialog();
		CommonDialog.closeCustomProgressDialog();
	}

}
