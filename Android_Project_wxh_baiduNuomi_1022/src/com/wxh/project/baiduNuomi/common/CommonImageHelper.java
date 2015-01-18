package com.wxh.project.baiduNuomi.common;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class CommonImageHelper {
	/**
	 * 获取Bitmap资源
	 * @param uri 连接地址
	 * @return Bitmap
	 */
	public static Bitmap getBitmap(String uri) {
		Bitmap bitmap = null;
		
		try {
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(CommonConfig.TIMEOUT);
			connection.connect();
			bitmap = BitmapFactory.decodeStream(connection.getInputStream());
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return bitmap;
	}
}
