package com.example.exampleproject.controller;


import com.example.exampleproject.Service.BusinessLogicService;
import com.example.exampleproject.Service.RoleOnPage;
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

    private RoleOnPage roleOnPage;

    private BusinessLogicService businessLogicService;


    @Autowired
    public FeedController(BusinessLogicService businessLogicService, RoleOnPage roleOnPage, ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, BuddyRepository buddyRepository, UserRepository userRepository, BusinessRepository businessRepository, RoleRepository roleRepo) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.buddyRepository = buddyRepository;
        this.userRepository = userRepository;
        this.businessRepository = businessRepository;
        this.roleRepo = roleRepo;
        this.roleOnPage = roleOnPage;
        this.businessLogicService = businessLogicService;
    }

    @GetMapping("/feed")
    public String findAll(@AuthenticationPrincipal UserDetails user, Model model) {

        model.addAttribute("hideButtonAdmin", hideAdminButton(user));
        User userInPage = userRepository.findByUsername(user.getUsername());
        if(userInPage.getRole().getName().equals("user")) {
            List<Product> products = productRepository.findAll();
            Buddy buddy = roleOnPage.findRoleBuddyOnPage(userInPage);
            model.addAttribute("homeId", buddy.getBuddyId());
            List<Product> productsRecommendations = businessLogicService.createProductRecommendations(products, buddy);
            if(buddy.getProductAuthors().size() == 0) {
                model.addAttribute("products", products);
            } else {
                model.addAttribute("products", productsRecommendations);
            }
        } else if (userInPage.getRole().getName().equals("business")) {
            List<Product> products = productRepository.findAll();
            Business business = roleOnPage.findRoleBusinessOnPage(userInPage);
            model.addAttribute("homeId", business.getBusinessId());
            model.addAttribute("products", products);
        }
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
//        model.addAttribute("products", products);
        model.addAttribute("user", userInPage);
        model.addAttribute("productCategories", productCategories);
        return "feed";
    }

    @PostMapping("/feed")
    public String addWishlist(@AuthenticationPrincipal UserDetails user, Product product) {

        User user1 =userRepository.findByUsername(user.getUsername());

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
    public String hideAdminButton (UserDetails user){
        String closeButtonAdmin = "true";
        if (user != null) {
            User loggedUser = userRepository.findByUsername(user.getUsername());

            if (loggedUser.getRole().getName().equals("admin")) {
                closeButtonAdmin = "false";
            }
            else {
                closeButtonAdmin = "true";
            }
        }
        return closeButtonAdmin;
    }
}
