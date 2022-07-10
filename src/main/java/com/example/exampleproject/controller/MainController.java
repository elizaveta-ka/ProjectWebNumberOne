package com.example.exampleproject.controller;

import com.example.exampleproject.model.User;
import com.example.exampleproject.repository.RoleRepository;
import com.example.exampleproject.repository.UserRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository rolrep;

    public MainController(UserRepository userRepository, RoleRepository rolrep) {
        this.userRepository = userRepository;
        this.rolrep = rolrep;
    }


    @GetMapping(value = {"/"})
    public String index() {
        System.out.println(getClass().getClassLoader().getResource("logging.properties"));

        return "login";
    }

    @GetMapping("/admin")
    public String admin(Model model) {

        List <User> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "admin";
    }
    @GetMapping("/login")
    public String get(Model model){
        List <User> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "login";
    }
    @GetMapping("/ban-user/{id}")
    public String banGet(@PathVariable("id") int id, Model model) {
        User userToBan = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        if (userToBan.isActive()) {
            userToBan.setActive(false);
        }
        else if (!userToBan.isActive()){
            userToBan.setActive(true);
        }

        userRepository.save(userToBan);
        return "redirect:/admin";
    }


}