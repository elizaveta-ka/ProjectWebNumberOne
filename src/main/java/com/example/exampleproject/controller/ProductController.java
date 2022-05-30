package com.example.exampleproject.controller;

import com.example.exampleproject.model.Product;
import com.example.exampleproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping("/products")
    public String findAll(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "product-page";
    }

//    @GetMapping("/products")
//    public String findAllPr(Model model) {
//        List<Product> products = productService.findAllProducts();
//        Product product = products.get(0);
//        model.addAttribute("product", product);
//        return "product-page";
//    }

}
