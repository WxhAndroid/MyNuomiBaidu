package com.wxh.project.baiduNuomi.entity;

import java.io.Serializable;

public class MerchantBaseinfo implements Serializable{
	private static final long serialVersionUID = -7751558042702593521L;
	private int shop_num;
	private SellerLlist sellerLlist;
	public int getShop_num() {
		return shop_num;
	}
	public void setShop_num(int shop_num) {
		this.shop_num = shop_num;
	}
	public SellerLlist getSellerLlist() {
		return sellerLlist;
	}
	public void setSellerLlist(SellerLlist sellerLlist) {
		this.sellerLlist = sellerLlist;
	}
	
}
