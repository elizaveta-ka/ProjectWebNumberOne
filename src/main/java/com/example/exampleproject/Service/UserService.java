//package com.example.exampleproject.Service;
//
//
//import com.example.exampleproject.model.Role;
//import com.example.exampleproject.model.User;
//import com.example.exampleproject.repository.RoleRepository;
//import com.example.exampleproject.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserService implements UserDetailsService{
//
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    RoleRepository roleRepository;
//
//
//    public boolean saveUser(User user) {
////        User userFromDB = userRepository.findByUsername(user.getUsername());
////
////        if (userFromDB != null) {
////            return false;
////        }
////        user.setRoles(Collections.singleton(new Role(2, "ROLE_USER")));
////        user.setPassword(user.getPassword());
////        userRepository.save(user);
//        return true;
//
//    }
//
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        List <User> users = userRepository.findAll();
//
//        for (User other : users) {
//            if (other.getUsername().equals(s)) {
//
//                return (UserDetails) other;
//            }
//        }
//
//        return null;
//    }
//}
