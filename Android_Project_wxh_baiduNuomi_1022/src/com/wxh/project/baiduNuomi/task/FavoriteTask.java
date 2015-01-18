package com.wxh.project.baiduNuomi.task;

import java.util.ArrayList;
import java.util.List;

import com.wxh.project.baiduNuomi.adapter.FavoriteAdapter;
import com.wxh.project.baiduNuomi.adapter.LikeAdapter;
import com.wxh.project.baiduNuomi.common.CommonDialog;
import com.wxh.project.baiduNuomi.common.CommonImageHelper;
import com.wxh.project.baiduNuomi.db.DBHelper;
import com.wxh.project.baiduNuomi.entity.Tuan;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.ListView;

public class FavoriteTask extends AsyncTask<Void, Void, List<Tuan>> {
	private static final String SQL_QUERY_TUAN = "SELECT * FROM table_tuan";
	
	private Context context;
	private ListView listView;
	private DBHelper db;
	
	private List<Tuan> list;
	private FavoriteAdapter adapter;
	
	public FavoriteTask(Context context, ListView listView) {
		this.context = context;
		this.listView = listView;
		
		// 实例化数据库
		db = new DBHelper(context);
	}

	@Override
	protected void onPreExecute() {
		CommonDialog.showProgressDialog(context, "请稍候...");
	}

	@Override
	protected List<Tuan> doInBackground(Void... params) {
		list = new ArrayList<Tuan>();
		Tuan tuan = null;
		
		// 游标，用于返回数据库中的每行数据
		Cursor cursor = db.executeQuery(SQL_QUERY_TUAN);
		// 遍历查询的数据，如果有就返回true
		while (cursor.moveToNext()) {
			tuan = new Tuan();
			// 通过对应列名查询对应的值
			tuan.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
			tuan.setBrand_name(cursor.getString(cursor.getColumnIndex("brand_name")));
			tuan.setDeal_id(cursor.getString(cursor.getColumnIndex("deal_id")));
			tuan.setGroupon_price(cursor.getInt(cursor.getColumnIndex("groupon_price")));
			tuan.setImage(cursor.getString(cursor.getColumnIndex("image")));
			tuan.setMarket_price(cursor.getInt(cursor.getColumnIndex("market_price")));
			tuan.setSale_count(cursor.getInt(cursor.getColumnIndex("sale_count")));
			tuan.setShort_title(cursor.getString(cursor.getColumnIndex("short_title")));
			// 异步任务请求网络获取图片
			tuan.setBitmap(CommonImageHelper.getBitmap(tuan.getImage()));
			list.add(tuan);
		}
		
		return list;
	}
	
	@Override
	protected void onPostExecute(List<Tuan> result) {
		adapter = new FavoriteAdapter(context, result);
		listView.setAdapter(adapter);
		CommonDialog.closeProgressDialog();
	}
	
	public List<Tuan> getList() {
		return list;
	}
	
	public FavoriteAdapter getAdapter() {
		return adapter;
	}

}
