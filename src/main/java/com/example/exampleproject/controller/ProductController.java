package com.example.exampleproject.controller;

import com.example.exampleproject.model.Business;
import com.example.exampleproject.model.Product;
import com.example.exampleproject.model.ProductCategory;
import com.example.exampleproject.repository.ProductCategoryRepository;
import com.example.exampleproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {
    private final ProductRepository productRepository;

    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }
    @GetMapping("/products")
    public String findAll(Model model) {
        List<Product> products = productRepository.findAll();
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("productCategories", productCategories);
        return "product-list";
    }
    @GetMapping("/fragments")
    public String getHome(){
        return "main-fragments.html";
    }

//    @GetMapping("/products")
//    public String findAllPr(Model model) {
//        List<Product> products = productService.findAllProducts();
//        Product product = products.get(0);
//        model.addAttribute("product", product);
//        return "product-page";
//    }

    @PostMapping("/product-create")
    public String createProduct(Product product) {
        productRepository.save(product);
        return "redirect:/product";
    }
}
