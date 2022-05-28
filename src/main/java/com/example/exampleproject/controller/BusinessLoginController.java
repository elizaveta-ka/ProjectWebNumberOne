package com.example.exampleproject.controller;

import com.example.exampleproject.model.BuddyLogin;
import com.example.exampleproject.model.BusinessLogin;
import com.example.exampleproject.service.BuddyLoginService;
import com.example.exampleproject.service.BusinessLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BusinessLoginController {

    private final BusinessLoginService businessLoginService;
    @Autowired
    public BusinessLoginController(BusinessLoginService businessLoginService) {
        this.businessLoginService = businessLoginService;
    }

    @GetMapping("/business-login")
    public String findAll(Model model) {
        List<BusinessLogin> businessLogins = businessLoginService.findAll();
        model.addAttribute("businessLogins", businessLogins);
        return "businessLogin-list";
    }

    @GetMapping("/businessLogin-create")
    public String createBusinessLoginForm(BusinessLogin businessLogin) {
        return "businessLogin-create";
    }

    @PostMapping("/businessLogin-create")
    public String createBusinessLogin(BusinessLogin businessLogin) {
        businessLoginService.saveBusinessLogin(businessLogin);
        return "redirect:/business-login";
    }
}
