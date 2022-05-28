package com.example.exampleproject.service;

import com.example.exampleproject.model.Buddy;
import com.example.exampleproject.model.BuddyLogin;
import com.example.exampleproject.repository.BuddyLoginRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuddyLoginService {

    private final BuddyLoginRep buddyLoginRep;

    @Autowired
    public BuddyLoginService(BuddyLoginRep buddyLoginRep) {
        this.buddyLoginRep = buddyLoginRep;
    }

    public List<BuddyLogin> findAll() {
        return buddyLoginRep.findAll();
    }

    public BuddyLogin saveBuddyLogin(BuddyLogin buddylogin) {
        return buddyLoginRep.save(buddylogin);
    }

    public void deleteById(Integer id){
        buddyLoginRep.deleteById(id);
    }
}
