package com.symbio.androidtraining;

public class DataCenter {
	private static DataCenter instance = null;
	
	private DataCenter(){
		
	}
	
	public static DataCenter getInstance(){
		if(instance == null){
			instance = new DataCenter();
		}
		return instance;
	}
	
	
	private User user = null;
	
	public User getUser(){
		if(user == null){
			user = new User("Truman", 110);
		}
		return user;
	}
	
	public void setUser(User aUser){
		this.user = aUser;
	}
	
	
	
	
}
