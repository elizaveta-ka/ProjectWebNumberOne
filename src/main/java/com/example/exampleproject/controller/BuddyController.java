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
import java.util.Set;


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

    @GetMapping("/buddy/{id}")
    public String showBuddyPage(@PathVariable("id") int id, @AuthenticationPrincipal UserDetails user,Model model) throws UnsupportedEncodingException {
        User userInPage = userRepository.findByUsername(user.getUsername());
        if(userInPage.getRole().getName().equals("user")) {
            Buddy buddy = roleOnPage.findRoleBuddyOnPage(userInPage);
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

    @PostMapping("/buddy/{id}")
   public String updateBuddy(@PathVariable int id, @AuthenticationPrincipal UserDetails user, Buddy buddy, BindingResult bindingResult, String formData) {
        Buddy buddyEdit = roleOnPage.findBuddyByUser(user);
        buddyEdit.setFirstName(buddy.getFirstName());
        buddyEdit.setLastName(buddy.getLastName());
        buddyEdit.setAge(buddy.getAge());
        buddyEdit.setAvatarImg(buddy.getAvatarImg());
        buddyEdit.setCity(buddy.getCity());
        buddyRepository.save(buddyEdit);
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

    @PostMapping("/wishlist-delete")
    public String deleteProduct(@AuthenticationPrincipal UserDetails user, Product product){
        Buddy buddy = roleOnPage.findBuddyByUser(user);
        Product product1 = productRepository.getById(product.getProductId());
        System.out.println(product1);
        Set<Product> productBuddy = buddy.getProducts();
        productBuddy.remove(product1);
        buddy.setProducts(productBuddy);
        buddyRepository.save(buddy);

        Set<Buddy> buddyProduct = product1.getBuddies();
        buddyProduct.remove(buddy);
        product1.setBuddies(buddyProduct);
        productRepository.delete(product1);
        return "redirect:/buddy/"+ buddy.getBuddyId() + "/wishlist";
    }


}
