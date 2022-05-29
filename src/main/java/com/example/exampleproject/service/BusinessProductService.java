package com.example.exampleproject.service;

import com.example.exampleproject.model.BusinessLogin;
import com.example.exampleproject.model.BusinessProduct;
import com.example.exampleproject.repository.BusinessProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessProductService {

    private BusinessProductRepository businessProductRepository;

    @Autowired
    public BusinessProductService(BusinessProductRepository businessProductRepository) {
        this.businessProductRepository = businessProductRepository;
    }

    public List<BusinessProduct> findAll() {
        return businessProductRepository.findAll();
    }

    public BusinessProduct saveBusinessProduct(BusinessProduct businessProduct) {
        return businessProductRepository.save(businessProduct);
    }

    public void deleteById(Integer id){
        businessProductRepository.deleteById(id);
    }
}
