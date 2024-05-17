package com.spring_practice.productregistration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public void registerProduct(Product product) {
        productDao.registerProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }
}
