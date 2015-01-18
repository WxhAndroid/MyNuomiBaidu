package com.wxh.project.baiduNuomi.common;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

public class CommonJSONHelper {
	
	/**
	 * 通过网络请求获取JSON字符串
	 * @param uri 连接地址
	 * @return String
	 */
	public static String getJSON(String uri) {
		String json = null;
		
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpParams httpParams = httpClient.getParams();
            String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6";
            HttpProtocolParams. setUserAgent(httpParams, userAgent);
			HttpConnectionParams.setConnectionTimeout(httpParams, CommonConfig.TIMEOUT);
			HttpGet httpGet = new HttpGet(uri);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					json = EntityUtils.toString(httpEntity, "UTF-8");
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return json;
	}
}
