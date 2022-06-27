package com.example.exampleproject.controller;

import com.example.exampleproject.Service.RoleOnPage;
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

import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
public class BusinessController {

    private final BusinessRepository businessRepository;

    private final ProductRepository productRepository;

    private final BusinessReviewRepository businessReviewRepository;

    private final ProductCategoryRepository productCategoryRepository;

    private final UserRepository userRepository;

    private final BuddyRepository buddyRepository;

    private RoleOnPage roleOnPage;


    @Autowired
    public BusinessController(RoleOnPage roleOnPage, BusinessRepository businessRepository, ProductRepository productRepository, BusinessReviewRepository businessReviewRepository, ProductCategoryRepository productCategoryRepository, UserRepository userRepository, BuddyRepository buddyRepository) {
        this.businessRepository = businessRepository;
        this.productRepository = productRepository;
        this.businessReviewRepository = businessReviewRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.userRepository = userRepository;
        this.buddyRepository = buddyRepository;
        this.roleOnPage = roleOnPage;
    }

    @GetMapping("/business/{id}")
    public String showBusinessPage(@PathVariable("id") int id, @AuthenticationPrincipal UserDetails user, Model model) throws UnsupportedEncodingException {
        User userInPage = userRepository.findByUsername(user.getUsername());
        if(userInPage.getRole().getName().equals("user")) {
            Buddy buddy = roleOnPage.findRoleBuddyOnPage(userInPage);
            model.addAttribute("homeId", buddy.getBuddyId());
        } else if (userInPage.getRole().getName().equals("business")) {
            Business business = roleOnPage.findRoleBusinessOnPage(userInPage);
            model.addAttribute("homeId", business.getBusinessId());
        }
        Business business = businessRepository.getById(id);
        model.addAttribute("user", userInPage);
        model.addAttribute("count", business.getBusinessReviews().size());
        model.addAttribute("business", business);
        return "business-page";
    }

    @PostMapping("/business/{id}")
    public String updateBusiness(@PathVariable int id, @AuthenticationPrincipal UserDetails user, Business business, BindingResult bindingResult) {
        Business business1 = roleOnPage.findBusinessByUser(user);
        System.out.println(business1);
        business1.setBusName(business.getBusName());
        business1.setLocation(business.getLocation());
        business1.setBusinessLink(business.getBusinessLink());
        business1.setBusImg(business.getBusImg());
        businessRepository.save(business1);
        return "redirect:/business/" + id;
    }
    // работает
    @GetMapping("/business/{id}/product-create")
    public String createProductForm(@PathVariable("id")int id, @AuthenticationPrincipal UserDetails user, Model model, Product product) {
        User userInPage = userRepository.findByUsername(user.getUsername());
        if(userInPage.getRole().getName().equals("user")) {
            Buddy buddy = roleOnPage.findRoleBuddyOnPage(userInPage);
            model.addAttribute("homeId", buddy.getBuddyId());
        } else if (userInPage.getRole().getName().equals("business")) {
            Business business = roleOnPage.findRoleBusinessOnPage(userInPage);
            model.addAttribute("homeId", business.getBusinessId());
        }
        Business business = businessRepository.getById(id);
        model.addAttribute("business", business);
        model.addAttribute("user", userInPage);
        model.addAttribute("productCategories", productCategoryRepository.findAll());
        return "product-create";
    }

    @PostMapping("/business/{id}/product-create")
    public String createProduct(@AuthenticationPrincipal UserDetails user, Product product, Business business) {
        Business business1 = roleOnPage.findBusinessByUser(user);
        Set<Product> products = business1.getProducts();
        var productCategory= productCategoryRepository.findById(product.getProductCategory().getCategoryId()).orElseThrow();
        product.setProductCategory(productCategory);
        product.setProductImg(product.getProductImg());
        productRepository.save(product);
        products.add(product);
        business1.setProducts(products);
        businessRepository.save(business1);
        int id = business1.getBusinessId();
        return "redirect:/business/"+ id + "/product-create";
    }
    @PostMapping("/product-delete")
    public String deleteProduct(@AuthenticationPrincipal UserDetails user, Product product){
        Business business1 = roleOnPage.findBusinessByUser(user);
        Product product1 = productRepository.getById(product.getProductId());
        Set<Product> productBusiness = business1.getProducts();
        productBusiness.remove(product1);
        business1.setProducts(productBusiness);
        businessRepository.save(business1);

        Set<Business> businessByProduct = product1.getBusinesses();
        businessByProduct.remove(business1);
        product1.setBusinesses(businessByProduct);
        productRepository.delete(product1);
        return "redirect:/business/"+ business1.getBusinessId() + "/product-create";
    }

    @RequestMapping(value = "/business/{id}/add-review", method = RequestMethod.POST)
    public String addReview(@PathVariable("id") int id, @AuthenticationPrincipal UserDetails user, BusinessReview br) {
        System.out.println(br);
        BusinessReview businessReview = new BusinessReview();
        businessReview.setReviewTitle(br.getReviewTitle());
        businessReview.setReviewBusiness(br.getReviewBusiness());
        businessReview.setRateB1(br.getRateB1());
        Business business = businessRepository.getById(id);
        Buddy buddy = roleOnPage.findBuddyByUser(user);
        businessReview.setBuddy(buddy);
        businessReview.setBuddyId(buddy.getBuddyId());
        businessReview.setBusiness(business);
        businessReview.setBusinessId(business.getBusinessId());
        buddyRepository.saveAndFlush(buddy);
        businessReviewRepository.saveAndFlush(businessReview);
        calculateRating(id);
        return "redirect:/business/" + id;
    }
    public void calculateRating(int id){
        float calcRate = 0;
        Business p = businessRepository.getById(id);
        for (int i = 0; i < p.getBusinessReviews().size(); i++){
            BusinessReview pr = Iterables.get(p.getBusinessReviews(), i) ;
            calcRate+=pr.getRateB1();
        }
        calcRate /=p.getBusinessReviews().size();
        businessRepository.getById(id).setBrRating(calcRate);
        businessRepository.save(businessRepository.getById(id));
    }
}
