package com.example.exampleproject.controller;

import com.example.exampleproject.model.*;
import com.example.exampleproject.repository.BuddyRepository;
import com.example.exampleproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Controller
public class BuddyController {
    private BuddyRepository buddyRepository;

    private ProductRepository productRepository;

    @Autowired
    public BuddyController(BuddyRepository buddyRepository, ProductRepository productRepository) {
        this.buddyRepository = buddyRepository;
        this.productRepository = productRepository;
    }
    @GetMapping("/buddy")
    public String findAll(Model model) {
        List<Buddy> buddies = buddyRepository.findAll();
        List<Product> products = productRepository.findAll();
        model.addAttribute("buddies", buddies);
        model.addAttribute("products", products);
        return "buddy-list";
    }

    @GetMapping("/buddy/{id}")
    public String showBuddyPage(@PathVariable("id") int id, Model model) {
        Buddy buddy = buddyRepository.getById(id);
        model.addAttribute("buddy", buddy);
        return "buddy-page";
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
        Buddy buddy = buddyRepository.getById(id);
        List<Buddy> buddies = buddyRepository.findAll();

//       buddyRepository.deleteById(id);

        return "redirect:/buddy";
    }
    // работает
    @GetMapping("/buddy-update/{id}")
   public String updateBuddyForm(@PathVariable("id") int id, Model model) {
        Optional<Buddy> buddy = buddyRepository.findById(id);  //Optional???
        model.addAttribute("buddy", buddy);
    return "/buddy-update";
   }
    // работает
    @PostMapping("/buddy-update")
   public String updateBuddy(Buddy buddy) {
        Optional<Buddy> buddy1 = buddyRepository.findById(buddy.getBuddyId());
        Set<Product> products = buddy1.get().getProducts();
        for (var product:products) {
            buddy.addProduct(product);
        }
        buddyRepository.save(buddy);
       return "redirect:/buddy";
   }



}
