package com.example.exampleproject.controller;

import com.example.exampleproject.model.Business;
import com.example.exampleproject.model.Product;
import com.example.exampleproject.service.BusinessService;
import com.example.exampleproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BusinessController {

    private final BusinessService businessService;

    private final ProductService productService;


    @Autowired
    public BusinessController(BusinessService businessService, ProductService productService) {
        this.businessService = businessService;
        this.productService = productService;
    }
//можно вывести и продукты и бизнес вместе
    @GetMapping("/business")
    public String findAll(Model model) {
        List<Business> businesses = businessService.findAll();
        System.out.println(businesses);
        model.addAttribute("businesses", businesses);
        List<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "business-list";
    }
}
