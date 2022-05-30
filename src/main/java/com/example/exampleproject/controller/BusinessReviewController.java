package com.example.exampleproject.controller;

import com.example.exampleproject.model.BusinessReview;
import com.example.exampleproject.repository.BusinessReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BusinessReviewController {

    private BusinessReviewRepository businessReviewRepository;

    @Autowired
    public BusinessReviewController(BusinessReviewRepository businessReviewRepository) {
        this.businessReviewRepository = businessReviewRepository;
    }

    @GetMapping("/business-review")
    public String findAll(Model model) {
        List<BusinessReview> businessReviews = businessReviewRepository.findAll();
        model.addAttribute("businessReviews", businessReviews);
        return "businessReview";
    }
}
