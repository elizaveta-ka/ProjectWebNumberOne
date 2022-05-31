package com.example.exampleproject.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "business_login")
public class BusinessLogin {

    @OneToOne(mappedBy = "businessLogin", cascade = CascadeType.ALL)
    private Business business;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_id", nullable = false)
    private int business_id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Transient
    transient private String confirmPassword;

    public BusinessLogin() {
    }
    public BusinessLogin(Business business, int business_id, String email, String password, String confirmPassword) {
        this.business = business;
        this.business_id = business_id;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public BusinessLogin(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public int getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
