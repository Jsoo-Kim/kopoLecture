package com.spring_practice.productregistration.model;

public class Product {
    private String productName;
    private int price;
    private int stock;
    private String imageUrl;

    public Product() {
    }

    public Product(String productName, int price, int stock, String imageUrl) {
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}