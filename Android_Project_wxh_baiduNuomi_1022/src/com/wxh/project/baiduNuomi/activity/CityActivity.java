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
	//��ͼ���
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
		//		mLocationClient = new LocationClient(getApplicationContext()); // ����LocationClient��
		//		mLocationClient.registerLocationListener(myListener); // ע���������
		//
		//		setLocationOption();
		//		mLocationClient.start();// ��ʼ��λ
		initUI();
		initData();
	}

	@Override
	protected void onDestroy() {
		//		mLocationClient.stop();
		super.onDestroy();
	}

	/**
	 * ������ز���
	 */
	private void setLocationOption() {
		//		LocationClientOption option = new LocationClientOption();
		//		option.setOpenGps(true);
		//		option.setIsNeedAddress(true);// ���صĶ�λ���������ַ��Ϣ
		//		//		option.setAddrType("all");// ���صĶ�λ���������ַ��Ϣ
		//		option.setCoorType("bd09ll");// ���صĶ�λ����ǰٶȾ�γ��,Ĭ��ֵgcj02
		//		option.setScanSpan(5000);// ���÷���λ����ļ��ʱ��Ϊ5000ms
		//		//		option.disableCache(true);// ��ֹ���û��涨λ
		//		//		option.setPoiNumber(5); // ��෵��POI����
		//		//		option.setPoiDistance(1000); // poi��ѯ����
		//		//		option.setPoiExtraInfo(true); // �Ƿ���ҪPOI�ĵ绰�͵�ַ����ϸ��Ϣ
		//		mLocationClient.setLocOption(option);//��λ�ͻ������ö�λѡ��
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu. action_city, menu);//��xml�ļ�

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("��ǰ����" + locationCityName);

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
	//			sb.append("��ǰʱ�� : ");
	//			sb.append(location.getTime());
	//			sb.append("\n������ : ");
	//			sb.append(location.getLocType());
	//			sb.append("\nγ�� : ");
	//			sb.append(location.getLatitude());
	//			sb.append("\n���� : ");
	//			sb.append(location.getLongitude());
	//			sb.append("\n�뾶 : ");
	//			sb.append(location.getRadius());
	//
	//			if (location.getLocType() == BDLocation.TypeGpsLocation) {
	//				sb.append("\n�ٶ� : ");
	//				sb.append(location.getSpeed());
	//				sb.append("\n������ : ");
	//				sb.append(location.getSatelliteNumber());
	//			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
	//				sb.append("\n��ַ : ");
	//				sb.append(location.getAddrStr());
	//			}

	//			locationCityName = location.getCity();
	//			txLocationCity.setText(sb.toString());
	//			btnLocationCity.setText(locationCityName);
	//			Log.d("���ؽ��", "onReceiveLocation " + sb.toString());
	//		}
	//
	//	}
	/**
	 * ��ȡ���ݿ�����
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
				if(str.equalsIgnoreCase(String.valueOf(cityUrl.charAt(0)))){//������ĸƥ�� װ�ؽ�ȥ
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
	 * ��ʼ���ؼ�
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
							//						Toast.makeText(this, String.format("��ѡ���˳��У�%s", locationCityName), Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(CityActivity.this, MainActivity.class);
							intent.putExtra(CITY_ID, cityId);
							intent.putExtra(CITY_NAME, locationCityName);
							setResult(CITY_SELECTED_RESULT, intent);
							finish();
						}
					}else{
						Toast.makeText(CityActivity.this, "û�л�ȡ����λ���У������¶�λ", Toast.LENGTH_SHORT).show();
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
	 * ��ʼ������
	 */
	private void initData() {
		locationCityName = MyApplication.mLocation.getCity();//��λ������
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
			 * ѡ����в���ת����ҳ
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
					Log.i("����id:heihei", cityId);
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
	 * �ұ���ĸ������
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
	 * ������������
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
			 * ��ʹconvertView������һЩ���֣��������ع�ʱ��������֪����ô��ȥ��convertView����������Ҫ�Ĳ��֣���ʱ����
			 * Ҫ��adapter֪���ҵ�ǰ����Щ���֣����ع�Itemʱ�Ĳ���ѡȡ���򣬺���convertView�ܷ�������Ҫ�Ĳ��֡�
			 * ��Ҫ��д������������
			 * 
			 * @Override public int getItemViewType(int position)
			 *           {}���������ȡ��getView�д�������ͼ������
			 * @Override public int getViewTypeCount() {}������getView�д�����ͼ���͵�����
			 *           ������������������ϸ�ô����Լ���api����
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
	 * listview����¼�
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
					Toast.makeText(this, String.format("��ѡ���˳��У�%s", cityName), Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(CityActivity.this, MainActivity.class);
					intent.putExtra(CITY_ID, cityId);
					intent.putExtra(CITY_NAME, cityName);
					setResult(CITY_SELECTED_RESULT, intent);
					finish();
				}
			}
			// �رյ�ǰҳ��
			//			startActivity(intent); //����ʹ��
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
 * ��������ĸ
 * 
 */
class ViewHolder1 {
	TextView tv;
}

/**
 * ������
 */
class ViewHolder2 {
	TextView tv;
}
