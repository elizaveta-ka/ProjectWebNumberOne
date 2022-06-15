package com.example.exampleproject.controller;

import com.example.exampleproject.model.Product;
import com.example.exampleproject.model.ProductReview;
import com.example.exampleproject.repository.ProductRepository;
import com.example.exampleproject.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductReviewController {

    private ProductReviewRepository productReviewRepository;

    private final ProductRepository productRepository;

    @Autowired
    public ProductReviewController(ProductReviewRepository productReviewRepository, ProductRepository productRepository) {
        this.productReviewRepository = productReviewRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/productReview")
    public String findAll(Model model) {
        List<ProductReview> productReviews = productReviewRepository.findAll();
        model.addAttribute("productReviews", productReviews);
        return "product-review";
    }
//    @PostMapping(value = "/addReview", produces = {MediaType.APPLICATION_JSON_VALUE})
//    @ResponseBody
//    public ProductReviewResponse saveReview(@ModelAttribute @Valid ProductReview productReview,
//                                            BindingResult result) {
//
//        ProductReviewResponse response = new ProductReviewResponse();
//
//        if (result.hasErrors()) {
//
//            Map<String, String> errors = result.getFieldErrors().stream()
//                    .collect(
//                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
//                    );
//
//            response.setValidated(false);
//            response.setErrorMessages(errors);
//        } else {
//
//            response.setValidated(true);
//        }
//        return response;
//    }

    @GetMapping("/product/{id}/add-review")
    public String showReview(@PathVariable("id") int id, Model model) {
        Product product = productRepository.getReferenceById(id);
        model.addAttribute("product", product);
        return "add-review";
    }


    @RequestMapping(value = "/product/{id}/add-review", method = RequestMethod.POST)
    public String addReview(@PathVariable("id") int id, ProductReview productReview, BindingResult bindingResult, Model model) {
        model.addAttribute("productId", productReview.getProductId());
        model.addAttribute("reviewTitle", productReview.getReviewTitle());
        model.addAttribute("reviewProduct", productReview.getReviewProduct());
        model.addAttribute("buddy", productReview.getBuddy());


        return "redirect:/product/" + id;
    }



}