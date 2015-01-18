package com.wxh.project.baiduNuomi.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.android_wxh_project_baidunuomi.R;

import com.wxh.project.baiduNuomi.adapter.FavoriteAdapter.ViewHolder;
import com.wxh.project.baiduNuomi.common.CommonConfig;
import com.wxh.project.baiduNuomi.common.CommonDialog;
import com.wxh.project.baiduNuomi.db.DBHelper;
import com.wxh.project.baiduNuomi.entity.Tuan;
import com.wxh.project.baiduNuomi.task.FavoriteTask;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class FavoriteActivity extends Activity implements  OnItemClickListener, android.view.View.OnClickListener{
	private DBHelper db;
	//控件相关
	private ListView lvFavorite;
	private Button btnFavoriteDelete;
	private static final String SQL_DELETE_TUAN_BY_DEAL_ID = "DELETE FROM table_tuan WHERE deal_id = ?";
	private List<String> listDelete = new ArrayList<String>();
	//任务相关
	private FavoriteTask task;
	private ViewHolder favoriteViewHolder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorite);
		
		db = new DBHelper(FavoriteActivity.this);
		initUI();
		initData();
	}

	private void initUI() {
		lvFavorite = (ListView) findViewById(R.id.lv_favorite);
		lvFavorite.setOnItemClickListener(this);
		btnFavoriteDelete = (Button) findViewById(R.id.btn_favorite_delete);
	}

	private void initData() {
		task = new FavoriteTask(this, lvFavorite);
		task.execute();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_favorite_menu_actionbar, menu);
		
		// 修改ActionBar的标题
		ActionBar actionBar = getActionBar();
		
		actionBar.setTitle("我的收藏");
		// 设置图标可以点击
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.backgroud_white));
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.action_favorite_edit:
			item.setIcon(R.drawable.favorite_cancel);
			Toast.makeText(this, "编辑收藏页面", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, final int position,	long id) {
		final Tuan tuan = (Tuan) lvFavorite.getItemAtPosition(position);
		favoriteViewHolder = (ViewHolder) view.getTag();//视图
//		CommonDialog.confirm(this, "确定要删除吗？", new OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				db.executeSQL(CommonConfig.SQL_DELETE_TUAN_BY_DEAL_ID, tuan.getDeal_id());
//				task.getList().remove(position);
//				task.getAdapter().notifyDataSetChanged();
//			}
//		});
		
		if(favoriteViewHolder.cbSelect.isChecked()){
//			favoriteViewHolder.cbSelect.setSelected(false);
			listDelete.add(tuan.getDeal_id());
		}else{
//			favoriteViewHolder.cbSelect.setSelected(true);
			listDelete.remove(tuan.getDeal_id());
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_favorite_delete:
			for (int i = 0; i < listDelete.size(); i++) {
				db.executeSQL(SQL_DELETE_TUAN_BY_DEAL_ID, listDelete.get(i));
			}
			break;

		default:
			break;
		}
	}

}
