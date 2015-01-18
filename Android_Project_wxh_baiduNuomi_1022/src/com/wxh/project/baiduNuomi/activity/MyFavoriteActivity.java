package com.wxh.project.baiduNuomi.activity;

import com.example.android_wxh_project_baidunuomi.R;
import com.wxh.project.baiduNuomi.common.CommonDialog;
import com.wxh.project.baiduNuomi.db.DBHelper;
import com.wxh.project.baiduNuomi.entity.Tuan;
import com.wxh.project.baiduNuomi.task.FavoriteTask;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class MyFavoriteActivity extends Activity implements OnItemClickListener {
	private static final String SQL_DELETE_TUAN_BY_DEAL_ID = "DELETE FROM table_tuan WHERE deal_id = ?";
	
	private ListView lvFavorite;
	private DBHelper db;
	
	private FavoriteTask task;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorite);
		
		db = new DBHelper(this);

		initUI();
		initData();
	}

	private void initUI() {
		lvFavorite = (ListView) findViewById(R.id.lv_favorite);
		lvFavorite.setOnItemClickListener(this);
	}

	private void initData() {
		task = new FavoriteTask(this, lvFavorite);
		task.execute();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
		final Tuan tuan = (Tuan) lvFavorite.getItemAtPosition(position);
		CommonDialog.confirm(this, "È·¶¨ÒªÉ¾³ýÂð£¿", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				db.executeSQL(SQL_DELETE_TUAN_BY_DEAL_ID, tuan.getDeal_id());
				task.getList().remove(position);
				task.getAdapter().notifyDataSetChanged();
			}
		});
	}
}
