package com.wxh.project.baiduNuomi.entity;

import java.io.Serializable;

public class City implements Serializable {
	private static final long serialVersionUID = -5249173296828859004L;
	//Ä¬ÈÏµÄÖµ
	private String cityName ;
	private String cityId;
	private String cityUrl;
	
	public String getCityUrl() {
		return cityUrl;
	}
	public void setCityUrl(String cityUrl) {
		this.cityUrl = cityUrl;
	}
	public City() {
		super();
	}
	public City(String cityName, String cityId) {
		super();
		this.cityName = cityName;
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	
	
	
}
