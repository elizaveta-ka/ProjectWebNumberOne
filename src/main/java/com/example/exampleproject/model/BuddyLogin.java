//package com.example.exampleproject.model;
//
//import lombok.Data;
//
//import javax.persistence.*;
//
//@Data
//@Entity
//@Table(name = "BuddyLogin")
//public class BuddyLogin {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long buddy_id;
//    @Column(name = "email")
//    private String email;
//    @Column(name = "password")
//    private String password;
//    @Transient
//    transient private String confirmPassword;
//
//    public long getBuddy_id() {
//        return buddy_id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public String getConfirmPassword() {
//        return confirmPassword;
//    }
//
//    public void setBuddy_id(long buddy_id) {
//        this.buddy_id = buddy_id;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setConfirmPassword(String confirmPassword) {
//        this.confirmPassword = confirmPassword;
//    }
//}
