package com.example.exampleproject.Service;

import com.example.exampleproject.model.*;
import com.example.exampleproject.repository.BuddyRepository;
import com.example.exampleproject.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    //business logic
    public List<Product> createProductRecommendations(List<Product> products, Buddy buddy) {
        //перевод из Set в List
        List<Product> buddyProducts = new ArrayList<>();
        for (var bProduct:buddy.getProducts()) {
            buddyProducts.add(bProduct);
        }
        //исключаем wishlist

        for (int i = 0; i < products.size(); i++) {
            for (int j = 0; j < buddyProducts.size(); j++) {
                if(buddyProducts.get(j).getProductId() == products.get(i).getProductId()) {
                    products.remove(products.get(i));
                }
            }
        }
       // система приоритета

        calculatePriority(products, buddy);
        return products;
    }

    List<Product> calculatePriority(List<Product> products, Buddy buddy) {
        HashMap<Product, Priority> productPriorityHashMap = new HashMap<>();



        return products;
    }
}
