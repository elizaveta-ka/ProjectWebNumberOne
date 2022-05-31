package com.example.exampleproject.repository;

import com.example.exampleproject.model.Business;
import com.example.exampleproject.model.BusinessLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessLoginRep extends JpaRepository<BusinessLogin, Integer> {

}
