package com.wxh.project.baiduNuomi.near;

import java.io.Serializable;
import java.util.List;

public class AreaFilterNum implements Serializable{
	private static final long serialVersionUID = 5750001461760996909L;
	private String key;
	private List<CategoryFilterNum> count;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<CategoryFilterNum> getCount() {
		return count;
	}
	public void setCount(List<CategoryFilterNum> count) {
		this.count = count;
	}
	
	
}
