package com.example.exampleproject.controller;

import com.example.exampleproject.Service.RoleOnPage;
import com.example.exampleproject.model.*;
import com.example.exampleproject.repository.BuddyRepository;
import com.example.exampleproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Collection;
import java.util.Set;

@Controller
public class FriendController {

    private BuddyRepository buddyRepository;

    private UserRepository userRepository;

    private RoleOnPage roleOnPage;
    @Autowired
    public FriendController(RoleOnPage roleOnPage, BuddyRepository buddyRepository, UserRepository userRepository) {
        this.buddyRepository = buddyRepository;
        this.userRepository = userRepository;
        this.roleOnPage = roleOnPage;
    }

    @GetMapping("/friend")
    public String findAll(@AuthenticationPrincipal UserDetails user, Model model) {
        User userInPage = userRepository.findByUsername(user.getUsername());
        System.out.println(userInPage);
        Buddy homeB = null;
        if(userInPage.getRole().getName().equals("user")) {
            Buddy buddy = roleOnPage.findRoleBuddyOnPage(userInPage);
            model.addAttribute("homeId", buddy.getBuddyId());
        } else if (userInPage.getRole().getName().equals("business")) {
            Business business = roleOnPage.findRoleBusinessOnPage(userInPage);
            model.addAttribute("homeId", business.getBusinessId());
        }
        model.addAttribute("user", userInPage);
        model.addAttribute("buddies", buddyRepository.findAll());
        return "friend";
    }

    @PostMapping("/friend")
    public String addFriend(@AuthenticationPrincipal UserDetails user, @RequestParam int idF) {
        System.out.println(idF);
        Buddy buddyOnline = roleOnPage.findBuddyByUser(user);
        Buddy myFriend = buddyRepository.getById(idF);

        Set<Buddy> buddySubscribers = buddyOnline.getSubscribers();
        buddySubscribers.add(myFriend);
        buddyOnline.setSubscribers(buddyOnline.getSubscribers());
        buddyRepository.save(buddyOnline);
        Set<Buddy> buddySubscriptions = myFriend.getSubscriptions();
        buddySubscriptions.add(buddyOnline);
        myFriend.setSubscriptions(buddySubscriptions);
        buddyRepository.save(myFriend);
        return "redirect:/friend";
    }
}
