package com.example.exampleproject.controller;
import com.example.exampleproject.model.*;
import com.example.exampleproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
        String hideAdminFlag = "true";
        if (user != null)
           hideAdminFlag = hideAdmin(user);
            model.addAttribute("closeButtonAdmin", hideAdminFlag);

        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(@RequestParam String role, @RequestParam String username,
                              @RequestParam String password, @RequestParam String age) {


        User newUser = new User(username, password);

        Role newRole = rolerep.findByName(role).orElseThrow();
        System.out.println(newRole);
        newUser.setRole(newRole);

        newUser.setActive(true);
        userRepository.save(newUser);

        if(newUser.getRole().getName().equals("user")) {

            return makeRedirectBuddyAfterRegistration(newUser, Integer.parseInt(age));
        }
        else if (newUser.getRole().getName().equals("business")) {
          return makeRedirectBusinessAfterRegistration(newUser);
        }
        else if (newUser.getRole().getName().equals("admin")){
            return makeRedirectAdminAfterRegistration();
        }
            return "redirect:/feed";
    }

    public String makeRedirectBuddyAfterRegistration(User user, int age) {
        String page = null;
            Buddy buddy = new Buddy();
            buddy.setUser(user);
            buddy.setAge(age);
            buddyRepository.save(buddy);
            int id = buddy.getBuddyId();
            page = "redirect:/buddy/" + id;
            return page;
    }

    public String makeRedirectBusinessAfterRegistration(User user) {

        String page = null;
            Business business = new Business();
            business.setUser(user);
            businessRepository.save(business);
            int id = business.getBusinessId();
            page = "redirect:/business/"+ id;
            return page;
    }
    public String makeRedirectAdminAfterRegistration() {

        return "redirect:/admin";
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
}
