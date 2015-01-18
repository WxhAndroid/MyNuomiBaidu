package com.wxh.project.baiduNuomi.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.android_wxh_project_baidunuomi.R;
import com.wxh.project.baiduNuomi.application.MyApplication;
import com.wxh.project.baiduNuomi.common.CommonConfig;
import com.wxh.project.baiduNuomi.db.CityDBHelper;
import com.wxh.project.baiduNuomi.entity.City;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CityActivity extends Activity implements OnItemClickListener {
	private String[] letter = { "A", "B", "C", "D", "E", "F", "G", "H", "I","J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V","W", "X", "Y", "Z" };
	private List<String> letterToCity = new ArrayList<String>();
	private ListView lvCity;
	private ListView lvLetter;
	private CityDBHelper db;
	private Cursor cursor;
	private AutoCompleteTextView atCitySearch;
	private List<Map<String, Object>> listAtCity;
	private List<City> listCity ;
	private City city;
	public static final String CITY_ID = "city_id_value";
	public static final String CITY_NAME = "city_name_value";
	public static final int CITY_SELECTED_RESULT = 11;
	public  static final int CITY_SELECTED_REQUEST = 1;
	//地图相关
	//	private LocationClient mLocationClient = null;
	//	private BDLocationListener myListener = new MyLocationListener();

	private static final String TAG = "CityActivity";
	private Button btnLocationCity;
	private TextView txLocationCity;
	private String locationCityName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city);

		db = new CityDBHelper(this);

		initParams();
		//		mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
		//		mLocationClient.registerLocationListener(myListener); // 注册监听函数
		//
		//		setLocationOption();
		//		mLocationClient.start();// 开始定位
		initUI();
		initData();
	}

	@Override
	protected void onDestroy() {
		//		mLocationClient.stop();
		super.onDestroy();
	}

	/**
	 * 设置相关参数
	 */
	private void setLocationOption() {
		//		LocationClientOption option = new LocationClientOption();
		//		option.setOpenGps(true);
		//		option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
		//		//		option.setAddrType("all");// 返回的定位结果包含地址信息
		//		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		//		option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms
		//		//		option.disableCache(true);// 禁止启用缓存定位
		//		//		option.setPoiNumber(5); // 最多返回POI个数
		//		//		option.setPoiDistance(1000); // poi查询距离
		//		//		option.setPoiExtraInfo(true); // 是否需要POI的电话和地址等详细信息
		//		mLocationClient.setLocOption(option);//定位客户端设置定位选项
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu. action_city, menu);//绑定xml文件

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("当前城市" + locationCityName);

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

	//	
	//
	//	public class MyLocationListener implements BDLocationListener {
	//		@Override
	//		public void onReceiveLocation(BDLocation location) {
	//			if (location == null)
	//				return;
	//			StringBuffer sb = new StringBuffer(256);
	//			sb.append("当前时间 : ");
	//			sb.append(location.getTime());
	//			sb.append("\n错误码 : ");
	//			sb.append(location.getLocType());
	//			sb.append("\n纬度 : ");
	//			sb.append(location.getLatitude());
	//			sb.append("\n经度 : ");
	//			sb.append(location.getLongitude());
	//			sb.append("\n半径 : ");
	//			sb.append(location.getRadius());
	//
	//			if (location.getLocType() == BDLocation.TypeGpsLocation) {
	//				sb.append("\n速度 : ");
	//				sb.append(location.getSpeed());
	//				sb.append("\n卫星数 : ");
	//				sb.append(location.getSatelliteNumber());
	//			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
	//				sb.append("\n地址 : ");
	//				sb.append(location.getAddrStr());
	//			}

	//			locationCityName = location.getCity();
	//			txLocationCity.setText(sb.toString());
	//			btnLocationCity.setText(locationCityName);
	//			Log.d("返回结果", "onReceiveLocation " + sb.toString());
	//		}
	//
	//	}
	/**
	 * 获取数据库数据
	 */
	private void initParams() {
		String str = "";
		listCity = new ArrayList<City>();
		City city = null;
		for (int i = 0; i < letter.length; i++) {
			cursor = db.executeQuery(CommonConfig.SQL_QUERY_CITY);
			str = letter[i];
			letterToCity.add(str);
			while (cursor.moveToNext()) {
				String cityUrl = cursor.getString(cursor.getColumnIndex("city_url"));
				if(str.equalsIgnoreCase(String.valueOf(cityUrl.charAt(0)))){//和首字母匹配 装载进去
					String cityName = cursor.getString(cursor.getColumnIndex("city_name"));
					city = new City();
					letterToCity.add(cityName);
					city.setCityName(cityName);
					city.setCityUrl(cityUrl);
					listCity.add(city);
				}
			}
		}
	}

	/**
	 * 初始化控件
	 */
	private void initUI() {
		lvCity = (ListView) findViewById(R.id.lv_city);
		lvCity.setOnItemClickListener(this);

		lvLetter = (ListView) findViewById(R.id.lv_letter);
		lvLetter.setOnItemClickListener(this);

		atCitySearch = (AutoCompleteTextView) findViewById(R.id.at_search);


		btnLocationCity = (Button) findViewById(R.id.btn_location_city);
		btnLocationCity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.btn_location_city:
					city = new City();
					if(locationCityName != null){
						cursor = db.executeQuery(CommonConfig.SQL_QUERY_CITY_BY_CITY_NAME, locationCityName);
						while(cursor.moveToNext()){
							String cityId = cursor.getString(cursor.getColumnIndex("city_id"));
							//						Toast.makeText(this, String.format("你选择了城市：%s", locationCityName), Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(CityActivity.this, MainActivity.class);
							intent.putExtra(CITY_ID, cityId);
							intent.putExtra(CITY_NAME, locationCityName);
							setResult(CITY_SELECTED_RESULT, intent);
							finish();
						}
					}else{
						Toast.makeText(CityActivity.this, "没有获取到定位城市，请重新定位", Toast.LENGTH_SHORT).show();
					}
					break;

				default:
					break;
				}
			}
		});

		txLocationCity = (TextView) findViewById(R.id.tx_location_city);

	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		locationCityName = MyApplication.mLocation.getCity();//定位城市名
		if(locationCityName != null){
			txLocationCity.setText(locationCityName);
			btnLocationCity.setText(locationCityName);
		}

		lvCity.setAdapter(new CityAdapter());
		lvLetter.setAdapter(new LetterAdapter());

		listAtCity = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
		for (int i = 0; i < listCity.size(); i++) {
			map = new HashMap<String, Object>();
			map.put("cityName2", listCity.get(i).getCityName());
			map.put("cityUrl2", listCity.get(i).getCityUrl());
			listAtCity.add(map);
		}

		SimpleAdapter simAdapter = new SimpleAdapter(CityActivity.this, listAtCity, R.layout.city_at_list, new String[]{"cityName2", "cityUrl2"}, new int[]{R.id.city_at_cityName, R.id.city_at_url});
		atCitySearch.setAdapter(simAdapter);
		atCitySearch.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				getAtNameAndToHomePage();
			}

			/**
			 * 选择城市并跳转到主页
			 */
			private void getAtNameAndToHomePage() {
				String atInfo = atCitySearch.getText().toString();
				String cityInfo[] = atInfo.split("\\=");
				String cityName = cityInfo[2].substring(0,cityInfo[2].length() - 1);
				atCitySearch.setText(cityName);
				atCitySearch.setSelection(cityName.length());

				city = new City();
				cursor = db.executeQuery(CommonConfig.SQL_QUERY_CITY_BY_CITY_NAME, cityName);
				while(cursor.moveToNext()){
					String cityId = cursor.getString(cursor.getColumnIndex("city_id"));
					Log.i("城市id:heihei", cityId);
					Intent intent = new Intent(CityActivity.this, MainActivity.class);
					intent.putExtra(CITY_ID, cityId);
					intent.putExtra(CITY_NAME, cityName);
					setResult(CITY_SELECTED_RESULT, intent);
					finish();
				}
			}
		});

	}

	/**
	 * 右边字母适配器
	 * 
	 */
	class LetterAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return letter.length;
		}

		@Override
		public Object getItem(int position) {
			return letter[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = LayoutInflater.from(CityActivity.this).inflate(R.layout.letter_list, null);
			TextView tv = (TextView) view.findViewById(R.id.letterListTextView);
			tv.setText(letter[position]);
			return view;
		}

	}

	/**
	 * 城市名适配器
	 * 
	 */
	class CityAdapter extends BaseAdapter {

		final static int TYPE_1 = 1;
		final static int TYPE_2 = 2;

		@Override
		public int getCount() {
			return letterToCity.size();
		}

		@Override
		public Object getItem(int position) {
			return letterToCity.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public int getItemViewType(int position) {
			for (int i = 0; i < letter.length; i++) {
				if (letterToCity.get(position).equals(letter[i])) {
					return TYPE_1;
				}
			}
			return TYPE_2;
		}

		@Override
		public int getViewTypeCount() {
			return 3;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			/**
			 * 即使convertView缓存了一些布局，但是在重构时，根本不知道怎么样去让convertView返回你所需要的布局，这时你需
			 * 要让adapter知道我当前有哪些布局，我重构Item时的布局选取规则，好让convertView能返回你需要的布局。
			 * 需要重写以下两个函数
			 * 
			 * @Override public int getItemViewType(int position)
			 *           {}这个函数获取在getView中创建的视图的类型
			 * @Override public int getViewTypeCount() {}返回在getView中创建视图类型的数量
			 *           至于这两个方法的详细用处，自己看api即可
			 */
			ViewHolder1 vh1 = null;
			ViewHolder2 vh2 = null;
			int type = getItemViewType(position);
			if (convertView == null) {
				switch (type) {
				case TYPE_1:
					convertView = LayoutInflater.from(CityActivity.this)
					.inflate(R.layout.letter, null);
					vh1 = new ViewHolder1();
					vh1.tv = (TextView) convertView.findViewById(R.id.letterTextView);
					convertView.setTag(vh1);
					break;
				case TYPE_2:
					convertView = LayoutInflater.from(CityActivity.this).inflate(R.layout.city_list, null);
					vh2 = new ViewHolder2();
					vh2.tv = (TextView) convertView.findViewById(R.id.cityTextView);
					convertView.setTag(vh2);
					break;
				default:
					break;
				}
			} else {
				switch (type) {
				case TYPE_1:
					vh1 = (ViewHolder1) convertView.getTag();
					break;
				case TYPE_2:
					vh2 = (ViewHolder2) convertView.getTag();
					break;
				default:
					break;
				}
			}
			switch (type) {
			case TYPE_1:
				vh1.tv.setText(letterToCity.get(position));
				break;
			case TYPE_2:
				vh2.tv.setText(letterToCity.get(position));
				break;
			default:
				break;
			}
			return convertView;
		}
	}

	/**
	 * listview点击事件
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (parent.getId()) {
		case R.id.lv_city:
			boolean isLetter = false;
			for (int i = 0; i < letter.length; i++) {
				if (letter[i].equals(letterToCity.get(position))) {
					isLetter = true;
					break;
				}
			}
			if(!isLetter){
				city = new City();
				cursor = db.executeQuery(CommonConfig.SQL_QUERY_CITY_BY_CITY_NAME, letterToCity.get(position));
				while(cursor.moveToNext()){
					String cityId = cursor.getString(cursor.getColumnIndex("city_id"));
					String cityName = letterToCity.get(position);
					Toast.makeText(this, String.format("你选择了城市：%s", cityName), Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(CityActivity.this, MainActivity.class);
					intent.putExtra(CITY_ID, cityId);
					intent.putExtra(CITY_NAME, cityName);
					setResult(CITY_SELECTED_RESULT, intent);
					finish();
				}
			}
			// 关闭当前页面
			//			startActivity(intent); //不能使用
			break;
		case R.id.lv_letter:
			for (int i = 0; i < letterToCity.size(); i++) {
				if (letter[position].equalsIgnoreCase(letterToCity.get(i))) {
					lvCity.setSelection(i);
					break;
				}
			}
			break;

		default:
			break;
		}
	}
}

/**
 * 城市首字母
 * 
 */
class ViewHolder1 {
	TextView tv;
}

/**
 * 城市名
 */
class ViewHolder2 {
	TextView tv;
}
