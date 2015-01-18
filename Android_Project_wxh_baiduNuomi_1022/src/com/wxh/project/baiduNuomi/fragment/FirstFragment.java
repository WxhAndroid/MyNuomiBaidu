package com.wxh.project.baiduNuomi.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.android_wxh_project_baidunuomi.R;
import com.wxh.project.baiduNuomi.activity.AllTuanActivity;
import com.wxh.project.baiduNuomi.activity.ProductDetailActivity;
import com.wxh.project.baiduNuomi.activity.CityActivity;
import com.wxh.project.baiduNuomi.adapter.LikeAdapter;
import com.wxh.project.baiduNuomi.application.MyApplication;
import com.wxh.project.baiduNuomi.common.CommonConfig;
import com.wxh.project.baiduNuomi.entity.Tuan;
import com.wxh.project.baiduNuomi.extend.GridViewForScrollView;
import com.wxh.project.baiduNuomi.extend.ListViewForScrollView;
import com.wxh.project.baiduNuomi.task.HomeCategoryAsyncTask;
import com.wxh.project.baiduNuomi.task.LikeAsyncTask;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.PageTransformer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;

public class FirstFragment extends Fragment implements OnClickListener,
OnItemClickListener ,OnPageChangeListener{
	// 控件相关
	private ListViewForScrollView lvLike;
	private Button btnAllTuan;// 全部团购
	private Button btnWatchAllTuan;// 查看全部团购
	private ImageView imgBanners;
	private ViewPager viewPager;
	private GridViewForScrollView gvCategory;
	private GridViewForScrollView gvSpecial;
	private Button btnSwitchCity;
	private String cityId;
	private String cityName;

	List<Tuan> tuanList = null;
	private Tuan tuan;
	// 适配器
	LikeAdapter likeAdapter = null;
	// 异步任务
	private LikeAsyncTask likeTask;
	private HomeCategoryAsyncTask categoryTask;
	private List<ImageView> imgViews ;
	private int [] ids = new int[] {R.drawable.guide_1, R.drawable.guide_2 , R.drawable.guide_3, R.drawable. guide_4};

	// SharedPreferences存储信息
	private static final String SP_CITY_CONFIG = "sp_city_config_info";
	private static final String SP_CITY_NAME_VALUE = "cityNameValue";
	private static final String SP_CITY_ID_VALUE = "cityIdValue";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		View view = inflater.inflate(R.layout.first_fragment, container, false);
		initUI(view);
		initData();

		return view;
	}

	private void initUI(View view) {
		lvLike = (ListViewForScrollView) view.findViewById(R.id.lv_like);
		lvLike.setOnItemClickListener(this);
		imgBanners = (ImageView) view.findViewById(R.id.image_display);// 顶部
		viewPager = (ViewPager) view.findViewById(R.id.viewpager);

		gvCategory = (GridViewForScrollView) view
				.findViewById(R.id.gv_category);// 分类
		gvSpecial = (GridViewForScrollView) view.findViewById(R.id.gv_special);// 特别

		btnAllTuan = (Button) view.findViewById(R.id.btn_all_tuan);// 全部团购
		btnAllTuan.setOnClickListener(this);
		btnWatchAllTuan = (Button) view.findViewById(R.id.btn_watch_all_tuan);
		btnWatchAllTuan.setOnClickListener(this);
		btnSwitchCity = (Button) view.findViewById(R.id.btn_city);// 切换城市
		btnSwitchCity.setOnClickListener(this);
	}

	class MyViewPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return imgViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (View) arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
