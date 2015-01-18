package com.wxh.project.baiduNuomi.entity;

import java.io.Serializable;
import java.util.List;

public class HomeCategory implements Serializable{
	private static final long serialVersionUID = 5740376508314556880L;
	private List<Category> categoryList;
	private List<Special> specialList;
	private Banners banners;
	
	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	public List<Special> getSpecialList() {
		return specialList;
	}
	public void setSpecialList(List<Special> specialList) {
		this.specialList = specialList;
	}
	public Banners getBanners() {
		return banners;
	}
	public void setBanners(Banners banners) {
		this.banners = banners;
	}
	
	
}
