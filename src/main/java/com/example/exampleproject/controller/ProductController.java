package com.example.exampleproject.controller;

import com.example.exampleproject.model.Product;
import com.example.exampleproject.model.ProductCategory;
import com.example.exampleproject.model.ProductReview;
import com.example.exampleproject.model.User;
import com.example.exampleproject.repository.ProductCategoryRepository;
import com.example.exampleproject.repository.ProductRepository;
import com.example.exampleproject.repository.ProductReviewRepository;
import com.example.exampleproject.repository.UserRepository;
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

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {
    private final ProductRepository productRepository;

    private final ProductReviewRepository productReviewRepository;

    private final ProductCategoryRepository productCategoryRepository;
    private final UserRepository userRepository;



    @Autowired
    public ProductController(ProductRepository productRepository, ProductReviewRepository productReviewRepository, ProductCategoryRepository productCategoryRepository, com.example.exampleproject.repository.UserRepository userRepository) {
        this.productRepository = productRepository;
        this.productReviewRepository = productReviewRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.userRepository = userRepository;
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
    public String addReview(@PathVariable("id") @AuthenticationPrincipal UserDetails user, int id, ProductReview pr) {
//        User user1 = userRepository.findByUsername(user.getUsername());
//        System.out.println(user1);
        ProductReview productReview = new ProductReview();
        productReview.setReviewTitle(pr.getReviewTitle());
        productReviewRepository.save(pr);
        System.out.println(pr);
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
}
