package com.example.exampleproject.controller;

import com.example.exampleproject.model.BusinessReview;
import com.example.exampleproject.model.ProductReview;
import com.example.exampleproject.repository.BusinessReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
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

//    @RequestMapping(value = "/business/{id}/add-review", method = RequestMethod.POST)
//    public String addReview(@PathVariable("id") int id, Model model, BindingResult result, @Valid BusinessReview br) {
//        businessReviewRepository.save(br);
//        return "redirect:/business" + id;
//    }
    @GetMapping("/business/{id}/update-review")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        BusinessReview br = businessReviewRepository.findById(id).get();
        model.addAttribute("productReview", br);
        return "redirect:/business" + id;
    }
    @RequestMapping(value = "/business/{id}/edit-review", method = RequestMethod.POST)
    public String editReview(@PathVariable("id") int id, Model model, BindingResult result, @Valid BusinessReview br) {
        businessReviewRepository.save(br);
        return "redirect:/business" + id;
    }

}
