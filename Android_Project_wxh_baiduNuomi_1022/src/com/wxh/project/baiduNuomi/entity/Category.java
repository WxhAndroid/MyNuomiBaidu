package com.wxh.project.baiduNuomi.entity;

import java.io.Serializable;

import android.graphics.Bitmap;

/**
 *分类显示页面 
 *
 */
public class Category implements Serializable{
	private static final long serialVersionUID = 2163933259588731797L;
	private String category_name;
	private String category_picurl;
	private Bitmap bitmap;
	
	/**
	 *无参构造函数 
	 */
	public Category() {
		super();
	}
	/**
	 * Category构造函数
	 * @param category_name 图片名字
	 * @param category_picurl 图片地址
	 */
	public Category(String category_name, String category_picurl) {
		super();
		this.category_name = category_name;
		this.category_picurl = category_picurl;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCategory_picurl() {
		return category_picurl;
	}
	public void setCategory_picurl(String category_picurl) {
		this.category_picurl = category_picurl;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	
}
