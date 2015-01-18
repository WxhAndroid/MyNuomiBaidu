package com.wxh.project.baiduNuomi.update;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;

public class UpdateTask extends AsyncTask<String, Integer, File> {
	private Context context;
	private ProgressDialog progressDialog;
	
	public UpdateTask(Context context) {
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		progressDialog = new ProgressDialog(context);
		progressDialog.setMessage("下载中...");
		progressDialog.setMax(100);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.show();
	}

	@Override
	protected File doInBackground(String... params) {
		FileOutputStream fos = null;
		InputStream is = null;
		File downloadFile = null;
		
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpParams httpParams = httpClient.getParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
			HttpGet httpGet = new HttpGet(params[0]);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					// 获取文件大小
					long length = httpEntity.getContentLength();
					// 获取文件输入流
					is = httpEntity.getContent();
					// 设置文件路径
					String filePath = Environment.getExternalStorageDirectory() + "/wxh/";
					// 设置文件名
//					String fileName = "Update.apk";
					String fileName = params[0].substring(params[0].lastIndexOf("/") + 1);
					
					// 判断文件路径是否存在
					File file = new File(filePath);
					if (!file.exists()) {
						file.mkdir();
					}
					
					// 创建输出流，准备写入文件
					downloadFile = new File(filePath + fileName);
					fos = new FileOutputStream(downloadFile);
					
					// 写入文件
					byte[] buf = new byte[1024];
					int len = -1, count = 0;
					while ((len = is.read(buf)) != -1) {
						fos.write(buf, 0, len);
						// 计算文件下载进度
						count += len;
						// 更新进度条
						publishProgress((int)((count * 100) / length));
						fos.flush();
					}
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return downloadFile;
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		progressDialog.setProgress(values[0]);
	}
	
	@Override
	protected void onPostExecute(File result) {
		progressDialog.dismiss();
		
		Intent apkIntent = new Intent(Intent.ACTION_VIEW);
		apkIntent.setDataAndType(
				Uri.parse("file://" + result.toString()), "application/vnd.android.package-archive");
		context.startActivity(apkIntent);
	}
	
}
