package com.kopo.project2.model;

import com.kopo.project2.Sha256EncryptUtil;

public class User {
    public int idx;
    public String name;
    public String id;
    public String password;
    public String sex;
    public String address;
    public String phone;
    public String user_type;
    public String updated;
    public String created;
    public String email;
    public Boolean isEmailVerified;

    public User() {
    	
    }
    
    public User(String name, String id, String sex, String address, String phone) {
        this(name, id, null, sex, address, phone, false);
    }

    public User(String name, String id, String password, String sex, String address, String phone) {
        this(name, null, password, sex, address, phone, false);
    }
    
    public User(String name, String email, String password, String sex, String address, String phone, Boolean isEmailVerified) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    	this.name = name;
    	this.email = email;
    	this.password = Sha256EncryptUtil.shaEncoder(password);
    	this.sex = sex;
    	this.address = address;
    	this.phone = phone;
    	this.user_type = "guest"; // 기본값은 일반 사용자
    	this.isEmailVerified = isEmailVerified != null ? isEmailVerified : false; // 인증 여부 기본값
    }

    public User(String id, String password) {
    	this.id = id;
    	this.password = Sha256EncryptUtil.shaEncoder(password);
    }
    
}
