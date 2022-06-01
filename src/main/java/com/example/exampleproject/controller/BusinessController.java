package com.example.exampleproject.controller;

import com.example.exampleproject.model.Buddy;
import com.example.exampleproject.model.Business;
import com.example.exampleproject.model.Product;
import com.example.exampleproject.repository.BusinessRepository;
import com.example.exampleproject.repository.BusinessReviewRepository;
import com.example.exampleproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class BusinessController {

    private final BusinessRepository businessRepository;

    private final ProductRepository productRepository;

    private final BusinessReviewRepository businessReviewRepository;


    @Autowired
    public BusinessController(BusinessRepository businessRepository, ProductRepository productRepository, BusinessReviewRepository businessReviewRepository) {
        this.businessRepository = businessRepository;
        this.productRepository = productRepository;
        this.businessReviewRepository = businessReviewRepository;
    }

    @GetMapping("/business")
    public String findAll(Model model) {
        List<Business> businesses = businessRepository.findAll();
        System.out.println(businesses);
        model.addAttribute("businesses", businesses);
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "business-list";
    }


    @GetMapping("/business-create")
    public String createBusinessForm(Business business) {
        return "business-create";
    }

    @PostMapping("/business-create")
    public String createBusiness(Business business) {
        businessRepository.save(business);
        return "redirect:/business";
    }
    @GetMapping("/business-delete/{id}")
    public String deleteBusiness(@PathVariable("id") int id) {
        businessRepository.deleteById(id);
        return "redirect:/business";
    }
    @GetMapping("/business-update/{id}")
    public String updateBusinessForm(@PathVariable("id") int id, Model model) {
        Optional<Business> business = businessRepository.findById(id);  //Optional???
        model.addAttribute("business", business);
        return "/business-update";
    }

    @PostMapping("/business-update")
    public String updateBusiness(Business business) {
        businessRepository.save(business);
        return "redirect:/business";
    }
}
