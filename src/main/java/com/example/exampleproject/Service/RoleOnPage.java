package com.example.exampleproject.Service;

import com.example.exampleproject.model.*;
import com.example.exampleproject.repository.BuddyRepository;
import com.example.exampleproject.repository.BusinessRepository;
import com.example.exampleproject.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleOnPage {

    private final BuddyRepository buddyRepository;

    private final BusinessRepository businessRepository;

    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public RoleOnPage(BuddyRepository buddyRepository, BusinessRepository businessRepository, ProductCategoryRepository productCategoryRepository) {
        this.buddyRepository = buddyRepository;
        this.businessRepository = businessRepository;
        this.productCategoryRepository = productCategoryRepository;
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
