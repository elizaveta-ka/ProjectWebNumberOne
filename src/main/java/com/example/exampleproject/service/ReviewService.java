package com.example.exampleproject.service;

import com.example.exampleproject.model.Business;
import com.example.exampleproject.model.Product;
import com.example.exampleproject.model.Review;
import com.example.exampleproject.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }
}
