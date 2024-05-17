package com.spring_practice.productregistration;


import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		return "home";
	}
	

    @RequestMapping(value = "/registerProduct", method = RequestMethod.POST)
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
