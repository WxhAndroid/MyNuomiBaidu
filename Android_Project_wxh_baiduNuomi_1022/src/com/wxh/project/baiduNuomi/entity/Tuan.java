package com.wxh.project.baiduNuomi.entity;

import java.io.Serializable;

import android.graphics.Bitmap;

public class Tuan implements Serializable{
	private static final long serialVersionUID = -147301740561349469L;
	private int _id;
	private String deal_id;
	private String brand_name;
	private int groupon_price;
	private String image;
	private int market_price;
	private int sale_count;
	private String short_title;
	private Bitmap bitmap;
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getDeal_id() {
		return deal_id;
	}
	public void setDeal_id(String deal_id) {
		this.deal_id = deal_id;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public int getGroupon_price() {
		return groupon_price;
	}
	public void setGroupon_price(int groupon_price) {
		this.groupon_price = groupon_price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getMarket_price() {
		return market_price;
	}
	public void setMarket_price(int market_price) {
		this.market_price = market_price;
	}
	public int getSale_count() {
		return sale_count;
	}
	public void setSale_count(int sale_count) {
		this.sale_count = sale_count;
	}
	public String getShort_title() {
		return short_title;
	}
	public void setShort_title(String short_title) {
		this.short_title = short_title;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}


}