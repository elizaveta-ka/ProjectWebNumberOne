package com.example.exampleproject.controller;

import com.example.exampleproject.model.Buddy;
import com.example.exampleproject.model.Wishlist;
import com.example.exampleproject.repository.BuddyRepository;
import com.example.exampleproject.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WishlistController {

    private WishlistRepository wishlistRepository;

    private BuddyRepository buddyRepository;

    @Autowired
    public WishlistController(WishlistRepository wishlistRepository, BuddyRepository buddyRepository) {
        this.wishlistRepository = wishlistRepository;
        this.buddyRepository = buddyRepository;
    }

    @GetMapping("/wishlist-buddy")
    public String findAll(Model model) {
        List<Wishlist> wishlists = wishlistRepository.findAll();
        List<Buddy> buddies = buddyRepository.findAll();
        model.addAttribute("wishlists", wishlists);
        model.addAttribute("buddies", buddies);
        return "wishlist";
    }
}
