package com.example.exampleproject.controller;

import com.example.exampleproject.model.User;
import com.example.exampleproject.repository.RoleRepository;
import com.example.exampleproject.repository.UserRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "login";
    }

    @GetMapping("/admin")
    public String admin( Model model) {
        List <User> users = userRepository.findAll();
        model.addAttribute("users", users);
//        User userFromDB = userRepository.findByUsername(username);
//
//        userFromDB.setActive(Boolean.parseBoolean(ban));
//        userRepository.save(userFromDB);
        return "admin";
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
        List <User> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "login";
    }
//
//    @RequestMapping("/login")
//    public String getLogin(@RequestParam(value = "error", required = false) String error,
//                           @RequestParam(value = "logout", required = false) String logout,
//                           Model model) {
//        model.addAttribute("error", error != null);
//        model.addAttribute("logout", logout != null);
//        return "login";
//    }
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String checkLogin(@RequestParam String username, @RequestParam String password, Model model) {
//             User user = userRepository.findByUsername(username);
//        if (user.getPassword().equals(password))
//        {
//            if (user.getRole().getName().equals("ADMIN"))
//                return "redirect:/admin";
//            if (user.getRole().getName().equals("USER"))
//                return "redirect:/suggestion";
//            if (user.getRole().getName().equals("BUSINESS"))
//                return "redirect:/business-page";
//        }
//        else
//        {
//            model.addAttribute("logError","logError");
//            return "login";
//        }
//        return null;
//    }
//    public String user(Authentication authentication) {
//        System.out.println((UserDetails)authentication.);
//        return "User";
//    }
//    @PostMapping("/login")
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