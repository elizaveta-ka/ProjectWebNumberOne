package com.example.exampleproject.controller;

import com.example.exampleproject.model.ProductCategory;
import com.example.exampleproject.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductCategoryController {

    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductCategoryController(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @GetMapping("/product-category")
    public String findAll(Model model) {
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        model.addAttribute("productCategories", productCategories);
        return "product-category";
    }
}
