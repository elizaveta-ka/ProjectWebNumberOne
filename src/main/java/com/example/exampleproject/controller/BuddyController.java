package com.example.exampleproject.controller;

import com.example.exampleproject.model.*;
import com.example.exampleproject.repository.BuddyRepository;
import com.example.exampleproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
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
    public String showBuddyPage(@PathVariable("id") int id, Model model) throws UnsupportedEncodingException {
        Buddy buddy = buddyRepository.getById(id);
        model.addAttribute("buddy", buddy);
//        byte[] encodeBase64 = Base64.encode(buddy.getAvatarImg());
//        String base64Encoded = new String(encodeBase64, "UTF-8");
//        model.addAttribute("image", base64Encoded );
        return "buddy-page";
    }

    @GetMapping("/buddy-delete/{id}")
    public String deleteBuddy(@PathVariable("id") int id) {
        Buddy buddy = buddyRepository.getById(id);
        List<Buddy> buddies = buddyRepository.findAll();

//       buddyRepository.deleteById(id);

        return "redirect:/buddy";
    }

    @PostMapping("/buddy/{id}")
   public String updateBuddy(@PathVariable int id,Buddy buddy, BindingResult bindingResult, String formData) {
        Buddy buddy1 = buddyRepository.getById(id);
        buddy1.setFirstName(buddy.getFirstName());
        buddy1.setLastName(buddy.getLastName());
        buddy1.setAge(buddy.getAge());
        buddy1.setCity(buddy.getCity());


//        Set<Product> products = buddy1.getProducts();
//        for (var product:products) {
//            buddy.addProduct(product);
//        }
        buddyRepository.save(buddy1);
       return "redirect:/buddy/" + id;
   }
    @GetMapping("/buddy/{id}/wishlist")
    public String showWishlist(@PathVariable("id") int id, Model model) {
        Buddy buddy = buddyRepository.getById(id);
        model.addAttribute("buddy", buddy);
        return "wishlist";
    }


}
