package com.spring_practice.productregistration.service;

import java.util.List;

import com.spring_practice.productregistration.model.Product;

public interface ProductService {
	void registerProduct(Product product);

	List<Product> getAllProducts();

}
