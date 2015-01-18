package com.wxh.project.baiduNuomi.common;
import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class CommonCallApp {
	/**
	 * �ı�����
	 */
	public static final String MIME_TEXT = "text/plain";
	/**
	 * ͼƬ����
	 */
	public static final String MIME_IMAGE = "image/*";
	/**
	 * ��Ƶ����
	 */
	public static final String MIME_AUDIO = "audio/*";
	/**
	 * ��Ƶ����
	 */
	public static final String MIME_VIDEO = "video/*";
	
	/**
	 * ���������
	 * @param context ������
	 * @param uri ��ַ
	 */
	public static void callBrowser(Context context, String uri) {
		Intent intent = new Intent();
		intent.setData(Uri.parse(uri));
		intent.setAction(Intent.ACTION_VIEW);
		context.startActivity(intent);
	}
	
	/**
	 * ���÷��Ͷ���
	 * @param context
	 * @param number
	 * @param content
	 */
	public static void callSms(Context context, String number, String content) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SENDTO);
		intent.putExtra("sms_body", content);
		intent.setData(Uri.parse(String.format("smsto:%s", number)));
		context.startActivity(intent);
	}
	
	/**
	 * ���ò���绰
	 * @param context
	 * @param number
	 */
	public static void callPhone(Context context, String number) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_DIAL);
		intent.setData(Uri.parse(String.format("tel:%s", number)));
		context.startActivity(intent);
	}
	
	/**
	 * ���ı�
	 * @param context
	 * @param path
	 */
	public static void callText(Context context, String path) {
		Intent intent = new Intent();  
		intent.setDataAndType(Uri.fromFile(new File(path)), "text/plain");
		context.startActivity(intent);
	}
	
	/**
	 * ��ͼƬ
	 * @param context
	 * @param path
	 */
	public static void callImage(Context context, String path) {
		Intent intent = new Intent();
		intent.setDataAndType(Uri.fromFile(new File(path)),  "image/*");
		context.startActivity(intent);
	}
	
	/**
	 * ���ļ�
	 * @param context
	 * @param path
	 * @param mimeType
	 */
	public static void callFile(Context context, String path, String mimeType) {
		Intent intent = new Intent();
		intent.setDataAndType(Uri.fromFile(new File(path)),  mimeType);
		context.startActivity(intent);
	}
	
}
