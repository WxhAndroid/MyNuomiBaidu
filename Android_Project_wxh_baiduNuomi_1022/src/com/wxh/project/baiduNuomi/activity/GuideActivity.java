package com.wxh.project.baiduNuomi.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.android_wxh_project_baidunuomi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class GuideActivity extends Activity implements OnClickListener{
	private ViewPager viewPager;
	int[] ids = {R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3, R.drawable.guide_4};
	private List<ImageView> listImg;
	private ImageView imgLast;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		initUI();
		initData();
	}

	private void initUI() {
		viewPager = (ViewPager) findViewById(R.id.vp_guide);
		
	}

	private void initData() {
		ImageView imageView = null;
		listImg = new ArrayList<ImageView>();
		for (int i = 0; i < ids.length; i++) {
			imageView = new ImageView(this);
			imageView.setImageResource(ids[i]);
			
			listImg.add(imageView);
		}
		// 为最后一张图片设置点击事件，跳转到主页
		ImageView img = listImg.get(listImg.size() - 1);
		img.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(GuideActivity.this, MainActivity.class));
			}
		});
		//添加适配器
		viewPager.setAdapter(new MyPagerGuideAdapter());
		
		
	}
	
	class MyPagerGuideAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return listImg.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(listImg.get(position));
			return listImg.get(position);
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(listImg.get(position));
		}
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.drawable.guide_4:
			startActivity(new Intent(this, MainActivity.class));
			break;

		default:
			break;
		}
	}
}
