package com.wxh.project.baiduNuomi.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.widget.Toast;

/**
 * 网络状态的广播接收者
 */
public class NetworkBroadcastReceiver extends BroadcastReceiver {
	public static final String ACTION_NETWORK_CHECK_STATE = "com.qf.teach.NETWORK_CHECK_STATE";
	public static String IS_NONE_CONNECTED = "isNoneConnected";
	private boolean IS_NET_SERVERD = true;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(ACTION_NETWORK_CHECK_STATE)) {
			checkNetworkState(context);
			sendValues();
		}
	}

	/**
	 * 检测网络状态
	 */
	private void checkNetworkState(Context context) {
		State wifiState = null;
		State mobileState = null;
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI) == null ? null : cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
		mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) == null ? null : cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
		if (wifiState != null && mobileState != null && State.CONNECTED != wifiState && State.CONNECTED == mobileState) {
			// 手机网络连接成功
			Toast.makeText(context, "使用 GSM 网络,请注意流量,亲！", Toast.LENGTH_LONG).show();
		} else if (wifiState != null && mobileState != null && State.CONNECTED != wifiState && State.CONNECTED != mobileState) {
			// 手机没有任何的网络
			IS_NET_SERVERD = false;
			Toast.makeText(context, "尚未接入网络", Toast.LENGTH_LONG).show();
		} else if (wifiState != null && State.CONNECTED == wifiState) {
			// 无线网络连接成功
			Toast.makeText(context, "使用 WIFI无线网络", Toast.LENGTH_LONG).show();
		}
	}
	
	private void sendValues(){
		Intent intent = new Intent();
		intent.putExtra(IS_NONE_CONNECTED, String.valueOf(IS_NET_SERVERD));
	}

}
