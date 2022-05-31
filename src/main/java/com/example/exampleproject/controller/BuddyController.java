package com.example.exampleproject.controller;

import com.example.exampleproject.model.Buddy;
import com.example.exampleproject.model.BuddyLogin;
import com.example.exampleproject.repository.BuddyLoginRep;
import com.example.exampleproject.repository.BuddyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
public class BuddyController {
    private BuddyRepository buddyRepository;

    private BuddyLoginRep buddyLoginRep;
    @Autowired
    public BuddyController(BuddyRepository buddyRepository, BuddyLoginRep buddyLoginRep) {
        this.buddyRepository = buddyRepository;
        this.buddyLoginRep = buddyLoginRep;
    }
    @GetMapping("/buddy")
    public String findAll(Model model) {
        List<Buddy> buddies = buddyRepository.findAll();
        List<BuddyLogin> buddyLogins = buddyLoginRep.findAll();
        model.addAttribute("buddies", buddies);
        model.addAttribute("buddyLogins", buddyLogins);
        return "buddy-list";
    }

    @GetMapping("/buddy-create")
   public String createUserForm(Buddy buddy) {
    return "buddy-create";
   }

    @PostMapping("/buddy-create")
   public String createBuddy(Buddy buddy) {
        buddyRepository.save(buddy);
    return "redirect:/buddy";
   }
    @GetMapping("/buddy-delete/{id}")
    public String deleteBuddy(@PathVariable("id") int id) {
       buddyRepository.deleteById(id);
        return "redirect:/buddy";
    }
    @GetMapping("/buddy-update/{id}")
   public String updateBuddyForm(@PathVariable("id") int id, Model model) {
        Optional<Buddy> buddy = buddyRepository.findById(id);  //Optional???
        model.addAttribute("buddy", buddy);
    return "/buddy-update";
   }

    @PostMapping("/buddy-update")
   public String updateBuddy(Buddy buddy) {
        buddyRepository.save(buddy);
       return "redirect:/buddy";
   }



}
