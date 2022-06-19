package com.example.exampleproject.controller;

//import com.example.exampleproject.Service.UserService;
import com.example.exampleproject.model.*;
import com.example.exampleproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @GetMapping("/registration")
//    public String registration(Model model) {
//        List<User> users = userRepository.findAll();
//        model.addAttribute("users", users);
//        return "registration";
//    }


    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String Roleee, @RequestParam String username,
                              @RequestParam String password, User user, Model model) {
        User userFromDB = userRepository.findByUsername(username);
        List<User> usersRep = userRepository.findAll();
//        List <String> users = new ArrayList<>();
//        for (var user : usersRep)
//            users.add(user.getUsername());

        if (userFromDB != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        User newUser = new User(username, password);

        System.out.println(user);
        Role role = rolerep.findByName(Roleee).orElseThrow();

        newUser.setRole(role);
        newUser.setActive(true);
        userRepository.save(newUser);
        System.out.println(newUser);


        if(newUser.getRole().getName().equals("user")) {
            Buddy buddy = new Buddy();
            buddy.setUser(newUser);
            buddyRepository.save(buddy);
            int id = buddy.getBuddyId();
            return "redirect:/buddy/" + id;
        }

        if(newUser.getRole().getName().equals("business")) {
            Business business = new Business();
            businessRepository.save(business);
            int id = business.getBusinessId();
            business.setUser(newUser);
        return "redirect:/business/" + id;
        }

        return "redirect:/";
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
