package com.example.exampleproject.service;

import com.example.exampleproject.model.BuddyLogin;
import com.example.exampleproject.model.BusinessLogin;
import com.example.exampleproject.repository.BuddyLoginRep;
import com.example.exampleproject.repository.BusinessLoginRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessLoginService {

    private final BusinessLoginRep businessLoginRep;

    @Autowired
    public BusinessLoginService(BusinessLoginRep businessLoginRep) {
        this.businessLoginRep = businessLoginRep;
    }

    public List<BusinessLogin> findAll() {
        return businessLoginRep.findAll();
    }

    public BusinessLogin saveBusinessLogin(BusinessLogin businesslogin) {
        return businessLoginRep.save(businesslogin);
    }

    public void deleteById(Integer id){
        businessLoginRep.deleteById(id);
    }
}
