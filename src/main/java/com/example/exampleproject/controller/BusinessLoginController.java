package com.example.exampleproject.controller;

import com.example.exampleproject.model.BusinessLogin;
import com.example.exampleproject.repository.BusinessLoginRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BusinessLoginController {

    private final BusinessLoginRep businessLoginRep;
    @Autowired
    public BusinessLoginController(BusinessLoginRep businessLoginRep) {
        this.businessLoginRep = businessLoginRep;
    }

    @GetMapping("/business-login")
    public String findAll(Model model) {
        List<BusinessLogin> businessLogins = businessLoginRep.findAll();
        model.addAttribute("businessLogins", businessLogins);
        return "businessLogin-list";
    }

    @GetMapping("/businessLogin-create")
    public String createBusinessLoginForm(BusinessLogin businessLogin) {
        return "businessLogin-create";
    }

    @PostMapping("/businessLogin-create")
    public String createBusinessLogin(BusinessLogin businessLogin) {
        businessLoginRep.save(businessLogin);
        return "redirect:/business-login";
    }
}
