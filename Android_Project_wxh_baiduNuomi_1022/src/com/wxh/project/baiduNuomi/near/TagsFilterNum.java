package com.wxh.project.baiduNuomi.near;

import java.io.Serializable;
import java.util.List;

public class TagsFilterNum implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<CategoryFilterNum> tags_filter_num;
	public List<CategoryFilterNum> getTags_filter_num() {
		return tags_filter_num;
	}
	public void setTags_filter_num(List<CategoryFilterNum> tags_filter_num) {
		this.tags_filter_num = tags_filter_num;
	}
	
	
}
