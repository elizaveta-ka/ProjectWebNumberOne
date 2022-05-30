package com.example.exampleproject.controller;

import com.example.exampleproject.model.Buddy;
import com.example.exampleproject.model.BuddyLogin;
import com.example.exampleproject.repository.BuddyLoginRep;
import com.example.exampleproject.repository.BuddyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BuddyLoginController {

    private final BuddyLoginRep buddyLoginRep;

    private final BuddyRepository buddyRepository;
    @Autowired
    public BuddyLoginController(BuddyLoginRep buddyLoginRep, BuddyRepository buddyRepository) {
        this.buddyLoginRep = buddyLoginRep;
        this.buddyRepository = buddyRepository;
    }

    @GetMapping("/buddy-login")
    public String findAll(Model model) {
        List<BuddyLogin> buddyLogins = buddyLoginRep.findAll();
        model.addAttribute("buddyLogins", buddyLogins);
        return "buddyLogins-list";
    }

    @GetMapping("/buddyLogin-create")
    public String createBuddyLoginForm(BuddyLogin buddyLogin, Buddy buddy) {

        return "buddyLogin-create";
    }

    @PostMapping("/buddyLogin-create")
    public String createBuddyLogin(Buddy buddy) {
        buddyRepository.save(buddy);
        return "redirect:/buddy";
    }
}
