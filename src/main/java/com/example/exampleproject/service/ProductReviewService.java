package com.example.exampleproject.service;

import com.example.exampleproject.model.Product;
import com.example.exampleproject.model.ProductReview;
import com.example.exampleproject.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductReviewService {

    private ProductReviewRepository productReviewRepository;

    @Autowired
    public ProductReviewService(ProductReviewRepository productReviewRepository) {
        this.productReviewRepository = productReviewRepository;
    }

    public List<ProductReview> findAllProducts() {
        return productReviewRepository.findAll();
    }
}
