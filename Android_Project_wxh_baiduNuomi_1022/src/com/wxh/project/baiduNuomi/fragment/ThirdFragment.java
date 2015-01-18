package com.wxh.project.baiduNuomi.fragment;

import com.example.android_wxh_project_baidunuomi.R;
import com.wxh.project.baiduNuomi.activity.FavoriteActivity;
import com.wxh.project.baiduNuomi.activity.LoginActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.TextView;

public class ThirdFragment extends Fragment implements OnClickListener{
	//¿Ø¼þ
	private Button btnLogin;
	private TextView txCollect;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		if(container == null){
			return null;
		}
		View view = inflater.inflate(R.layout.third_fragment, container, false);
		initUI(view);
		
		return view;
	}

	private void initUI(View view) {
		btnLogin = (Button) view.findViewById(R.id.btn_login);
		btnLogin.setOnClickListener(this);

		txCollect = (TextView) view.findViewById(R.id.tx_my_collection);
		txCollect.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btn_login:
			intent = new Intent(getActivity(), LoginActivity.class);
			break;

		case R.id.tx_my_collection:
			intent = new Intent(getActivity(), FavoriteActivity.class);
			break;
		default:
			break;
		}
		startActivity(intent);
	}
}
