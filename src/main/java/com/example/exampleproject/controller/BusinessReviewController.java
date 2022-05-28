package com.example.exampleproject.controller;

import com.example.exampleproject.model.Business;
import com.example.exampleproject.model.BusinessReview;
import com.example.exampleproject.model.Product;
import com.example.exampleproject.service.BusinessReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BusinessReviewController {

    private BusinessReviewService businessReviewService;

    @Autowired
    public BusinessReviewController(BusinessReviewService businessReviewService) {
        this.businessReviewService = businessReviewService;
    }

    @GetMapping("/business-review")
    public String findAll(Model model) {
        List<BusinessReview> businessReviews = businessReviewService.findAll();
        model.addAttribute("businessReviews", businessReviews);
        return "businessReview";
    }
}
