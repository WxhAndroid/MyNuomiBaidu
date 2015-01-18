package com.wxh.project.baiduNuomi.fragment;

import com.example.android_wxh_project_baidunuomi.R;
import com.wxh.project.baiduNuomi.application.MyApplication;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

public class FourthFragment extends Fragment{
	//控件
	private CheckBox cbTuanNotify;
	private CheckBox cbImgWifi;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		if(container == null){
			return null;
		}
		View view = inflater.inflate(R.layout.fourth_fragment, container, false);
		initUI(view);
		initData();
	
		return view;
	}

	private void initUI(View view) {
		cbTuanNotify = (CheckBox) view.findViewById(R.id.cb_notify_tuan);
		
		cbImgWifi = (CheckBox) view.findViewById(R.id.cb_wifi_show);
	}

	private void initData() {
		// 生命周期，从项目安装进系统后就存在，直到项目被删除
		// 取值
		SharedPreferences sp = getActivity().getSharedPreferences(MyApplication.PREFS_NAME, getActivity().MODE_PRIVATE);
		boolean isCheck = sp.getBoolean("isCheck", false);
		cbTuanNotify.setChecked(isCheck);
		
		SharedPreferences sp2 = getActivity().getSharedPreferences("wifi", getActivity().MODE_PRIVATE);
		boolean isCheck2 = sp2.getBoolean("isWifiShow", false);
		cbImgWifi.setChecked(isCheck2);
		
	}
	
	@Override
	public void onStop() {
		// 复写SharedPreferences里的值
		SharedPreferences sp = getActivity().getSharedPreferences(MyApplication.PREFS_NAME, getActivity().MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean("isCheck", cbTuanNotify.isChecked());
		SharedPreferences sp2 = getActivity().getSharedPreferences("wifi", getActivity().MODE_PRIVATE);
		Editor editor2 = sp2.edit();
		editor2.putBoolean("isWifiShow", cbImgWifi.isChecked());
		
		editor2.commit();
		editor.commit();
		super.onStop();
	}

}
