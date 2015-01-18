package com.wxh.project.baiduNuomi.entity;

import java.io.Serializable;

public class RushBuy implements Serializable{
	private static final long serialVersionUID = -857226445625141701L;
	private int market_price;//市场价
	private int current_price;//当前价
	private int sell_status;
	private FavourInfo favour_info;
	
	public int getMarket_price() {
		return market_price;
	}
	public void setMarket_price(int market_price) {
		this.market_price = market_price;
	}
	public int getCurrent_price() {
		return current_price;
	}
	public void setCurrent_price(int current_price) {
		this.current_price = current_price;
	}
	public int getSell_status() {
		return sell_status;
	}
	public void setSell_status(int sell_status) {
		this.sell_status = sell_status;
	}
	public FavourInfo getFavour_info() {
		return favour_info;
	}
	public void setFavour_info(FavourInfo favour_info) {
		this.favour_info = favour_info;
	}
	
	
	
	
}
