package com.wxh.project.baiduNuomi.entity;

import java.io.Serializable;

public class CommentInfo implements Serializable{
	private static final long serialVersionUID = -49011466641943722L;
	private double average_score_display;
	private int user_num;
	
	public double getAverage_score_display() {
		return average_score_display;
	}
	public void setAverage_score_display(double d) {
		this.average_score_display = d;
	}
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}

}
