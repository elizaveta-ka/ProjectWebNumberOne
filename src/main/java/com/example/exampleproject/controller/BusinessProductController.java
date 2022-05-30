package com.example.exampleproject.controller;

import com.example.exampleproject.repository.BusinessProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BusinessProductController {

    private BusinessProductRepository businessProductRepository;

    @Autowired
    public BusinessProductController(BusinessProductRepository businessProductRepository) {
        this.businessProductRepository = businessProductRepository;
    }
}
