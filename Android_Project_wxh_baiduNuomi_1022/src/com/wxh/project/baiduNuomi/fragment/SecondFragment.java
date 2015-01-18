package com.wxh.project.baiduNuomi.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.android_wxh_project_baidunuomi.R;
import com.wxh.project.baiduNuomi.activity.AllTuanActivity;
import com.wxh.project.baiduNuomi.activity.ProductDetailActivity;
import com.wxh.project.baiduNuomi.adapter.LikeAdapter;
import com.wxh.project.baiduNuomi.entity.Tuan;
import com.wxh.project.baiduNuomi.extend.ListViewForScrollView;
import com.wxh.project.baiduNuomi.task.AllTuanAsyncTask;
import com.wxh.project.baiduNuomi.near.NearTask;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

public class SecondFragment extends Fragment implements OnItemClickListener{
	//附近接口
	private static final String URL_NEAR = "http://app.nuomi.com/naserver/search/nearitem?distance=0%2C3000&logpage=NearList&locate_city_id=0&page_idx=%s&location=0%2C0&sort_type=5&goods_per_page=15&fst_cattag_id=0&appid=android&tn=android&terminal_type=android&device=Genymotion+Google+Galaxy+Nexus+-+4.2.2+-+API+17+-+720x1280_2&channel=1009769b&v=5.1.0&os=SDK17&cityid=300210000&cuid=0451C21653F8BA3E94B1041B534DB039|000000000000000&uuid=ffffffff-888b-0268-9c1c-963000000000&timestamp=1412425826434&swidth=720&sheight=1184&net=wifi&sign=536d897e6675c5a3bc5775071b023ecb";
	private static final String URL_NEAR1 = "http://app.nuomi.com/naserver/search/nearitem?distance=3000&logpage=NearList&locate_city_id=300210000&page_idx=0&location=22.527788&sort_type=5&auto_broaden=1&goods_per_page=15&fst_cattag_id=0&appid=android&tn=android&terminal_type=android&cityid=300210000&cuid=5F75F038C2AC18FECAA7BB82A62699AA&uuid=ffffffff-c3ab-9a69-ffff-ffff9ab6dad7&bduss=1ZoWElNUHpjazlBSVRzdzZlVFB-flZ4Sk5OVHF4bTJzQ0pvb3FnQjd0S1IzVkJVQVFBQUFBJCQAAAAAAAAAAAEAAACzG4cONDA0NTEzNTY4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJFQKVSRUClUS&timestamp=1412265006372&swidth=720&sheight=1280&net=wifi&sign=178c7c7e4efdaa9c2395f8b6ae1e6624";

	public static final String EXTRA_DETAIL_ID = "deal_id";//交易ID
	//控件相关
	private ExpandableListView lvNear;

	//变量相关
	private boolean isLastRow = false;
	private int page = 0;
	private NearTask nearTask;

	private List<Tuan> listTuan = new ArrayList<Tuan>();
	private LikeAdapter likeAdapter = new LikeAdapter(getActivity(), listTuan);
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		if(container == null){
			return null;
		}
		View view = inflater.inflate(R.layout.second_fragment, container, false);
		initUI(view);


		initData();
		return view;
	}

	private void initUI(View view) {
		lvNear = (ExpandableListView) view.findViewById(R.id.lv_near);

		lvNear.setGroupIndicator(getResources().getDrawable(R.drawable.expandlistview_near_selector)); 
		lvNear.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
				String dealID = nearTask.getData().getPoi_list().get(groupPosition).getTuan_list().get(childPosition).getDeal_id();
				Log.i("secondFragment", dealID);
				intent.putExtra("deal_id", dealID);
				startActivity(intent);
				return true;
			}
		});
		
		lvNear.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if(scrollState == SCROLL_STATE_FLING && isLastRow){
					nearTask = new NearTask(getActivity(), lvNear);
					nearTask.execute(String.format(URL_NEAR, page++));
					
					isLastRow = false;
				}
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0){
					isLastRow = true;
					page ++;
				}
			}
		});
	}

	private void initData() {
		if(nearTask == null){
			nearTask = new NearTask(getActivity(), lvNear);
			nearTask.execute(URL_NEAR1);
		}else{
			lvNear.setAdapter(nearTask.getAdapter());
			
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Log.i("进入了主页附近页面点击", "yes");
//				Intent intent = new Intent(getActivity(), ProductInfoActivity.class);
				Tuan tuan = listTuan.get(position);
//				intent.putExtra(EXTRA_DETAIL_ID, tuan.get_id());
//				startActivity(intent);
	}

}
