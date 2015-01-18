package com.symbio.androidtraining;

import com.symbio.androidtraining.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QQBrowserSettingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qq_browser_setting);
		TextView tvTitle = (TextView) findViewById(R.id.title);
		tvTitle.setText("设置");
		
		
	}

	
	
	public void onClick(View v){
//		startActivity(new Intent(this,SecondActivity.class));
	}
}
