package com.wxh.project.baiduNuomi.application;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.wxh.project.baiduNuomi.entity.Tuan;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

public class MyApplication extends Application{
	public static final String PREFS_NAME = "MyPrefsFile";
	public static final String PREFS_NAME_IS_TX = "isTX";
	public static final String PREFS_NAME_IS_WIFI_TP = "isWiFiTP";
	private static final String TAG = "MyApplication";
	
	public static boolean isTX;
	public static boolean isWiFiTP;
	
	public static Tuan tuan = null;
	private LocationClient mlocationClient;
	public static BDLocation mLocation;
	public BDLocationListener mListener = new MyLocationListener();
	
	@Override
	public void onCreate() {
		initMap();
	}
	
	private void initMap() {
		SDKInitializer.initialize(getApplicationContext());
		mlocationClient = new LocationClient(getApplicationContext());
		mlocationClient.registerLocationListener(mListener);
		
		initSettings();
		mlocationClient.start();
	}

	/**
	 * 初始化配置文件
	 */
	private void initSettings() {
		// 获取数据共享资源
		SharedPreferences spSettings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		// 在SharedPreferences如果存在该key，就使用key对应的value，否则使用默认值（false）
		isTX = spSettings.getBoolean(PREFS_NAME_IS_TX, true);
		isWiFiTP = spSettings.getBoolean(PREFS_NAME_IS_WIFI_TP, false);
		
		//设置定位选项
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
		option.setNeedDeviceDirect(true);// 返回的定位结果包含手机机头的方向
		mlocationClient.setLocOption(option);
	}
	
	/**
	 * 定位监听器
	 * 
	 */
	class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return;
//			StringBuffer sb = new StringBuffer(256);
//			sb.append("time : ");
//			sb.append(location.getTime());
//			sb.append("\nerror code : ");
//			sb.append(location.getLocType());
//			sb.append("\nlatitude : ");
//			sb.append(location.getLatitude());
//			sb.append("\nlontitude : ");
//			sb.append(location.getLongitude());
//			sb.append("\nradius : ");
//			sb.append(location.getRadius());
//			if (location.getLocType() == BDLocation.TypeGpsLocation) {
//				sb.append("\nspeed : ");
//				sb.append(location.getSpeed());
//				sb.append("\nsatellite : ");
//				sb.append(location.getSatelliteNumber());
//			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
//				sb.append("\naddr : ");
//				sb.append(location.getAddrStr());
//			}
			
			/*
			 * 61 ： GPS定位结果
			 * 62 ： 扫描整合定位依据失败。此时定位结果无效。
			 * 63 ： 网络异常，没有成功向服务器发起请求。此时定位结果无效。
			 * 65 ： 定位缓存的结果。
			 * 66 ： 离线定位结果。通过requestOfflineLocaiton调用时对应的返回结果
			 * 67 ： 离线定位失败。通过requestOfflineLocaiton调用时对应的返回结果
			 * 68 ： 网络连接失败时，查找本地离线定位时对应的返回结果
			 * 161： 表示网络定位结果
			 * 162~167： 服务端定位失败
			 * 502：key参数错误
			 * 505：key不存在或者非法
			 * 601：key服务被开发者自己禁用
			 * 602：key mcode不匹配
			 * 501～700：key验证失败
			 * */
			if (location.getLocType() >= 162 && location.getLocType() <= 167) {
				Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_SHORT).show();
			}
			
//			Log.i(TAG, "纬度：" + String.valueOf(location.getLatitude()));
//			Log.i(TAG, "经度：" + String.valueOf(location.getLongitude()));
//			Log.i(TAG, "城市：" + String.valueOf(location.getCity()));
//			Log.i(TAG, "城市码：" + String.valueOf(location.getCityCode()));
//			Log.i(TAG, "是否有地址：" + String.valueOf(location.hasAddr()));
			Log.i(TAG, "地址：" + location.getAddrStr());
			
			mLocation = location;

		}

	}

	public static Tuan getTuan() {
		return tuan;
	}

	public static void setTuan(Tuan tuan) {
		MyApplication.tuan = tuan;
	}

}
