package com.example.exampleproject.Service;

import com.example.exampleproject.model.Buddy;
import com.example.exampleproject.model.Product;
import com.example.exampleproject.model.ProductReview;
import com.example.exampleproject.repository.BuddyRepository;
import com.example.exampleproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Collection;
import java.util.List;
@Service
public class BusinessLogicService {

    private ProductRepository productRepository;

    private BuddyRepository buddyRepository;

    @Autowired
    public BusinessLogicService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }





    public double createSimilarityCoefficient(Buddy buddy, Buddy buddyForComparison) {
        buddy = buddyRepository.getById(1);
        buddyForComparison = buddyRepository.getById(2);
        compareProductRate(buddy, buddyForComparison);
        double temp = 0;
        double a = 0;
        double b = 0;
        double c = 0;

        a = compareProductRate(buddy, buddyForComparison);
        b = Math.sqrt(compareProductRate(buddy, buddy));
        c = Math.sqrt(compareProductRate(buddyForComparison, buddyForComparison));
        temp = b * c;
        if(temp == 0) {
            return 0;
        }
        return a/ (b * c);
    }


    
    public double createKCoeff(List<Buddy> buddies, Buddy buddy) {
        double kCoeff = 0;
        for (int i = 0; i < buddies.size(); i++) {
            if(buddies.get(i).equals(buddy.getBuddyId()))
                continue;
            kCoeff += 1/Math.abs(createSimilarityCoefficient(buddy, buddies.get(i)));
        }
        return kCoeff;
    }




    public List<Product> makeRec(Buddy buddy) {
        List<Product> bestProducts = null;
        System.out.println(buddy);
        return bestProducts;
    }


    public double compareProductRate(Buddy buddy, Buddy buddyForComparison) {
        double d = 0;
//        buddy = buddyRepository.getById(1);
//        buddyForComparison = buddyRepository.getById(2);
        Collection<Product> products = buddyForComparison.getProducts();
        Collection<Product> products2 = buddy.getProducts();
        for (var pr : products) {
            for (var p: products2) {
                if (pr.getProductId() == p.getProductId()) {
                    for (var r:pr.getProductReviews()) {
                        for (var r2:p.getProductReviews()) {
                            d += r.getRateP4() * r2.getRateP4();
                        }
                    }
                }
            }
        }
        System.out.println(d);
        return d;
    }
}
