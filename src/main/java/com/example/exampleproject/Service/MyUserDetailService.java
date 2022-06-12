//package com.example.exampleproject.Service;
//
//import com.example.exampleproject.model.User;
//import com.example.exampleproject.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MyUserDetailService implements UserDetailsService {
//    @Autowired
//    private UserRepository dao;
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        User myUser= dao.findByUsername(userName);
//        if (myUser == null) {
//            throw new UsernameNotFoundException("Unknown user: "+userName);
//        }
//        UserDetails user = org.springframework.security.core.userdetails.User.builder()
//                .username(myUser.getUsername())
//                .password(myUser.getPassword())
//                .roles(myUser.getRole().getName())
//                .build();
//        return user;
//    }
//}