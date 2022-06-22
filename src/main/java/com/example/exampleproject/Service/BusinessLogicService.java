package com.example.exampleproject.Service;

import com.example.exampleproject.model.Buddy;
import com.example.exampleproject.model.Product;
import com.example.exampleproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BusinessLogicService {

    private ProductRepository productRepository;

    @Autowired
    public BusinessLogicService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }





    public double createSimilarityCoefficient(Buddy buddy, Buddy buddyForComparison) {
        double coefficient = 0;

        return coefficient;
    }







    public List<Product> makeRec(Buddy buddy) {
        List<Product> bestProducts = null;
        System.out.println(buddy);
        return bestProducts;
    }
}
