package com.wxh.project.baiduNuomi.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.android_wxh_project_baidunuomi.R;
import com.wxh.project.baiduNuomi.adapter.LikeAdapter;
import com.wxh.project.baiduNuomi.application.MyApplication;
import com.wxh.project.baiduNuomi.common.CommonConfig;
import com.wxh.project.baiduNuomi.entity.Tuan;
import com.wxh.project.baiduNuomi.task.AllTuanAsyncTask;
import com.wxh.project.baiduNuomi.task.LikeAsyncTask;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class AllTuanActivity extends Activity implements OnItemClickListener{
	//静态常量相关
	private static final String TAG = "AllTuanActivity";
	private static final String URL_ALL_TUAN = "http://app.nuomi.com/naserver/search/commonitem?logpage=CateList&locate_city_id=0&page_idx=%s&location=0&bizarea_list=0&sort_type=0&goods_per_page=15&fst_cattag_id=0&appid=android&tn=android&terminal_type=android&device=Genymotion+Google+Nexus+S+-+4.1.1+-+API+16+-+480x800&channel=1006900a&v=5.0.2&os=SDK16&cityid=300210000&cuid=477AD5CF1A3CC8B6073AD245387DCAA7000000000000000&uuid=ffffffff-bcbf-43b9-9c1c-96300033c587&timestamp=1411024201362&swidth=480&sheight=728&net=wifi&sign=6d72fd5841a40b4a9a5d6800e25ee602";
	
	public static final String EXTRA_DETAIL_ID = "deal_id";//交易ID
	//控件相关
	private ListView lvAllTuan;
	//变量相关
	private boolean isLastRow = false;
	private int page = 0;
	
	private List<Tuan> listTuan = new ArrayList<Tuan>();
	private LikeAdapter likeAdapter = new LikeAdapter(this, listTuan);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_tuan);
		initUI();
		initData();
	}

	private void initUI() {
		lvAllTuan = (ListView) findViewById(R.id.lv_all_tuan);
		lvAllTuan.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if(scrollState == SCROLL_STATE_IDLE && isLastRow){
					AllTuanAsyncTask task = new AllTuanAsyncTask(AllTuanActivity.this, lvAllTuan, listTuan, likeAdapter);
					task.execute(String.format(URL_ALL_TUAN, ++ page));
					// 设置底部标记状态为否
					isLastRow = false;
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0){
					isLastRow = true;
					page ++;
				}

			}
		});
		lvAllTuan.setOnItemClickListener(this);
	}
	/**
	 * 要用异步任务去下载网络数据
	 */
	private void initData(){
		lvAllTuan.setAdapter(likeAdapter);
		AllTuanAsyncTask task = new AllTuanAsyncTask(this, lvAllTuan, listTuan, likeAdapter);
		task.execute(String.format(URL_ALL_TUAN, page));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu. action_all_tuan, menu);//绑定xml文件

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("全部分类");
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;

		default:
			break;
		}
		
		return true;
	}

	/**
	 * 用Inten传值 deal_id 号给商品详情页面
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//		Intent intent = new Intent(AllTuanActivity.this, ProductInfoActivity.class);
		Intent intent = new Intent(this, ProductDetailActivity.class);
		Tuan tuan = (Tuan) lvAllTuan.getItemAtPosition(position);
//		Tuan tuan = listTuan.get(position);
//		MyApplication.setTuan(tuan);
		intent.putExtra(CommonConfig.EXTRA_DETAIL_ID, tuan.getDeal_id());
//		Log.i("交易ID", tuan.getDeal_id());
		startActivity(intent);
	}
}