//			container.addView(imgViews.get(position));
//			return imgViews.get(position); (list.get(arg1%list.size()),0)
            try{
            	((ViewPager)container).addView(imgViews.get(position % imgViews.size()), 0);
            }catch(Exception e){
            	Log.i("出现异常", "yes");
            }
			return imgViews.get(position % imgViews.size());
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
//			container.removeView( imgViews.get(position));
		}
		

	}

	private void initData() {
		// 初始化配置信息
		// cityId = "300210000";
		// cityName = "深圳";
		SharedPreferences sp = getActivity().getSharedPreferences(SP_CITY_CONFIG, getActivity().MODE_PRIVATE);
		cityName = sp.getString(SP_CITY_NAME_VALUE, "鞍山");
		cityId = sp.getString(SP_CITY_ID_VALUE, "1300030000");
		btnSwitchCity.setText(cityName);
		imgViews = new ArrayList<ImageView>();
		ImageView imgView = null;
		for (int i = 0 ; i < ids.length ; i++) {
			imgView = new ImageView(getActivity());
			imgView.setImageResource( ids[i]);
			imgViews.add(imgView);
		}

		viewPager.setAdapter(new MyViewPagerAdapter());
		// 如果数据为空则通过异步加载数据
		// 分类数据加载
		if (categoryTask == null) {
			categoryTask = new HomeCategoryAsyncTask(getActivity(), imgBanners, gvCategory, gvSpecial);
			categoryTask.execute(String.format(CommonConfig.URL_CATAGORY_BY_CITY_ID, cityId));
		} else {
			gvCategory.setAdapter(categoryTask.getCategoryAdapter());
			gvSpecial.setAdapter((ListAdapter) categoryTask.getSpecialAdapter());
			imgBanners.setImageBitmap(categoryTask.getHomeCategory().getBanners().getBitmap());
		}

		// 猜你喜欢任务加载 如果数据为空则通过异步加载数据
		if (likeTask == null) {
			likeTask = new LikeAsyncTask(getActivity(), lvLike);
			likeTask.execute(String.format(CommonConfig.URL_LIKE_BY_CITY_ID, cityId));
		} else {
			lvLike.setAdapter(likeTask.getAdapter());
		}

	}

	/*--------------------点击事件--------------------*/
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btn_watch_all_tuan:

		case R.id.btn_all_tuan:
			intent = new Intent(getActivity(), AllTuanActivity.class);
			startActivity(intent);
			break;

		case R.id.btn_city:
			intent = new Intent(getActivity(), CityActivity.class);
			startActivityForResult(intent, CityActivity.CITY_SELECTED_REQUEST);
			break;
		default:
			break;
		}

	} 

	/*--------------------------Activity结果---------------------------------*/
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CityActivity.CITY_SELECTED_REQUEST) {
			if (resultCode == CityActivity.CITY_SELECTED_RESULT) {
				cityId = data.getStringExtra(CityActivity.CITY_ID);
				cityName = data.getStringExtra(CityActivity.CITY_NAME);
				Log.i(cityId, cityName);
				// 写入存储信息中
				SharedPreferences sp = getActivity().getSharedPreferences(SP_CITY_CONFIG, getActivity().MODE_PRIVATE);
				Editor editor = sp.edit();
				editor.putString(SP_CITY_NAME_VALUE, cityName);
				editor.putString(SP_CITY_ID_VALUE, cityId);
				editor.commit();

				categoryTask = new HomeCategoryAsyncTask(getActivity(), imgBanners, gvCategory, gvSpecial);
				categoryTask.execute(String.format(CommonConfig.URL_CATAGORY_BY_CITY_ID, cityId));

				likeTask = new LikeAsyncTask(getActivity(), lvLike);
				likeTask.execute(String.format(CommonConfig.URL_LIKE_BY_CITY_ID, cityId));
				btnSwitchCity.setText(cityName);
			}
		}

	}

	/*--------------------选项点击事件--------------------*/
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		tuan = (Tuan) lvLike.getItemAtPosition(position);
//		MyApplication.setTuan(tuan);// 用Application传值
		Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
		intent.putExtra(CommonConfig.EXTRA_DETAIL_ID, tuan.getDeal_id());
		startActivity(intent);
	}

	@Override
	public void onPageScrollStateChanged(int state) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int position) {
//		if(position == 0){
//			imgViews.get(imgViews.size() - 1);
//		}else if(position == imgViews.size() -1){
//			imgViews.get(0);
//		}
	}
}
