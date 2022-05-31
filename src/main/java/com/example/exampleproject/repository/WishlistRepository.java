package com.example.exampleproject.repository;

import com.example.exampleproject.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository <Wishlist, Integer> {
}
