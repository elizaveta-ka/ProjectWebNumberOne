package com.example.exampleproject.controller;

import com.example.exampleproject.model.Friend;
import com.example.exampleproject.repository.FriendRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FriendController {

    private FriendRepository friendRepository;

    public FriendController(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @GetMapping("/friend")
    public String findAll(Model model) {
        List<Friend> friends = friendRepository.findAll();
        model.addAttribute("friends", friends);
        return "friend";
    }
}
