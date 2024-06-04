package com.kopo.Customer;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer {
	int idx;
	String name;
	String id;
	String pw;
	int gender;
	String address;
	String created;

    // 기본 생성자
	 public Customer() {
	    }
	 
    // 생성자
	 public Customer(String name, String id, String pw, int gender, String address, String created) {
        this.name = name;
        this.id = id;
        this.pw = pw;
        this.gender = gender;
        this.address = address;
        this.created = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
    }
	 
	public Customer(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

}