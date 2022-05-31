package com.example.exampleproject.service;

import com.example.exampleproject.model.Buddy;
import com.example.exampleproject.repository.BuddyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Service
public class BuddyService {
    private final BuddyRepository buddyRepository;

    @Autowired
    public BuddyService(BuddyRepository buddyRepository) {
        this.buddyRepository = buddyRepository;
    }

    public List<Buddy> findAll() {
    return buddyRepository.findAll();
    }

    public Buddy saveBuddy(Buddy buddy) {
    return buddyRepository.save(buddy);
    }

    public Buddy findById(int id) {
        return buddyRepository.getOne(id);
    }

    public void deleteById(int id) {
        buddyRepository.deleteById(id);
    }
}
