package com.example.exampleproject.controller;

import com.example.exampleproject.model.Business;
import com.example.exampleproject.model.Product;
import com.example.exampleproject.repository.BusinessRepository;
import com.example.exampleproject.repository.BusinessReviewRepository;
import com.example.exampleproject.repository.ProductCategoryRepository;
import com.example.exampleproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class BusinessController {

    private final BusinessRepository businessRepository;

    private final ProductRepository productRepository;

    private final BusinessReviewRepository businessReviewRepository;

    private final ProductCategoryRepository productCategoryRepository;


    @Autowired
    public BusinessController(BusinessRepository businessRepository, ProductRepository productRepository, BusinessReviewRepository businessReviewRepository, ProductCategoryRepository productCategoryRepository) {
        this.businessRepository = businessRepository;
        this.productRepository = productRepository;
        this.businessReviewRepository = businessReviewRepository;
        this.productCategoryRepository = productCategoryRepository;
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
    //страница бизнеса // работает
    @GetMapping("/business/{id}")
    public String showBusinessPage(@PathVariable("id") int id, Model model) {
        Business business = businessRepository.getById(id);
        model.addAttribute("business", business);
        return "business-page";
    }
    //меню бизнеса // работает
    @GetMapping("/business/{id}/menu")
    public String showBusinessMenu(@PathVariable("id") int id, Model model) {
        Business business = businessRepository.getById(id);
        model.addAttribute("business", business);
        return "business-menu";
    }
    // работает
    @PostMapping("/business-update")
    public String updateBusiness(Business business) {
        Optional<Business> business1 = businessRepository.findById(business.getBusinessId());
        Set<Product> products = business1.get().getProducts();
        for (var product:products) {
            business.addProduct(product);
        }
        businessRepository.save(business);
        return "redirect:/business";
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
}
