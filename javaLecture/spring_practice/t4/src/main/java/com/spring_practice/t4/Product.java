package com.spring_practice.t4;

public class Product {
	int idx;
	String name;
	int price;
	String created;
	
	Product() {
		
	}
	
	public Product(String name, int price, String created) {
		this.name = name;
		this.price = price;
		this.created = created;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}
	
	
}
