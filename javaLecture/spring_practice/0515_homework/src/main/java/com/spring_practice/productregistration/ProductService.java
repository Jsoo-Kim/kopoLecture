package com.spring_practice.productregistration;

import java.util.List;

public interface ProductService {
	void registerProduct(Product product);

	List<Product> getAllProducts();

}
