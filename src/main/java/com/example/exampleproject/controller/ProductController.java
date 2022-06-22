package com.example.exampleproject.controller;

import com.example.exampleproject.model.*;
import com.example.exampleproject.repository.*;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String showProductPage(@PathVariable("id") int id, Model model) {
        Product product = productRepository.getById(id);
        model.addAttribute("product", product);
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
        productReview.setRateP1(pr.getRateP1());
        Product product = productRepository.getById(id);
        User user1 = userRepository.findByUsername(user.getUsername());
        List<Buddy> buddies = buddyRepository.findAll();
        int bId = 0;
        for (var b:buddies) {
            User user2 = b.getUser();
            if(user1.equals(user2)) {
                bId = b.getBuddyId();
            }
        }
        Buddy buddy = buddyRepository.getById(bId);
        productReview.setBuddyId(buddy.getBuddyId());
        Collection<ProductReview> productReviews = buddy.getProductAuthors();
        productReviews.add(productReview);
        productReview.setBuddy(buddy);
        productReview.setProduct(product);
        productReview.setProductId(product.getProductId());
        buddyRepository.saveAndFlush(buddy);
        productReviewRepository.saveAndFlush(productReview);
        calculateRating(id);
        return "redirect:/product/" + id;
    }
//    @GetMapping("/product/{id}")
//    public String showUpdateForm(@PathVariable("id") int id, Model model) {
//        ProductReview pr = productReviewRepository.findById(id).get();
//        model.addAttribute("productReview", pr);
//        return "redirect:/product" + id;
//    }
    @RequestMapping(value = "/product/{id}/edit-review", method = RequestMethod.POST)
    public String editReview(@PathVariable("id") int id, Model model, BindingResult result, @Valid ProductReview pr) {
        productReviewRepository.save(pr);
        return "redirect:/product" + id;
    }

    public void calculateRating(int id){
        float calcRate = 0;
        Product p = productRepository.getById(id);
        for (int i = 0; i<p.getProductReviews().size(); i++){
            ProductReview pr = Iterables.get(p.getProductReviews(), i) ;
            calcRate+=pr.getRateP1();
        }
        calcRate /=p.getProductReviews().size();
        productRepository.getById(id).setPrRating(calcRate);
        productRepository.save(productRepository.getById(id));
    }
}
