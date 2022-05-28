package com.example.exampleproject.controller;

import com.example.exampleproject.model.Product;
import com.example.exampleproject.model.ProductReview;
import com.example.exampleproject.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductReviewController {

    private ProductReviewService productReviewService;

    @Autowired
    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    @GetMapping("/productReview")
    public String findAll(Model model) {
        List<ProductReview> productReviews = productReviewService.findAllProducts();
        model.addAttribute("productReviews", productReviews);
        return "product-review";
    }
}
