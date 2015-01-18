package com.symbio.androidtraining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class NetworkTestLoginActivity extends Activity implements
		OnClickListener {
	private static final String basePath = "http://192.168.130.97:8080";
	private EditText etLoginPWD;
	private EditText etLoginName;
	private MyHandler mHandler;
	private TextView tvResult;
	private final String TAG_LOGIN = NetworkTestLoginActivity.class
			.getSimpleName() + ".TAG_LOGIN";
	
	private final int TAG_LOGIN_RETURN = 1;
	private final int TAG_LOGIN_RETURN_SUCC = 1;
	private final int TAG_LOGIN_RETURN_FAIL = 0;

	class LoginReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			if (!arg1.getAction().equals(TAG_LOGIN)) {
				return;
			}
			hideProgress();
			
			int state = arg1.getIntExtra("state", -1);
			if(state == TAG_LOGIN_RETURN_SUCC){
				loginSucc(1,"BroadcastReceiver处理:"+arg1.getStringExtra("result"));
				tvResult.setText("");
			}else if(state == TAG_LOGIN_RETURN_FAIL){
				tvResult.setText("登录失败,请检查网络或告诉Truman");
			}
		}

	}

	private LoginReceiver receiver = new LoginReceiver();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.network_test_login);

		findViewById(R.id.btn_qq_login).setOnClickListener(this);
		findViewById(R.id.btn_qq_login_broadcast).setOnClickListener(this);
		
		// findViewById(R.id.btn_qq_android_login).setOnClickListener(this);

		etLoginName = (EditText) findViewById(R.id.et_log_name);
		etLoginPWD = (EditText) findViewById(R.id.et_log_password);

		Looper looper = Looper.myLooper();
		mHandler = new MyHandler(looper);// 

		tvResult = (TextView) findViewById(R.id.tv_result);

		IntentFilter inFilter = new IntentFilter();
		inFilter.addAction(TAG_LOGIN);
		this.registerReceiver(receiver, inFilter);
	}
	
	private void loginSucc(int tag,String msg){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		
		AlertDialog ad = builder.setMessage(msg).setTitle("登录成功").setPositiveButton("OK", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
			
		}).create();
		ad.show();
	}


	@Override
	protected void onDestroy() {
		this.unregisterReceiver(receiver);
		super.onDestroy();
	}

	private class MyHandler extends Handler {
		public MyHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {//
			if(msg.what != TAG_LOGIN_RETURN){
				return;
			}
			hideProgress();
			
			//Login return
			if(msg.arg1 == TAG_LOGIN_RETURN_SUCC){
				tvResult.setText("");
				loginSucc(1,"Handler处理结果:" + msg.obj.toString());
				findViewById(R.id.btn_qq_login_broadcast).setVisibility(View.VISIBLE);
			}else if(msg.arg1 == TAG_LOGIN_RETURN_FAIL){
				tvResult.setText("登录失败,请检查网络或告诉Truman");
			}
			
		}
	}

	ProgressDialog pd;
	private void showProgress(){
		pd = ProgressDialog.show(this, "登录中", "请稍候...");
		pd.setCancelable(false);
	}
	private void hideProgress(){
		if(pd == null){
			return;
		}
		pd.dismiss();
	}
	
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_qq_login) {
			showProgress();
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						String result = login(etLoginName.getText().toString(), etLoginPWD
								.getText().toString());
						
						Message m = mHandler.obtainMessage(TAG_LOGIN_RETURN, TAG_LOGIN_RETURN_SUCC, TAG_LOGIN_RETURN_SUCC, result);
						mHandler.sendMessage(m);
						
					} catch (Exception e) {
						e.printStackTrace();
						Message m = mHandler.obtainMessage(TAG_LOGIN_RETURN, TAG_LOGIN_RETURN_FAIL, TAG_LOGIN_RETURN_FAIL);
						mHandler.sendMessage(m);
					}
				}
			}).start();
		}
		
		if (v.getId() == R.id.btn_qq_login_broadcast) {
			showProgress();
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						String result = login(etLoginName.getText().toString(), etLoginPWD
								.getText().toString());
						
						Intent in = new Intent(TAG_LOGIN);
						in.putExtra("result", result);
						in.putExtra("state", TAG_LOGIN_RETURN_SUCC);
						NetworkTestLoginActivity.this.sendBroadcast(in);
						
					} catch (Exception e) {
						e.printStackTrace();
						Intent in = new Intent(TAG_LOGIN);
						in.putExtra("result", "");
						in.putExtra("state", TAG_LOGIN_RETURN_FAIL);
						NetworkTestLoginActivity.this.sendBroadcast(in);
					}
				}
			}).start();
		}

	}

	static String TAG_GET = "get";

	public String login(String name, String password)
			throws Exception {
		String path = basePath + "/login?username=" + name + "&password="
				+ password;
		URL url = new URL(path);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		urlConn.setConnectTimeout(5 * 1000);
		urlConn.connect();
		String data = null;
		if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			data = readStream(urlConn.getInputStream());

			Log.i(TAG_GET, data);
			Log.i(TAG_GET, data);
		} else {
			Log.i(TAG_GET, "Get data failed");
		}
		urlConn.disconnect();
		
		return data;
	}

	public static void androidLogin(String name, String password)
			throws Exception {
		String path = basePath + "/login?username=" + name + "&password="
				+ password;
		URL url = new URL(path);

		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		urlConn.setConnectTimeout(5 * 1000);
		urlConn.connect();
		if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			String data = readStream(urlConn.getInputStream());
			Log.i(TAG_GET, data);
		} else {
			Log.i(TAG_GET, "Get data failed");
		}
	}

	public static String readStream(InputStream in) throws IOException {
		StringBuffer sb = new StringBuffer();

		BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
		String line = "";
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}

}
