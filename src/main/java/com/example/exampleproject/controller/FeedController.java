package com.example.exampleproject.controller;


import com.example.exampleproject.model.Product;
import com.example.exampleproject.model.ProductCategory;
import com.example.exampleproject.repository.BuddyRepository;
import com.example.exampleproject.repository.ProductCategoryRepository;
import com.example.exampleproject.repository.ProductRepository;
import com.example.exampleproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FeedController {

    private ProductRepository productRepository;
    private ProductCategoryRepository productCategoryRepository;
    private BuddyRepository buddyRepository;

    private final UserRepository userRepository;

    @Autowired
    public FeedController(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, BuddyRepository buddyRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.buddyRepository = buddyRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/feed")
    public String findAll(Model model) {
        List<Product> products = productRepository.findAll();
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        model.addAttribute("products", products);

        model.addAttribute("productCategories", productCategories);
        return "feed";
    }

    @GetMapping("/notifications")
    public String notificationPage() {

        return "notifications";
    }

}
