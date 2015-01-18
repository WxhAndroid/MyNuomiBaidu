package com.wxh.project.baiduNuomi.entity;

import java.io.Serializable;
import java.util.List;


import android.graphics.Bitmap;


public class ProductDetail implements Serializable{
	private static final long serialVersionUID = -3084706533189829421L;
	private String	deal_id;
	private int deal_type;
	private RushBuy rushBuy;
	private TitleAbout titleAbout;
	private List<SafeguardInfo> safeguardInfo;
	private BuyContent buyContent;
	private ConsumerTips consumerTips;
	private CommentInfo commentInfo;
	private MerchantBaseinfo merchantBaseinfo;
	private Bitmap bitmap;
	public String getDeal_id() {
		return deal_id;
	}
	public void setDeal_id(String deal_id) {
		this.deal_id = deal_id;
	}
	public int getDeal_type() {
		return deal_type;
	}
	public void setDeal_type(int deal_type) {
		this.deal_type = deal_type;
	}
	public RushBuy getRushBuy() {
		return rushBuy;
	}
	public void setRushBuy(RushBuy rushBuy) {
		this.rushBuy = rushBuy;
	}
	public TitleAbout getTitleAbout() {
		return titleAbout;
	}
	public void setTitleAbout(TitleAbout titleAbout) {
		this.titleAbout = titleAbout;
	}
	public List<SafeguardInfo> getSafeguardInfo() {
		return safeguardInfo;
	}
	public void setSafeguardInfo(List<SafeguardInfo> safeguardInfo) {
		this.safeguardInfo = safeguardInfo;
	}
	public BuyContent getBuyContent() {
		return buyContent;
	}
	public void setBuyContent(BuyContent buyContent) {
		this.buyContent = buyContent;
	}
	public ConsumerTips getConsumerTips() {
		return consumerTips;
	}
	public void setConsumerTips(ConsumerTips consumerTips) {
		this.consumerTips = consumerTips;
	}
	public CommentInfo getCommentInfo() {
		return commentInfo;
	}
	public void setCommentInfo(CommentInfo commentInfo) {
		this.commentInfo = commentInfo;
	}
	public MerchantBaseinfo getMerchantBaseinfo() {
		return merchantBaseinfo;
	}
	public void setMerchantBaseinfo(MerchantBaseinfo merchantBaseinfo) {
		this.merchantBaseinfo = merchantBaseinfo;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}


}
