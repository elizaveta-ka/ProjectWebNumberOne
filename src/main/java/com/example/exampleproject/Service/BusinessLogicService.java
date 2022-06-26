package com.example.exampleproject.Service;

import com.example.exampleproject.model.Buddy;
import com.example.exampleproject.model.Product;
import com.example.exampleproject.model.ProductReview;
import com.example.exampleproject.repository.BuddyRepository;
import com.example.exampleproject.repository.BusinessRepository;
import com.example.exampleproject.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BusinessLogicService {
    
    //business logic
    public List<Product> createProductRecommendations(List<Product> products, Buddy buddy) {
        //List продуктов, на которые buddy оставлял отзыв
        List<ProductReview> productReviews = (List<ProductReview>) buddy.getProductAuthors();

        Set<Product> buddyProducts = buddy.getProducts();

        List<Product> productRecommend = calculateRecommend(products, productReviews);

        //delete wishlist
        for (int i = 0; i < productRecommend.size(); i++) {
            for (var pR : buddyProducts) {
                if (productRecommend.get(i).getProductId() == pR.getProductId()) {
                    productRecommend.remove(productRecommend.get(i));
                }
            }
        }
        // помещаем в map, сортируем по ключу, забираем отсортированные значения
        Map<Float, Product> mapPopularCategory = new TreeMap<>(Collections.reverseOrder());
        for (var p : productRecommend) {
            mapPopularCategory.put(p.getPrRating(), p);
        }
        List<Product> productsNew2 = new ArrayList<>(mapPopularCategory.values());
        System.out.println(Arrays.toString(productsNew2.toArray()));

        return productsNew2;
    }

    public List<Product> calculateRecommend(List<Product> products, List<ProductReview> productReviews) {
        List<Product> productRecommend = new ArrayList<>();
        List<Integer> popularCategory = getPopularCategory(productReviews);

        for (int i = 0; i < popularCategory.size(); i++) {
            int categoryId = popularCategory.get(i);
            for (int j = 0; j < products.size(); j++) {
                if(products.get(j).getProductCategory().getCategoryId() == categoryId) {
                    productRecommend.add(products.get(j));
                }
            }
        }
        return productRecommend;
    }


    //возвращает List id 3х популярных категорий у buddy(продукты, на которые buddy оставлял отзыв)
    public List<Integer> getPopularCategory(List<ProductReview> productReviews) {
        List <Integer> productCategories = new ArrayList<>();
        for (var p : productReviews) {
            productCategories.add(p.getProduct().getProductCategory().getCategoryId());
        }
        int count = 0;
        List<Integer> listPopularCategory = new ArrayList<>();
        while (count != 3) {
        int tempOccurrences = 0;
        int tempElement = 0;
        int mostOccurrences = 0;
        int mostElement = 0;
            for (int i = 0; i < productCategories.size(); i++) {
                if (tempElement == productCategories.get(i)) {
                    tempOccurrences++;
                    if (tempOccurrences > mostOccurrences) {
                        mostOccurrences = tempOccurrences;
                        mostElement = tempElement;
                    }
                } else {
                    tempOccurrences = 1;
                    tempElement = productCategories.get(i);
                }
            }
            if(productCategories.size() == 1) {
                mostElement = productCategories.get(0);
            }
            listPopularCategory.add(mostElement);
            count++;
            while(productCategories.contains(mostElement)){
                productCategories.remove((Integer) mostElement);
            }
        }
            return listPopularCategory;
        }
}
