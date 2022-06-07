package com.example.exampleproject.controller;

//import com.example.exampleproject.Service.UserService;
import com.example.exampleproject.model.Role;
import com.example.exampleproject.model.User;
import com.example.exampleproject.repository.RoleRepository;
import com.example.exampleproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository rolerep;

    public RegistrationController(UserRepository userRepository, RoleRepository rolerep) {
        this.userRepository = userRepository;
        this.rolerep = rolerep;
    }

//    @GetMapping("/registration")
//    public String registration(Model model) {
//        List<User> users = userRepository.findAll();
//        model.addAttribute("users", users);
//        return "registration";
//    }

    //From D
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addEmployee(User user, Model model) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        User newuser = new User(user.getUsername(), user.getPassword());

        Role role = rolerep.findByName("USER").orElseThrow();

        newuser.setRole(role);
//        rolelist.add(role);
        newuser.setActive(true);
//        employee.setRoles(rolelist);
        userRepository.save(newuser);

        return "redirect:/login";
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
