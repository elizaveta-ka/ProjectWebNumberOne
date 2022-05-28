package com.example.exampleproject.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "business_login")
public class BusinessLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int business_id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Transient
    transient private String confirmPassword;
}
