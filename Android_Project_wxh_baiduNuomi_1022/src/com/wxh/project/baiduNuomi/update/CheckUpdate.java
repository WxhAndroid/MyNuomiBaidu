package com.wxh.project.baiduNuomi.update;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;

public class CheckUpdate {
	//other localhost:10.2.105.27  192.168.1.104
	private static final String UPDATE_URL = "http://10.2.105.28:8080/apk/update.xml";
	private static final String UPDATE_APK = "http://10.2.105.28:8080/apk/Android_wxh_Project_baiduNuomi.apk";
	
	private Context context;
	
	public CheckUpdate(Context context) {
		this.context = context;
	}
	
	public void check() {
		CheckTask task = new CheckTask();
		task.execute(UPDATE_URL);
	}
	
	class CheckTask extends AsyncTask<String, Void, Boolean> implements OnClickListener{
		private Update update;
		@Override
		protected Boolean doInBackground(String... params) {
			boolean isNew = false;
			try {
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser parser = factory.newSAXParser();
				UpdateHandler handler = new UpdateHandler();
				parser.parse(params[0], handler);

				update = handler.getUpdate();
				// 获取当前版本号
				int version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
				if (update.getVersion() > version) {
					isNew = true;
				}
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
			
			return isNew;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			if(result){
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setTitle(update.getTitle());
				builder.setMessage(update.getMessage().replace("-", "\r\n"));
				builder.setPositiveButton("立即升级", this);
				builder.setNegativeButton("残忍拒绝", this);
				builder.create().show();
			}
		}

		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			// 立即升级
			case -1:
				UpdateTask task = new UpdateTask(context);
				task.execute(UPDATE_APK);
				break;
			// 残忍拒绝
			case -2:
				dialog.dismiss();
				break;

			default:
				break;
			}
		}
		
	}
}
