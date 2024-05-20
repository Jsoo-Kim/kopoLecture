package com.spring_practice.productregistration.dao;

import java.util.List;

import com.spring_practice.productregistration.model.Product;

public interface ProductDao {
    void registerProduct(Product product);
    List<Product> getAllProducts();
}
