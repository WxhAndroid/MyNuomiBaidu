package com.wxh.project.baiduNuomi.entity;

import java.io.Serializable;

import android.graphics.Bitmap;

public class Banners implements Serializable{
	private static final long serialVersionUID = -9161931104425813672L;
	private String picture_url;
	private Bitmap bitmap;
	
	public String getPicture_url() {
		return picture_url;
	}
	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	
}
