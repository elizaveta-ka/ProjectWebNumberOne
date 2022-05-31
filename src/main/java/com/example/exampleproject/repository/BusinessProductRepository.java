package com.example.exampleproject.repository;

import com.example.exampleproject.model.BusinessProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessProductRepository extends JpaRepository<BusinessProduct, Integer> {
}
