package com.symbio.androidtraining;

import android.app.Application;

public class TrainingApplication extends Application{

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	private User user = null;
	public User getUser(){
		if(user == null){
			user = new User("Truman",100);
		}
		return user;
	}

	public void setUser(User aUser){
		this.user = aUser;
	}
}
