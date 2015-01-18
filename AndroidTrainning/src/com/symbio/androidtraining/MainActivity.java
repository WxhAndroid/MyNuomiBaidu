package com.symbio.androidtraining;

import com.symbio.androidtraining.R;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.btn_back).setVisibility(View.INVISIBLE);
		TextView tvTitle  = (TextView) findViewById(R.id.title);
		tvTitle.setText("Android Trainig");
		
		String str = getResources().getString(R.string.pay_summary, "Truman",
				30);

		TextView tvTest = (TextView) findViewById(R.id.txt_string_test);
		tvTest.setText(str);
		// startActivity(new Intent(this,SecondActivity.class));

		findViewById(R.id.btn_to_wechat).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent in = new Intent(MainActivity.this,
								WechatActivity.class);
						startActivity(in);
					}
				});
		
		findViewById(R.id.btn_to_qq_browser_setting).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent in = new Intent(MainActivity.this,
								QQBrowserSettingActivity.class);
						startActivity(in);
					}
				});
		findViewById(R.id.btn_to_qq_login).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent in = new Intent(MainActivity.this,
								NetworkTestLoginActivity.class);
						startActivity(in);
					}
				});
		
		

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		Toast.makeText(this, "onConfigurationChanged", Toast.LENGTH_SHORT)
				.show();

	}

//	public void onClick(View v) {
//		String s = "asdfasdf";
//		Intent in = new Intent(this, SecondActivity.class);
//		in.putExtra("value", s);
//
//		User u = new User("truman", 310);
//		in.putExtra("employee", u);
//
//		startActivity(in);
//	}

//	public void onDataCenter(View v) {
//		String s = "asdfasdf";
//		Intent in = new Intent(this, SecondActivity.class);
//
//		startActivity(in);
//
//		TrainingApplication app = (TrainingApplication) this.getApplication();
//		app.getUser();
//
//	}
}
