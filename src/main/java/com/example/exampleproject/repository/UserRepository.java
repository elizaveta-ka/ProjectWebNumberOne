package com.example.exampleproject.repository;

import com.example.exampleproject.model.Role;
import com.example.exampleproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Collections;

@Repository
public interface UserRepository extends JpaRepository <User, Integer> {
    User findByUsername(String username);

    @Override
    void delete(User entity);
}
