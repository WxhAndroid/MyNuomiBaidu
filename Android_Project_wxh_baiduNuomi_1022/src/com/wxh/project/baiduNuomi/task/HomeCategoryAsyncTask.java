package com.wxh.project.baiduNuomi.task;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.wxh.project.baiduNuomi.adapter.CategoryAdapter;
import com.wxh.project.baiduNuomi.adapter.SpecialAdapter;
import com.wxh.project.baiduNuomi.common.CommonDialog;
import com.wxh.project.baiduNuomi.common.CommonImageHelper;
import com.wxh.project.baiduNuomi.common.CommonJSONHelper;
import com.wxh.project.baiduNuomi.entity.Banners;
import com.wxh.project.baiduNuomi.entity.Category;
import com.wxh.project.baiduNuomi.entity.HomeCategory;
import com.wxh.project.baiduNuomi.entity.Special;
import com.wxh.project.baiduNuomi.extend.GridViewForScrollView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class HomeCategoryAsyncTask extends AsyncTask<String, Void, HomeCategory>{
	private Context context;
	private ImageView imgBanners;
	private GridViewForScrollView gvSpecial;
	
	private GridViewForScrollView gvCategory;
	private HomeCategory homeCategory;
	private CategoryAdapter categoryAdapter;
	private SpecialAdapter specialAdapter;

	public HomeCategoryAsyncTask(Context context, ImageView imgBanners,
			GridViewForScrollView gvCategory, GridViewForScrollView gvSpecial) {
		super();
		this.context = context;
		this.imgBanners = imgBanners;
		this.gvCategory = gvCategory;
		this.gvSpecial = gvSpecial;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		CommonDialog.showCustomProgressDialog(context, "努力加载中……");//加载动画
	}

	@SuppressWarnings("null")
	@SuppressLint("ShowToast")
	@Override
	protected HomeCategory doInBackground(String... params) {
		String json = CommonJSONHelper.getJSON(params[0]);
		if(json != null){
			homeCategory = new HomeCategory();
			try {
				//解析顶部图片
				JSONObject jsonObject = new JSONObject(json);
				JSONObject jsonData = jsonObject.getJSONObject("data");
				JSONArray arrayBanners = jsonData.getJSONArray("banners");
				if(arrayBanners != null && arrayBanners.length() > 0){
					JSONObject jsonBanners = null;
					Banners banners = null;
					for (int i = 0; i < arrayBanners.length(); i++) {
						jsonBanners = arrayBanners.getJSONObject(i);
						banners = new Banners();
						
						banners.setPicture_url(jsonBanners.getString("picture_url"));
						banners.setBitmap(CommonImageHelper.getBitmap(banners.getPicture_url()));
						// 设置HomeCategory中banners的属性值
						homeCategory.setBanners(banners);
						//打印log信息
//						Log.i("主页分类数据", banners.getPicture_url());
					}
				}
				//解析中间部分 美食 图片等信息
				JSONArray arrayCategory = jsonData.getJSONArray("category");
				if (arrayCategory != null && arrayCategory.length() > 0) {
					Category category = null;
					List<Category> categoryList = new ArrayList<Category>();
					for (int i = 0; i < arrayCategory.length(); i++) {
						JSONObject obj = arrayCategory.getJSONObject(i);

						category = new Category();
						category.setCategory_picurl(obj.getString("category_picurl"));
						category.setCategory_name(obj.getString("category_name"));
						category.setBitmap(CommonImageHelper.getBitmap(category.getCategory_picurl()));

						//打印log信息
						Log.i("种类分类名称",category.getCategory_name());
//						Log.i("种类分类图片地址",category.getCategory_picurl());
						//添加到集合
						categoryList.add(category);
						
						homeCategory.setCategoryList(categoryList);
					}
				}
				
				//解析特别推荐
				JSONArray arraySpecial = jsonData.getJSONArray("special");
				if (arraySpecial != null && arraySpecial.length() > 0) {
					Special special = null;
					List<Special> specialList = new ArrayList<Special>();
					for (int i = 0; i < arraySpecial.length(); i++) {
						JSONObject obj = arraySpecial.getJSONObject(i);
						special = new Special();
						special.setPicture_url(obj.getString("picture_url"));
						special.setBitmap(CommonImageHelper.getBitmap(special
								.getPicture_url()));
						
//						Log.i("特别推荐", special.getPicture_url());
						
						specialList.add(special);
						
						homeCategory.setSpecialList(specialList);
					}
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
//		}else{
//			Toast.makeText(context, "没有可以解析的数据", Toast.LENGTH_SHORT);
//		}

		return homeCategory;
	}
	
	@Override
	protected void onPostExecute(HomeCategory result) {
		imgBanners.setImageBitmap(result.getBanners().getBitmap());//只需显示图片
		
		categoryAdapter = new CategoryAdapter(context, result);
		gvCategory.setAdapter(categoryAdapter);//显示分类 图片
		
		specialAdapter = new SpecialAdapter(context, result);
		gvSpecial.setAdapter(specialAdapter);
		
	}

	public CategoryAdapter getCategoryAdapter() {
		return categoryAdapter;
	}

	public SpecialAdapter getSpecialAdapter() {
		return specialAdapter;
	}

	public HomeCategory getHomeCategory(){
		return homeCategory;
	}
}
