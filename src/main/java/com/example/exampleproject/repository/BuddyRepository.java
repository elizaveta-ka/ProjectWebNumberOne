package com.example.exampleproject.repository;

import com.example.exampleproject.model.Buddy;
import com.example.exampleproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuddyRepository extends JpaRepository<Buddy, Integer> {
}
