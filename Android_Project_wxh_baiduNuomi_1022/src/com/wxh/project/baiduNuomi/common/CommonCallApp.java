package com.wxh.project.baiduNuomi.common;
import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class CommonCallApp {
	/**
	 * 文本类型
	 */
	public static final String MIME_TEXT = "text/plain";
	/**
	 * 图片类型
	 */
	public static final String MIME_IMAGE = "image/*";
	/**
	 * 音频类型
	 */
	public static final String MIME_AUDIO = "audio/*";
	/**
	 * 视频类型
	 */
	public static final String MIME_VIDEO = "video/*";
	
	/**
	 * 调用浏览器
	 * @param context 上下文
	 * @param uri 地址
	 */
	public static void callBrowser(Context context, String uri) {
		Intent intent = new Intent();
		intent.setData(Uri.parse(uri));
		intent.setAction(Intent.ACTION_VIEW);
		context.startActivity(intent);
	}
	
	/**
	 * 调用发送短信
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
	 * 调用拨打电话
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
	 * 打开文本
	 * @param context
	 * @param path
	 */
	public static void callText(Context context, String path) {
		Intent intent = new Intent();  
		intent.setDataAndType(Uri.fromFile(new File(path)), "text/plain");
		context.startActivity(intent);
	}
	
	/**
	 * 打开图片
	 * @param context
	 * @param path
	 */
	public static void callImage(Context context, String path) {
		Intent intent = new Intent();
		intent.setDataAndType(Uri.fromFile(new File(path)),  "image/*");
		context.startActivity(intent);
	}
	
	/**
	 * 打开文件
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
