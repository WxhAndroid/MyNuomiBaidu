package com.wxh.project.baiduNuomi.entity;

import java.io.Serializable;
import java.util.List;

public class FavourInfo implements Serializable{
	private static final long serialVersionUID = -1252803383024796356L;
	private int favour_price;
	private List<FavourInfoText> text;
	
	public int getFavour_price() {
		return favour_price;
	}
	public void setFavour_price(int favour_price) {
		this.favour_price = favour_price;
	}
	public List<FavourInfoText> getText() {
		return text;
	}
	public void setText(List<FavourInfoText> text) {
		this.text = text;
	}
	
	
}
