package com.example.exampleproject.controller;

import com.example.exampleproject.model.Product;
import com.example.exampleproject.model.ProductCategory;
import com.example.exampleproject.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductCategoryController {

    private ProductCategoryService productCategoryService;

    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping("/product-category")
    public String findAll(Model model) {
        List<ProductCategory> productCategories = productCategoryService.findAll();
        model.addAttribute("productCategories", productCategories);
        return "product-category";
    }
}
