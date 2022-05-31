package com.example.exampleproject.controller;

import com.example.exampleproject.service.BusinessProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BusinessProductController {

    private BusinessProductService businessProductService;

    @Autowired
    public BusinessProductController(BusinessProductService businessProductService) {
        this.businessProductService = businessProductService;
    }
}
