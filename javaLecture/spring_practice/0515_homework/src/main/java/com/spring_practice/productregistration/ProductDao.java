package com.spring_practice.productregistration;

import java.util.List;

public interface ProductDao {
    void registerProduct(Product product);
    List<Product> getAllProducts();
}
