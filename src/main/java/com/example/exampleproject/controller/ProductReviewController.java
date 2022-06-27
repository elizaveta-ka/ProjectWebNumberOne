package com.example.exampleproject.controller;

import com.example.exampleproject.model.ProductReview;
import com.example.exampleproject.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ProductReviewController {

    private ProductReviewRepository productReviewRepository;

    @Autowired
    public ProductReviewController(ProductReviewRepository productReviewRepository) {
        this.productReviewRepository = productReviewRepository;
    }

    @GetMapping("/productReview")
    public String findAll(Model model) {
        List<ProductReview> productReviews = productReviewRepository.findAll();
        model.addAttribute("productReviews", productReviews);
        return "product-review";
    }
}
