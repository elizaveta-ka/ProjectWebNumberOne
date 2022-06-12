package com.example.exampleproject.repository;

import com.example.exampleproject.model.Role;
import com.example.exampleproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository <Role, Integer> {
    Optional<Role> findByName(String name);
}
