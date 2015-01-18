package com.wxh.project.baiduNuomi.entity;

import java.io.Serializable;

import android.graphics.Bitmap;

public class TitleAboutImage implements Serializable{
	private static final long serialVersionUID = 8010678490254695037L;
	private String image;
	private Bitmap bitmap;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	
}
