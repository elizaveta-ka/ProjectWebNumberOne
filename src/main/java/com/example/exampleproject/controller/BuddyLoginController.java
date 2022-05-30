package com.example.exampleproject.controller;

import com.example.exampleproject.model.Buddy;
import com.example.exampleproject.model.BuddyLogin;
import com.example.exampleproject.service.BuddyLoginService;
import com.example.exampleproject.service.BuddyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BuddyLoginController {

    private final BuddyLoginService buddyLoginService;

    private final BuddyService buddyService;
    @Autowired
    public BuddyLoginController(BuddyLoginService buddyLoginService, BuddyService buddyService) {
        this.buddyLoginService = buddyLoginService;
        this.buddyService = buddyService;
    }

    @GetMapping("/buddy-login")
    public String findAll(Model model) {
        List<BuddyLogin> buddyLogins = buddyLoginService.findAll();
        model.addAttribute("buddyLogins", buddyLogins);
        return "buddyLogins-list";
    }

    @GetMapping("/buddyLogin-create")
    public String createBuddyLoginForm(BuddyLogin buddyLogin, Buddy buddy) {

        return "buddyLogin-create";
    }

    @PostMapping("/buddyLogin-create")
    public String createBuddyLogin(Buddy buddy) {
        buddyService.saveBuddy(buddy);
        return "redirect:/buddy";
    }
}
