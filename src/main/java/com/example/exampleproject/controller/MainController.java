package com.example.exampleproject.controller;

import com.example.exampleproject.model.User;
import com.example.exampleproject.repository.RoleRepository;
import com.example.exampleproject.repository.UserRepository;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.InetAddress;
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
    public String admin( Model model) throws IOException, GeoIp2Exception {



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
//    @PostMapping("/admin")
//    public String addUser(@RequestParam String username, @RequestParam String active, Model model) {
//        List <User> users = userRepository.findAll();
//        model.addAttribute("users", users);
//        User user = userRepository.findByUsername(username);
//        user.setActive(Boolean.parseBoolean(active));
//        userRepository.save(user);
//        return "admin";
//    }
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
//
//    @RequestMapping("/login")
//    public String getLogin(@RequestParam(value = "error", required = false) String error,
//                           @RequestParam(value = "logout", required = false) String logout,
//                           Model model) {
//        model.addAttribute("error", error != null);
//        model.addAttribute("logout", logout != null);
//        return "login";
//    }
//        @GetMapping("/login")
//    public String userForm(Model model) {
//
//        return "login";
//    }
//    @PostMapping("/login")
//    public String checkLogin(@RequestParam String username, @RequestParam String password, Model model) {
//             User user = new User(username,password);
//        System.out.println(user);
//
//       return "redirect:/feed";
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
@GetMapping("/xxx")
public String getxxx(Model model){
    List <User> users = userRepository.findAll();
    model.addAttribute("users", users);

    return "xxx";
}


}