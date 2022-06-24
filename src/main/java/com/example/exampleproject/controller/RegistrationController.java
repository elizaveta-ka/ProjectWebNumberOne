package com.example.exampleproject.controller;

//import com.example.exampleproject.Service.UserService;
import com.example.exampleproject.model.*;
import com.example.exampleproject.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class RegistrationController {


    private final UserRepository userRepository;

    private final BuddyRepository buddyRepository;

    private final BusinessRepository businessRepository;


    private final RoleRepository rolerep;
    @Autowired
    public RegistrationController(UserRepository userRepository, BuddyRepository buddyRepository, BusinessRepository businessRepository, RoleRepository rolerep) {
        this.userRepository = userRepository;
        this.buddyRepository = buddyRepository;
        this.businessRepository = businessRepository;
        this.rolerep = rolerep;
    }


    @GetMapping("/registration")
    public String registration(@AuthenticationPrincipal UserDetails user, Model model) {
        List<User> usersrep = userRepository.findAll();
        List <String> users = new ArrayList<>();
        for (var userdb : usersrep)
            users.add(userdb.getUsername());
        model.addAttribute("users", users);
        if (user != null)
        model.addAttribute("closeButtonAdmin", hideAdmin(user));

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String role, @RequestParam String username,
                              @RequestParam String password, Model model) {

        User newUser = new User(username, password);
        Role usersRole = rolerep.findByName(role).orElseThrow();

        newUser.setRole(usersRole);
        newUser.setActive(true);
        userRepository.save(newUser);
        System.out.println(redirect(newUser));
        return redirect(newUser);
    }

    public String redirect (User user){
        String redirect = "";

        if(user.getRole().getName().equals("USER")) {
            Buddy buddy = new Buddy();
            buddy.setUser(user);
            buddyRepository.save(buddy);
            int id = buddy.getBuddyId();
            redirect = "redirect:/buddy/" + id;

        }

        else if(user.getRole().getName().equals("BUSINESS")) {
            Business business = new Business();
            businessRepository.save(business);
            int id = business.getBusinessId();
            business.setUser(user);
            redirect ="redirect:/business/" + id;
        }
        else if(user.getRole().getName().equals("ADMIN")) {

            redirect ="redirect:/admin" ;
        }
        else {
           redirect = "redirect:/";
        }

        return redirect;
    }
    public String hideAdmin (UserDetails user){
        String closeButtonAdmin = "true";
        if (user != null) {
            User loggedUser = userRepository.findByUsername(user.getUsername());

            if (loggedUser.getRole().getName().equals("ADMIN")) {
                closeButtonAdmin = "false";
            }
            else {
                closeButtonAdmin = "true";
            }
        }
        return closeButtonAdmin;
    }





//    @PostMapping("/registration")
//    public String addUser(@ModelAttribute("userForm") @Validated User userForm, BindingResult bindingResult, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//        if (!userForm.getPassword().equals(userForm.getConfirmPassword())){
//            model.addAttribute("passwordError", "Пароли не совпадают");
//            return "registration";
//        }
//        if (!userService.saveUser(userForm)){
//            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
//            return "registration";
//        }
//
//        return "redirect:/";
//    }
}
