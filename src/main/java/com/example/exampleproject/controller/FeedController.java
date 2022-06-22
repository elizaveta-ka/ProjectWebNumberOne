package com.example.exampleproject.controller;


import com.example.exampleproject.model.*;
import com.example.exampleproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Controller
public class FeedController {

    private ProductRepository productRepository;
    private ProductCategoryRepository productCategoryRepository;
    private BuddyRepository buddyRepository;

    private final UserRepository userRepository;

    private BusinessRepository businessRepository;

    private RoleRepository roleRepo;

//    private BusinessLogicService businessLogicService;

    @Autowired
    public FeedController(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, BuddyRepository buddyRepository, UserRepository userRepository, BusinessRepository businessRepository, RoleRepository roleRepo) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.buddyRepository = buddyRepository;
        this.userRepository = userRepository;
        this.businessRepository = businessRepository;
        this.roleRepo = roleRepo;
//        this.businessLogicService = businessLogicService;
    }
            //уменьшить метод
    @GetMapping("/feed")
    public String findAll(@AuthenticationPrincipal UserDetails user, Model model) {
        System.out.println(user);
        User user1 = userRepository.findByUsername(user.getUsername());
        System.out.println(user1);
        Buddy homeB = null;
        if(user1.getRole().getName().equals("user")) {
            int bId = 0;
            Collection<Buddy> buddies = buddyRepository.findAll();
            for (var b : buddies) {
                User user2 = b.getUser();
                if (user1.equals(user2)) {
                    bId = b.getBuddyId();
                }
            }
            homeB = buddyRepository.getById(bId);
            model.addAttribute("home", "buddy");
            model.addAttribute("homeId", homeB.getBuddyId());
        } else if (user1.getRole().getName().equals("business")) {
            int buId = 0;
            Collection<Business> businesses = businessRepository.findAll();
            for (var b : businesses) {
                User user2 = b.getUser();
                if (user1.equals(user2)) {
                    buId = b.getBusinessId();
                }
            }
            Business homeBu = businessRepository.getById(buId);
            model.addAttribute("homeId", homeBu.getBusinessId());
        }

        List<Product> products = productRepository.findAll(); //приходит из сервиса отсортированный по рекомендациям список продуктов

        List<ProductCategory> productCategories = productCategoryRepository.findAll();

        model.addAttribute("products", products);
        model.addAttribute("user", user1);
        model.addAttribute("productCategories", productCategories);
        return "feed";
    }



    @PostMapping("/feed")
    public String addWishlist(@AuthenticationPrincipal UserDetails user, Product product) {
        System.out.println(user);
        User user1 =userRepository.findByUsername(user.getUsername());
        System.out.println(user1);
        int bId = 0;

        Product product1 = productRepository.getById(product.getProductId());

        List<Buddy> buddies = buddyRepository.findAll();
        for (var b:buddies) {
            User user2 = b.getUser();
            if(user1.equals(user2)) {
                bId = b.getBuddyId();
            }
        }
        Buddy buddy = buddyRepository.getById(bId);
        Set<Product> products = buddy.getProducts();
        products.add(product1);
        buddy.setProducts(products);
        buddyRepository.save(buddy);

        Set<Buddy> buddiesList = product1.getBuddies();
        buddiesList.add(buddy);
        product1.setBuddies(buddiesList);
        productRepository.save(product1);
        return "redirect:/feed";
    }


    @GetMapping("/notifications")
    public String notificationPage() {

        return "notifications";
    }

    //бизнес логика
  


//    public double createSimilarityCoefficient() {
//        double coefficient = 0;
//        return coefficient;
//    }
//
//
//
//
//
//
//
//    public List<Product> makeRec(Buddy buddy) {
//        List<Product> bestProducts = null;
//        System.out.println(buddy);
//        return bestProducts;
//    }
}
