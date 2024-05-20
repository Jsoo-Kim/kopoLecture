package com.spring_practice.productregistration.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring_practice.productregistration.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void registerProduct(Product product) {
        String sql = "INSERT INTO products (productName, price, stock, imageUrl) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getProductName(), product.getPrice(), product.getStock(), product.getImageUrl());
    }

    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM products ORDER BY id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class)); // ResultSet의 각 행을 Java 객체로 매핑
    }

}
