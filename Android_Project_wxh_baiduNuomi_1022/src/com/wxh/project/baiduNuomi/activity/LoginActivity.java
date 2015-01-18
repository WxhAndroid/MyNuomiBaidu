package com.wxh.project.baiduNuomi.activity;

import com.example.android_wxh_project_baidunuomi.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class LoginActivity extends Activity{
	//¿Ø¼þ
	private EditText editUserName;
	private EditText editUserPwd;
	//±äÁ¿
	private String userName;
	private String userPwd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		initUI();
		initData();
	}

	private void initUI() {
		editUserName = (EditText) findViewById(R.id.et_username);
		editUserPwd = (EditText) findViewById(R.id.et_userpwd);
		
	}
	
	private void initData(){
		userName = editUserName.getText().toString();
		userPwd = editUserPwd.getText().toString();
	}
}
