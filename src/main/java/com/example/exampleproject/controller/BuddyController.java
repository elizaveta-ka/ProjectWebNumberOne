package com.example.exampleproject.controller;

import com.example.exampleproject.Service.RoleOnPage;
import com.example.exampleproject.model.*;
import com.example.exampleproject.repository.BuddyRepository;
import com.example.exampleproject.repository.ProductRepository;
import com.example.exampleproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;


@Controller
public class BuddyController {
    private BuddyRepository buddyRepository;

    private ProductRepository productRepository;

    private UserRepository userRepository;

    private RoleOnPage roleOnPage;

    @Autowired
    public BuddyController(RoleOnPage roleOnPage, BuddyRepository buddyRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.buddyRepository = buddyRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.roleOnPage = roleOnPage;
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
    public String showBuddyPage(@PathVariable("id") int id, @AuthenticationPrincipal UserDetails user,Model model) throws UnsupportedEncodingException {
        User userInPage = userRepository.findByUsername(user.getUsername());
        if(userInPage.getRole().getName().equals("user")) {
            Buddy buddy = roleOnPage.findRoleBuddyOnPage(userInPage);
//            model.addAttribute("buddy", buddy);
            model.addAttribute("homeId", buddy.getBuddyId());
        } else if (userInPage.getRole().getName().equals("business")) {
            Business business = roleOnPage.findRoleBusinessOnPage(userInPage);
            model.addAttribute("homeId", business.getBusinessId());
        }
        Buddy buddyPage = buddyRepository.getById(id);
        model.addAttribute("user", userInPage);
        model.addAttribute("buddy", buddyPage);
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
   public String updateBuddy(@PathVariable int id, @AuthenticationPrincipal UserDetails user, Buddy buddy, BindingResult bindingResult, String formData) {
        User user1 = userRepository.findByUsername(user.getUsername());
        int bId = 0;
        Collection<Buddy> buddies = buddyRepository.findAll();
        for (var b:buddies) {
            User user2 = b.getUser();
            if(user1.equals(user2)) {
                bId = b.getBuddyId();
            }
        }
        Buddy buddy1 = buddyRepository.getById(bId);
        buddy1.setFirstName(buddy.getFirstName());
        buddy1.setLastName(buddy.getLastName());
        buddy1.setAge(buddy.getAge());
        System.out.println(buddy);
        buddy1.setAvatarImg(buddy.getAvatarImg());
        buddy1.setCity(buddy.getCity());
        buddyRepository.save(buddy1);
       return "redirect:/buddy/" + id;
   }
    @GetMapping("/buddy/{id}/wishlist")
    public String showWishlist(@PathVariable("id") int id, @AuthenticationPrincipal UserDetails user,  Model model) {
        User userInPage = userRepository.findByUsername(user.getUsername());
        if(userInPage.getRole().getName().equals("user")) {
            Buddy buddy = roleOnPage.findRoleBuddyOnPage(userInPage);
            model.addAttribute("buddy", buddy);
            model.addAttribute("homeId", buddy.getBuddyId());
        } else if (userInPage.getRole().getName().equals("business")) {
            Business business = roleOnPage.findRoleBusinessOnPage(userInPage);
            model.addAttribute("homeId", business.getBusinessId());
        }
        Buddy buddyPage = buddyRepository.getById(id);
        model.addAttribute("user", userInPage);
        model.addAttribute("buddyPage", buddyPage);
        return "wishlist";
    }


}
