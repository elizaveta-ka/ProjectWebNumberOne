package com.example.exampleproject.repository;

import com.example.exampleproject.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Integer> {
//        Optional<Business> getBusinessByUserId(int id);
}
