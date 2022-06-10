package com.example.exampleproject;

import com.example.exampleproject.repository.BuddyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ExampleProjectApplication {

    public static void main(String[] args) {

        SpringApplication.run(ExampleProjectApplication.class, args);

    }
}