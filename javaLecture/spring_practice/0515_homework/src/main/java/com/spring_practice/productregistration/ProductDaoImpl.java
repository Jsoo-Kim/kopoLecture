package com.spring_practice.productregistration;

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
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }
}
