//package com.example.exampleproject;
//
//import com.example.exampleproject.model.Buddy;
//import com.example.exampleproject.model.Product;
//import com.example.exampleproject.repository.BuddyRepository;
//import com.example.exampleproject.repository.ProductRepository;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Commit;
//
//@DataJpaTest
//@Commit
//public class ManyToManyTest {
//
//    @Autowired
//    private BuddyRepository buddyRepository;
//    @Autowired
//    private ProductRepository productRepository;
//
//
//    @BeforeEach
//    public void booksShouldBeAdded() {
////        Product product1 = new Product("p1");
////
////        Buddy buddy1 = new Buddy("b1");
////        Buddy buddy2 = new Buddy("b2");
////        product1.addBuddy(buddy1);
////
////
////
////
////
//        Buddy buddy1 = new Buddy("b1");
//
//        Product p1 = new Product("p1");
//        Product p2 = new Product("p2");
//        buddy1.addProduct(p1);
//        buddy1.addProduct(p2);
//
//        buddyRepository.save(buddy1);
//
//        Buddy buddy2 = new Buddy("b2");
//        buddy2.addProduct(p1);
//        buddyRepository.save(buddy2);
//
//        Assertions.assertEquals(2, buddyRepository.count());
//        Assertions.assertEquals(2, productRepository.count());
//    }
//
//    @Test
//    @DisplayName("при удалении buddy у продукта с Set должен выполняться один delete-оператор")
//    public void whenDeleteAuthorFromBook_thenOneDeleteStatement() {
//        Buddy buddy = buddyRepository.findByFirstName("a1");
//        Product product = productRepository.findByName("b1");
//        buddy.removeProduct(product);
//        }
//    }
