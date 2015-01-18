package com.wxh.project.baiduNuomi.near;

import java.io.Serializable;

public class FavourList  implements Serializable{
	private static final long serialVersionUID = -8506666173704380650L;
	private String deal_id     ;
	private int reductionAmount;
	private int price          ;
//	private String activityList;
	private String list_text   ;
//	private String hongbao_info;
	private String price_tag_id;
	public String getDeal_id() {
		return deal_id;
	}
	public void setDeal_id(String deal_id) {
		this.deal_id = deal_id;
	}
	public int getReductionAmount() {
		return reductionAmount;
	}
	public void setReductionAmount(int reductionAmount) {
		this.reductionAmount = reductionAmount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getList_text() {
		return list_text;
	}
	public void setList_text(String list_text) {
		this.list_text = list_text;
	}
	public String getPrice_tag_id() {
		return price_tag_id;
	}
	public void setPrice_tag_id(String price_tag_id) {
		this.price_tag_id = price_tag_id;
	}
	
}
