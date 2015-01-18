package com.wxh.project.baiduNuomi.common;

import com.wxh.project.baiduNuomi.extend.CustomProgressDialog;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class CommonDialog {
	private static ProgressDialog progressDialog;
	private static CustomProgressDialog customProgressDialog;//加载显示动画
	
	/**
	 * 进度条显示对话框
	 * @param context
	 * @param message
	 */
	public static void showProgressDialog(Context context, String message){
		progressDialog = new ProgressDialog(context);
		progressDialog.setMessage(message);
		progressDialog.show();
	}
	/**
	 * 进度条关闭对话框
	 */
	public static void closeProgressDialog(){
		progressDialog.dismiss();
	}
	
	/**
	 * 显示动画加载对话框
	 * @param context
	 * @param message
	 */
	public static void showCustomProgressDialog(Context context,String message){
		customProgressDialog = CustomProgressDialog.createDialog(context);
		customProgressDialog = CustomProgressDialog.setMessage(message);
		customProgressDialog.show();
	}
	
	/**
	 * 关闭动画加载对话框
	 */
	public static void closeCustomProgressDialog(){
		customProgressDialog.dismiss();
	}
	
	/**
	 * 确认对话框
	 * @param context
	 * @param message
	 * @param listener
	 */
	public static void confirm(Context context, String message, OnClickListener listener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message);
		builder.setPositiveButton("确定", listener);
		builder.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
	
	
	
}
