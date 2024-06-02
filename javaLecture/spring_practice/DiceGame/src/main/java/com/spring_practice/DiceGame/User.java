package com.spring_practice.DiceGame;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	int id;
	String name;
	String userId;
	String userPw;
	String gender;
	String address;
	String created;

	public User() {
		
	}
	
	public User(int id, String name, String userId, String userPw, String gender, String address, String created) {
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.userPw = userPw;
		this.gender = gender;
		this.address = address;
		this.created = created;
	}

	public User(String name, String userId, String userPw, String gender, String address, String created) {
		this.name = name;
		this.userId = userId;
		this.userPw = userPw;
		this.gender = gender;
		this.address = address;
		this.created = created;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}
	
	

}
