package com.example.exampleproject.controller;

import com.example.exampleproject.model.*;
import com.example.exampleproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.List;

@Controller
public class ProductController {
    private final ProductRepository productRepository;

    private final ProductReviewRepository productReviewRepository;

    private final ProductCategoryRepository productCategoryRepository;
    private final UserRepository userRepository;
    private final BuddyRepository buddyRepository;



    @Autowired
    public ProductController(ProductRepository productRepository, ProductReviewRepository productReviewRepository, ProductCategoryRepository productCategoryRepository, UserRepository userRepository, BuddyRepository buddyRepository) {
        this.productRepository = productRepository;
        this.productReviewRepository = productReviewRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.userRepository = userRepository;
        this.buddyRepository = buddyRepository;
    }
    @GetMapping("/products")
    public String findAll(Model model) {
        List<Product> products = productRepository.findAll();
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("productCategories", productCategories);
        return "product-list";
    }

    @GetMapping("/productsAdd")
    public String productAdd(Model model) {
        List<Product> products = productRepository.findAll();
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("productCategories", productCategories);
        return "product-list";
    }
    //страница продукта
    @GetMapping("/product/{id}")
    public String showProductPage(@AuthenticationPrincipal UserDetails user, @PathVariable("id") int id, Model model) {
        User user1 = userRepository.findByUsername(user.getUsername());
        int bId = 0;
        Collection<Buddy> buddies = buddyRepository.findAll();
        for (var b:buddies) {
            User user2 = b.getUser();
            if(user1.equals(user2)) {
                bId = b.getBuddyId();
            }
        }
        Buddy buddy1 = buddyRepository.getById(bId);
        Product product = productRepository.getById(id);
        model.addAttribute("product", product);
        model.addAttribute("buddy", buddy1);
        return "product";
    }

    @GetMapping("/product/{id}/suggestion")
    public String showProductSuggestionPage(@PathVariable("id") int id, Model model) {
        Product product = productRepository.getById(id);
        model.addAttribute("product", product);
        return "suggestion-page";
    }

    @GetMapping("/fragments")
    public String getHome(){
        return "main-fragments.html";
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.POST)
    public String addReview(@PathVariable("id") int id, @AuthenticationPrincipal UserDetails user, ProductReview pr) {
        ProductReview productReview = new ProductReview();
        productReview.setReviewTitle(pr.getReviewTitle());
        productReview.setReviewProduct(pr.getReviewProduct());
        productReview.setRateP4(pr.getRateP4());

        Product product = productRepository.getById(id);

        User user1 = userRepository.findByUsername(user.getUsername());
        System.out.println(user1);
        List<Buddy> buddies = buddyRepository.findAll();
        int bId = 0;
        for (var b:buddies) {
            User user2 = b.getUser();
            if(user1.equals(user2)) {
                bId = b.getBuddyId();
            }
        }
        System.out.println(productReview);
        Buddy buddy = buddyRepository.getById(bId);
        productReview.setBuddyId(buddy.getBuddyId());
        Collection<ProductReview> productReviews = buddy.getProductAuthors();
        productReviews.add(productReview);
        productReview.setBuddy(buddy);
        productReview.setProduct(product);
        productReview.setProductId(product.getProductId());

        System.out.println(productReview);
        buddyRepository.saveAndFlush(buddy);

        System.out.println(productReview);
        productReviewRepository.saveAndFlush(productReview);
        System.out.println(pr);
        return "redirect:/product/" + id;
    }
    @RequestMapping(value = "/product/{id}/edit-review", method = RequestMethod.POST)
    public String editReview(@PathVariable("id") int id, Model model, BindingResult result, ProductReview pr) {
        productReviewRepository.save(pr);
        return "redirect:/product" + id;
    }
}


