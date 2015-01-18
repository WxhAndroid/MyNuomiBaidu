package com.wxh.project.baiduNuomi.common;

import com.wxh.project.baiduNuomi.extend.CustomProgressDialog;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class CommonDialog {
	private static ProgressDialog progressDialog;
	private static CustomProgressDialog customProgressDialog;//������ʾ����
	
	/**
	 * ��������ʾ�Ի���
	 * @param context
	 * @param message
	 */
	public static void showProgressDialog(Context context, String message){
		progressDialog = new ProgressDialog(context);
		progressDialog.setMessage(message);
		progressDialog.show();
	}
	/**
	 * �������رնԻ���
	 */
	public static void closeProgressDialog(){
		progressDialog.dismiss();
	}
	
	/**
	 * ��ʾ�������ضԻ���
	 * @param context
	 * @param message
	 */
	public static void showCustomProgressDialog(Context context,String message){
		customProgressDialog = CustomProgressDialog.createDialog(context);
		customProgressDialog = CustomProgressDialog.setMessage(message);
		customProgressDialog.show();
	}
	
	/**
	 * �رն������ضԻ���
	 */
	public static void closeCustomProgressDialog(){
		customProgressDialog.dismiss();
	}
	
	/**
	 * ȷ�϶Ի���
	 * @param context
	 * @param message
	 * @param listener
	 */
	public static void confirm(Context context, String message, OnClickListener listener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message);
		builder.setPositiveButton("ȷ��", listener);
		builder.setNegativeButton("ȡ��", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
	
	
	
}
