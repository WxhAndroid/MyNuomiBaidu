package com.symbio.androidtraining;

import com.symbio.androidtraining.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
	}

	
	public void onClick(View v){
		startActivity(new Intent(this,ThirdActivity.class));
	}
}
