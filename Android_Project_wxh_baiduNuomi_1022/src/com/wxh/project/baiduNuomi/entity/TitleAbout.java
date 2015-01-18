package com.wxh.project.baiduNuomi.entity;

import java.io.Serializable;
import java.util.List;

import android.graphics.Bitmap;

public class TitleAbout implements Serializable{
	private static final long serialVersionUID = -5603169303599817056L;
	private String image;
	private String min_image;
	private String business_title;
	private String subtitle;
	private String remain_time;
	private int sell_count;//销售数量
	private Bitmap bitmap;
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public String getMin_image() {
		return min_image;
	}
	public void setMin_image(String min_image) {
		this.min_image = min_image;
	}
	public String getBusiness_title() {
		return business_title;
	}
	public void setBusiness_title(String business_title) {
		this.business_title = business_title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public int getSell_count() {
		return sell_count;
	}
	public void setSell_count(int sell_count) {
		this.sell_count = sell_count;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRemain_time() {
		return remain_time;
	}
	public void setRemain_time(String remain_time) {
		this.remain_time = remain_time;
	}
	
	
}
