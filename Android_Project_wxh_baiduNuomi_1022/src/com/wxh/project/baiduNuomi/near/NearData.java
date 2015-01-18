package com.wxh.project.baiduNuomi.near;

import java.io.Serializable;
import java.util.List;

public class NearData implements Serializable{
	private static final long serialVersionUID = -3203597936669406018L;
	private List<PoiList> poi_list;
	private int tuan_num;
	private int poi_num;
	
	
	public int getTuan_num() {
		return tuan_num;
	}
	public void setTuan_num(int tuan_num) {
		this.tuan_num = tuan_num;
	}
	public int getPoi_num() {
		return poi_num;
	}
	public void setPoi_num(int poi_num) {
		this.poi_num = poi_num;
	}
	public List<PoiList> getPoi_list() {
		return poi_list;
	}
	public void setPoi_list(List<PoiList> poi_list) {
		this.poi_list = poi_list;
	}
	
}
