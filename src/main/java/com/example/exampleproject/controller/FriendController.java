package com.example.exampleproject.controller;

import com.example.exampleproject.model.Buddy;
import com.example.exampleproject.model.Friend;
import com.example.exampleproject.model.Wishlist;
import com.example.exampleproject.service.FriendService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FriendController {

    private FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping("/friend")
    public String findAll(Model model) {
        List<Friend> friends = friendService.findAll();
        model.addAttribute("friends", friends);
        return "friend";
    }
}
