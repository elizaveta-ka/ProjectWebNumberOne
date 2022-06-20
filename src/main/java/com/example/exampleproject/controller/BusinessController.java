package com.example.exampleproject.controller;

import com.example.exampleproject.model.*;
import com.example.exampleproject.repository.*;
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


    @Autowired
    public BusinessController(BusinessRepository businessRepository, ProductRepository productRepository, BusinessReviewRepository businessReviewRepository, ProductCategoryRepository productCategoryRepository, UserRepository userRepository, BuddyRepository buddyRepository) {
        this.businessRepository = businessRepository;
        this.productRepository = productRepository;
        this.businessReviewRepository = businessReviewRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.userRepository = userRepository;
        this.buddyRepository = buddyRepository;
    }
    // работает для демонстрации связей
    @GetMapping("/business")
    public String findAll(Model model) {
        List<Business> businesses = businessRepository.findAll();
        System.out.println(businesses);
        model.addAttribute("businesses", businesses);
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "business-list";
    }


    @GetMapping("/business-create")
    public String createBusinessForm(Business business) {
        return "business-create";
    }

    @PostMapping("/business-create")
    public String createBusiness(Business business) {
        businessRepository.save(business);
        return "redirect:/business";
    }
    @GetMapping("/business-delete/{id}")
    public String deleteBusiness(@PathVariable(value = "id") int id) {

        businessRepository.deleteById(id);
        return "redirect:/business";
    }
    // работает
    @GetMapping("/business-update/{id}")
    public String updateBusinessForm(@PathVariable("id") int id, Model model) {
        Optional<Business> business = businessRepository.findById(id);
        model.addAttribute("business", business);
        return "/business-update";
    }

    @GetMapping("/business/{id}")
    public String showBusinessPage(@PathVariable("id") int id, Model model) throws UnsupportedEncodingException {
        Business business = businessRepository.getById(id);
        model.addAttribute("business", business);
//        byte[] encodeBase64 = Base64.encode(business.getImg());
//        String base64Encoded = new String(encodeBase64, "UTF-8");
//        model.addAttribute("image", base64Encoded );
        return "business-page";
    }

    @PostMapping("/business/{id}")
    public String updateBusiness(@PathVariable int id, Business business, BindingResult bindingResult) {
        Business business1 = businessRepository.getById(id);
        business1.setBusName(business.getBusName());
        business1.setLocation(business.getLocation());
        business1.setBusinessLink(business.getBusinessLink());
        businessRepository.save(business1);
        return "redirect:/business/" + id;
    }

//    @PostMapping("/link")
//    public String goToLink(@PathVariable int id, Business business, BindingResult bindingResult) {
//        Business business1 = businessRepository.getById(id);
//        var link = business.getBusinessLink();
//        System.out.println(link);
//        return "redirect:" + link;
//    }

    //меню бизнеса // работает
    @GetMapping("/business/{id}/menu")
    public String showBusinessMenu(@PathVariable("id") int id, Model model) {
        Business business = businessRepository.getById(id);
        model.addAttribute("business", business);
        return "business-menu";
    }
    // работает
    @GetMapping("/business/{id}/product-create")
    public String createProductForm(@PathVariable("id")int id, Model model, Product product) {
        Business business = businessRepository.getById(id);
        model.addAttribute("business", business);
        model.addAttribute("productCategories", productCategoryRepository.findAll());
        return "product-create";
    }

    @PostMapping("/business/{id}/product-create")
    public String createProduct(Product product, Business business) {
        Business business1 = businessRepository.getById(business.getBusinessId());
        Set<Product> products = business1.getProducts();
        var productCategory= productCategoryRepository.findById(product.getProductCategory().getCategoryId()).orElseThrow();
        product.setProductCategory(productCategory);
        productRepository.save(product);
        products.add(product);
        business1.setProducts(products);
        businessRepository.save(business1);
        int id = business1.getBusinessId();
        return "redirect:/business/"+ id;
    }

    @RequestMapping(value = "/business/{id}/add-review", method = RequestMethod.POST)
    public String addReview(@PathVariable("id") int id, @AuthenticationPrincipal UserDetails user, BusinessReview br) {
        BusinessReview businessReview = new BusinessReview();
        businessReview.setReviewTitle(br.getReviewTitle());
        businessReview.setReviewBusiness(br.getReviewBusiness());
        businessReview.setRateB1(br.getRateB1());
        Business business = businessRepository.getById(id);
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
        Buddy buddy = buddyRepository.getById(bId);
        businessReview.setBuddy(buddy);
//        Collection<BusinessReview> businessReviews = buddy.getProductAuthors();
//        businessReviews.add(productReview);
        businessReview.setBuddy(buddy);
        businessReview.setBusiness(business);
        businessReview.setBusinessId(business.getBusinessId());
        buddyRepository.saveAndFlush(buddy);
        System.out.println(businessReview);
        businessReviewRepository.saveAndFlush(businessReview);
        System.out.println(br);
        return "redirect:/business/" + id;
    }
}
