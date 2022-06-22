package com.example.exampleproject;

import com.example.exampleproject.model.Product;
import com.example.exampleproject.repository.BuddyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication

public class ExampleProjectApplication {

    public static void main(String[] args) {

        SpringApplication.run(ExampleProjectApplication.class, args);


    }
}