package com.example.exampleproject.controller;

import com.example.exampleproject.model.Buddy;
import com.example.exampleproject.model.BuddyLogin;
import com.example.exampleproject.model.Wishlist;
import com.example.exampleproject.service.BuddyService;
import com.example.exampleproject.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WishlistController {

    private WishlistService wishlistService;

    private BuddyService buddyService;

    @Autowired
    public WishlistController(WishlistService wishlistService, BuddyService buddyService) {
        this.wishlistService = wishlistService;
        this.buddyService = buddyService;
    }

    @GetMapping("/wishlist-buddy")
    public String findAll(Model model) {
        List<Wishlist> wishlists = wishlistService.findAll();
        List<Buddy> buddies = buddyService.findAll();
        model.addAttribute("wishlists", wishlists);
        model.addAttribute("buddies", buddies);
        return "wishlist";
    }
}
