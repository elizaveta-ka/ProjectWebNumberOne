package com.example.exampleproject.controller;

import com.example.exampleproject.model.Buddy;
import com.example.exampleproject.model.Human;
import com.example.exampleproject.repository.BuddyRepository;
import com.example.exampleproject.service.BuddyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class BuddyController {
    private BuddyService buddyService;

    @Autowired
    public BuddyController(BuddyService buddyService) {
        this.buddyService = buddyService;
    }

    @GetMapping("/buddy")
    public String findAll(Model model) {
        List<Buddy> buddies = buddyService.findAll();
        model.addAttribute("buddies", buddies);
        return "buddy-list";
    }

    @GetMapping("/buddy-create")
   public String createUserForm(Buddy buddy) {
    return "buddy-create";
   }

    @PostMapping("/buddy-create")
   public String createBuddy(Buddy buddy) {
        buddyService.saveBuddy(buddy);
    return "redirect:/buddy";
   }

    @GetMapping("/buddy-update/{id}")
   public String updateBuddyForm(@PathVariable("id") int id, Model model) {
        Buddy buddy = buddyService.findById(id);
        model.addAttribute("buddy", buddy);
    return "/buddy-update";
   }

    @PostMapping("/buddy-update")
   public String updateBuddy(Buddy buddy) {
        buddyService.saveBuddy(buddy);
       return "redirect:/buddy";
   }

}
