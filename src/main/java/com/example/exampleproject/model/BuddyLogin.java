package com.example.exampleproject.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "BuddyLogin")
public class BuddyLogin {
    @OneToOne(mappedBy = "buddyLogin", cascade = CascadeType.ALL)
    private Buddy buddy;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
//    @Transient
//    transient private String confirmPassword;


    public Buddy getBuddy() {
        return buddy;
    }

    public void setBuddy(Buddy buddy) {
        this.buddy = buddy;
    }

    public BuddyLogin() {

    }
    public BuddyLogin(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\nBuddyLogin{" +
                "buddy=" + buddy +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
