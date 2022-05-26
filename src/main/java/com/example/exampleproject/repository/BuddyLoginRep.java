package com.example.exampleproject.repository;

import com.example.exampleproject.model.BuddyLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuddyLoginRep extends JpaRepository<BuddyLogin, Integer> {

}
