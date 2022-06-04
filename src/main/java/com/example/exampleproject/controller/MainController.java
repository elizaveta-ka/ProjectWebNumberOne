package com.example.exampleproject.controller;

import com.example.exampleproject.model.User;
import com.example.exampleproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    UserRepository userRepository;
    //Мы могли бы расписать эти 2 маппинга отдельно, но смысла дублировать одинаковый код нет.
    // этот метод будет слушать запросы на "/" и "/index"
    @GetMapping(value = {"/"})
    public String index() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin() {
        return "buddy-list";
    }
//
//    @GetMapping("/user")
//    public String user() {
//        return "/user";
//    }
//
//    @GetMapping("/about")
//    public String about() {
//        return "/about";
//    }
//
    @GetMapping("/login")
public String get(Model model){
        model.addAttribute("title", "форма входа");
        return "login";
    }

//    public String login (String username, String password) {
//        User user = new User (username, password);
//        List <User> users = userRepository.findAll();
//        for (User other : users) {
//            if (other.equals(user)) {
//
//                return "product-list";
//            }
//        }
//
//        return "login";
//    }
//    @PostMapping("/register")
//    public String registerUser( User newUser) {
//        List <User> users = userRepository.findAll();
//
//        System.out.println("New user: " + newUser.toString());
//
//        for (User user : users) {
//            System.out.println("Registered user: " + newUser.toString());
//
//            if (user.equals(newUser)) {
//                System.out.println("User Already exists!");
//                return Status.USER_ALREADY_EXISTS;
//            }
//        }
//
//        userRepository.save(newUser);
//        return Status.SUCCESS;
//    }



}