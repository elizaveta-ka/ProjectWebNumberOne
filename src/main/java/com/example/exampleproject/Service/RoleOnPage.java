package com.example.exampleproject.Service;

import com.example.exampleproject.model.*;
import com.example.exampleproject.repository.BuddyRepository;
import com.example.exampleproject.repository.BusinessRepository;
import com.example.exampleproject.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleOnPage {

    private final BuddyRepository buddyRepository;

    private final BusinessRepository businessRepository;

    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public RoleOnPage(BuddyRepository buddyRepository, BusinessRepository businessRepository, ProductCategoryRepository productCategoryRepository) {
        this.buddyRepository = buddyRepository;
        this.businessRepository = businessRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    public Buddy findRoleBuddyOnPage(User user) {
        int bId = 0;
        Collection<Buddy> buddies = buddyRepository.findAll();
        for (var b : buddies) {
            User user2 = b.getUser();
            if (user.equals(user2)) {
                bId = b.getBuddyId();
            }
        }
        Buddy buddy = buddyRepository.getById(bId);
        return buddy;
    }

    public Business findRoleBusinessOnPage(User user) {
        int buId = 0;
        Collection<Business> businesses = businessRepository.findAll();
        for (var b : businesses) {
            User user2 = b.getUser();
            if (user.equals(user2)) {
                buId = b.getBusinessId();
            }
        }
        Business business = businessRepository.getById(buId);
        return business;
    }

    //business logic
    public List<Product> createProductRecommendations(List<Product> products, Buddy buddy) {
        //перевод из Set в List
        List<Product> buddyProducts = new ArrayList<>();
        for (var bProduct:buddy.getProducts()) {
            buddyProducts.add(bProduct);
        }
        //исключаем wishlist
        for (int i = 0; i < products.size(); i++) {
            for (int j = 0; j < buddyProducts.size(); j++) {
                if(buddyProducts.get(j).getProductId() == products.get(i).getProductId()) {
                    products.remove(products.get(i));
                }
            }
        }
       // система приоритета
        List<Product> productRecommend = calculatePriority(products, buddyProducts);

        return productRecommend;
    }

    public List<Product> calculatePriority(List<Product> products, List<Product> buddyProducts) {
        // map Оценка: Продукт отсортированна по убыванию оценки
        Map<Float, Product> map = new TreeMap<>(Collections.reverseOrder());
        for (var p:products) {
            if(p.getProductCategory().getCategoryId() != getPopularCategory(buddyProducts))
            map.put(p.getPrRating(), p);
        }

        //отсортированный лист по оценке
        List<Product> productsNew1 = new ArrayList<>(map.values());

        // map Оценка: Продукт отсортированна по убыванию оценки на популярную категорию
        Map<Float, Product> mapPopularCategory = new TreeMap<>(Collections.reverseOrder());
        for (var p:products) {
            if(p.getProductCategory().getCategoryId() == getPopularCategory(buddyProducts))
            mapPopularCategory.put(p.getPrRating(), p);
        }

        List<Product> productsNew2 = new ArrayList<>(mapPopularCategory.values());
        productsNew2.addAll(productsNew1);
        return productsNew2;
    }


    //возвращает id популярной категории у buddy(самое наибольшее количество в wishlist)
    public Integer getPopularCategory(List<Product> buddyProducts){
        List<Integer> productCategories = new ArrayList<>();
            for (var p:buddyProducts) {
                productCategories.add(p.getProductCategory().getCategoryId());
            }
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
        return mostElement;
    }

}
