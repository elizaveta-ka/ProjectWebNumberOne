package com.example.exampleproject.service;

import com.example.exampleproject.model.Buddy;
import com.example.exampleproject.model.Wishlist;
import com.example.exampleproject.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private WishlistRepository wishlistRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public List<Wishlist> findAll() {
        return wishlistRepository.findAll();
    }

    public Wishlist saveWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    public Wishlist findById(int id) {
        return wishlistRepository.getOne(id);
    }

    public void deleteById(int id) {
        wishlistRepository.deleteById(id);
    }
}
