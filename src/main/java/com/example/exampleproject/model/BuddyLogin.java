package com.example.exampleproject.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "BuddyLogin")
public class BuddyLogin {
    @OneToOne(mappedBy = "buddyLogin", cascade = CascadeType.ALL)
    private Buddy buddy;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buddy_id", nullable = false)
    private int buddyId;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @Transient
    transient private String confirmPassword;

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

    public int getBuddyId() {
        return buddyId;
    }

    public void setId(int buddyId) {
        this.buddyId = buddyId;
    }

    @Override
    public String toString() {
        return "\nBuddyLogin{" +
//                "buddy=" + buddy +
                ", buddy_id=" + buddyId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
