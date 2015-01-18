package com.symbio.androidtraining;

import com.symbio.androidtraining.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WechatActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.we_chat);
	}

	
	
	public void onClick(View v){
		startActivity(new Intent(this,SecondActivity.class));
	}
}
