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
	 * ��ʼ�������ļ�
	 */
	private void initSettings() {
		// ��ȡ���ݹ�����Դ
		SharedPreferences spSettings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		// ��SharedPreferences������ڸ�key����ʹ��key��Ӧ��value������ʹ��Ĭ��ֵ��false��
		isTX = spSettings.getBoolean(PREFS_NAME_IS_TX, true);
		isWiFiTP = spSettings.getBoolean(PREFS_NAME_IS_WIFI_TP, false);
		
		//���ö�λѡ��
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// ���ö�λģʽ
		option.setCoorType("bd09ll");// ���صĶ�λ����ǰٶȾ�γ��,Ĭ��ֵgcj02
		option.setScanSpan(5000);// ���÷���λ����ļ��ʱ��Ϊ5000ms
		option.setIsNeedAddress(true);// ���صĶ�λ���������ַ��Ϣ
		option.setNeedDeviceDirect(true);// ���صĶ�λ��������ֻ���ͷ�ķ���
		mlocationClient.setLocOption(option);
	}
	
	/**
	 * ��λ������
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
			 * 61 �� GPS��λ���
			 * 62 �� ɨ�����϶�λ����ʧ�ܡ���ʱ��λ�����Ч��
			 * 63 �� �����쳣��û�гɹ���������������󡣴�ʱ��λ�����Ч��
			 * 65 �� ��λ����Ľ����
			 * 66 �� ���߶�λ�����ͨ��requestOfflineLocaiton����ʱ��Ӧ�ķ��ؽ��
			 * 67 �� ���߶�λʧ�ܡ�ͨ��requestOfflineLocaiton����ʱ��Ӧ�ķ��ؽ��
			 * 68 �� ��������ʧ��ʱ�����ұ������߶�λʱ��Ӧ�ķ��ؽ��
			 * 161�� ��ʾ���綨λ���
			 * 162~167�� ����˶�λʧ��
			 * 502��key��������
			 * 505��key�����ڻ��߷Ƿ�
			 * 601��key���񱻿������Լ�����
			 * 602��key mcode��ƥ��
			 * 501��700��key��֤ʧ��
			 * */
			if (location.getLocType() >= 162 && location.getLocType() <= 167) {
				Toast.makeText(getApplicationContext(), "��λʧ��", Toast.LENGTH_SHORT).show();
			}
			
//			Log.i(TAG, "γ�ȣ�" + String.valueOf(location.getLatitude()));
//			Log.i(TAG, "���ȣ�" + String.valueOf(location.getLongitude()));
//			Log.i(TAG, "���У�" + String.valueOf(location.getCity()));
//			Log.i(TAG, "�����룺" + String.valueOf(location.getCityCode()));
//			Log.i(TAG, "�Ƿ��е�ַ��" + String.valueOf(location.hasAddr()));
			Log.i(TAG, "��ַ��" + location.getAddrStr());
			
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
