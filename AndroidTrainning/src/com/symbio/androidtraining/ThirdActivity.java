package com.symbio.androidtraining;

import com.symbio.androidtraining.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ThirdActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		
		TextView tv = (TextView) findViewById(R.id.txt_string_test);
		tv.setText(this.toString());
	}

	
	
	public void onClick(View v){
		startActivity(new Intent(this,SecondActivity.class));
	}
}
