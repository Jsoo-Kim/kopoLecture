package com.kopo.project1;

public class User {
    int idx;
    String name;
    String id;
    String password;
    String sex;
    String address;
    String phone;
    String user_type;
    String updated;
    String created;

    User() {

    }

    User(String name, String id, String password, String sex, String address, String phone) {
        this.name = name;
        this.id = id;
        this.password = Sha256EncryptUtil.shaEncoder(password);
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }

    User(String id, String password) {
        this.id = id;
        this.password = Sha256EncryptUtil.shaEncoder(password);
    }
}
