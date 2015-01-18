package com.wxh.project.baiduNuomi.near;

import java.io.Serializable;

public class CategoryFilterNum implements Serializable{
	private static final long serialVersionUID = 5606608801758277652L;
	private String filter_id;
	private int count;
	public String getFilter_id() {
		return filter_id;
	}
	public void setFilter_id(String filter_id) {
		this.filter_id = filter_id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
