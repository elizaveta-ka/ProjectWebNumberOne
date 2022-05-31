package com.example.exampleproject.repository;

import com.example.exampleproject.model.BusinessReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessReviewRepository extends JpaRepository<BusinessReview, Integer> {
}
