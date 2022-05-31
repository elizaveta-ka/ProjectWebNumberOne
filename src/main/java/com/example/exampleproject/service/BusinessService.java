package com.example.exampleproject.service;

import com.example.exampleproject.model.Buddy;
import com.example.exampleproject.model.Business;
import com.example.exampleproject.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

    private BusinessRepository businessRepository;

    @Autowired
    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    public List<Business> findAll() {
        return businessRepository.findAll();
    }

    public Business saveBusiness(Business business) {

        return businessRepository.save(business);
    }

    public Business findById(int id) {
        return businessRepository.getOne(id);
    }
}
