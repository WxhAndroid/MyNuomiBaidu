package com.wxh.project.baiduNuomi.near;

import java.io.Serializable;
import java.util.List;

public class PoiList implements Serializable{
	private static final long serialVersionUID = -4645346145005373285L;
	private String poi_name;
	private String poi_distance;
	private int lat;
	private int lng;
	private String bizarea_title;
	private String tuan_more;
	private List<TuanList> tuan_list;
	private int tuan_num;
	public String getPoi_name() {
		return poi_name;
	}
	public void setPoi_name(String poi_name) {
		this.poi_name = poi_name;
	}
	public String getPoi_distance() {
		return poi_distance;
	}
	public void setPoi_distance(String poi_distance) {
		this.poi_distance = poi_distance;
	}
	public int getLat() {
		return lat;
	}
	public void setLat(int lat) {
		this.lat = lat;
	}
	public int getLng() {
		return lng;
	}
	public void setLng(int lng) {
		this.lng = lng;
	}
	public String getBizarea_title() {
		return bizarea_title;
	}
	public void setBizarea_title(String bizarea_title) {
		this.bizarea_title = bizarea_title;
	}
	public String getTuan_more() {
		return tuan_more;
	}
	public void setTuan_more(String tuan_more) {
		this.tuan_more = tuan_more;
	}
	public List<TuanList> getTuan_list() {
		return tuan_list;
	}
	public void setTuan_list(List<TuanList> tuan_list) {
		this.tuan_list = tuan_list;
	}
	public int getTuan_num() {
		return tuan_num;
	}
	public void setTuan_num(int tuan_num) {
		this.tuan_num = tuan_num;
	}
	
	
}
