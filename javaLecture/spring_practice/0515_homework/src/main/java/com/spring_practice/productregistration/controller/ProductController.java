package com.spring_practice.productregistration.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring_practice.productregistration.model.Product;
import com.spring_practice.productregistration.service.ProductService;


@Controller
public class ProductController {
	
	
	@Autowired
	private ProductService productService;

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class); // 아직은 안 쓰는 중
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
	    LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = now.format(formatter);
	    System.out.println("현재 시각: " + formattedDateTime);
		return "home";
	}
	
	@RequestMapping(value = "/registerProductForm", method = RequestMethod.GET)
	public String registerProductForm() {
		return "registerProduct";
	}
	
	@RequestMapping(value = "/registerProduct", method = RequestMethod.GET)
	public String registerProduct(@RequestParam("productName") String productName,
			@RequestParam("price") int price,
			@RequestParam("stock") int stock,
			@RequestParam("imageUrl") String imageUrl) {
		
		Product product = new Product(productName, price, stock, imageUrl);
		productService.registerProduct(product);
		
		return "redirect:/productList";
	}
	
	@RequestMapping(value = "/productList", method = RequestMethod.GET)
	public String productList(Model model) {
		
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		
		return "productList";
	}
	
    

}
