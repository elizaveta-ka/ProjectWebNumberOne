package com.example.exampleproject.service;

import com.example.exampleproject.model.Business;
import com.example.exampleproject.model.BusinessReview;
import com.example.exampleproject.repository.BusinessReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessReviewService {

    private BusinessReviewRepository businessReviewRepository;

    @Autowired
    public BusinessReviewService(BusinessReviewRepository businessReviewRepository) {
        this.businessReviewRepository = businessReviewRepository;
    }

    public List<BusinessReview> findAll() {
        return businessReviewRepository.findAll();
    }
}
