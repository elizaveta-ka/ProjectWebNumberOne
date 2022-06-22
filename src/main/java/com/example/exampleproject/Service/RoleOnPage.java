package com.example.exampleproject.Service;

import com.example.exampleproject.model.Buddy;
import com.example.exampleproject.model.Business;
import com.example.exampleproject.model.User;
import com.example.exampleproject.repository.BuddyRepository;
import com.example.exampleproject.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleOnPage {

    private final BuddyRepository buddyRepository;

    private final BusinessRepository businessRepository;

    @Autowired
    public RoleOnPage(BuddyRepository buddyRepository, BusinessRepository businessRepository) {
        this.buddyRepository = buddyRepository;
        this.businessRepository = businessRepository;
    }

    public Buddy findRoleBuddyOnPage(User user) {
        int bId = 0;
        Collection<Buddy> buddies = buddyRepository.findAll();
        for (var b : buddies) {
            User user2 = b.getUser();
            if (user.equals(user2)) {
                bId = b.getBuddyId();
            }
        }
        Buddy buddy = buddyRepository.getById(bId);
        return buddy;
    }

    public Business findRoleBusinessOnPage(User user) {
        int buId = 0;
        Collection<Business> businesses = businessRepository.findAll();
        for (var b : businesses) {
            User user2 = b.getUser();
            if (user.equals(user2)) {
                buId = b.getBusinessId();
            }
        }
        Business business = businessRepository.getById(buId);
        return business;
    }
}
