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
		CommonDialog.showCustomProgressDialog(context, "Ŭ�������С���");//���ض���
	}

	@SuppressWarnings("null")
	@SuppressLint("ShowToast")
	@Override
	protected HomeCategory doInBackground(String... params) {
		String json = CommonJSONHelper.getJSON(params[0]);
		if(json != null){
			homeCategory = new HomeCategory();
			try {
				//��������ͼƬ
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
						// ����HomeCategory��banners������ֵ
						homeCategory.setBanners(banners);
						//��ӡlog��Ϣ
//						Log.i("��ҳ��������", banners.getPicture_url());
					}
				}
				//�����м䲿�� ��ʳ ͼƬ����Ϣ
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

						//��ӡlog��Ϣ
						Log.i("�����������",category.getCategory_name());
//						Log.i("�������ͼƬ��ַ",category.getCategory_picurl());
						//��ӵ�����
						categoryList.add(category);
						
						homeCategory.setCategoryList(categoryList);
					}
				}
				
				//�����ر��Ƽ�
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
						
//						Log.i("�ر��Ƽ�", special.getPicture_url());
						
						specialList.add(special);
						
						homeCategory.setSpecialList(specialList);
					}
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
//		}else{
//			Toast.makeText(context, "û�п��Խ���������", Toast.LENGTH_SHORT);
//		}

		return homeCategory;
	}
	
	@Override
	protected void onPostExecute(HomeCategory result) {
		imgBanners.setImageBitmap(result.getBanners().getBitmap());//ֻ����ʾͼƬ
		
		categoryAdapter = new CategoryAdapter(context, result);
		gvCategory.setAdapter(categoryAdapter);//��ʾ���� ͼƬ
		
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
