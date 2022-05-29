package com.example.exampleproject.service;

import com.example.exampleproject.model.Friend;
import com.example.exampleproject.model.Wishlist;
import com.example.exampleproject.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    private FriendRepository friendRepository;

    @Autowired
    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public List<Friend> findAll() {
        return friendRepository.findAll();
    }

    public Friend saveFriend(Friend friend) {
        return friendRepository.save(friend);
    }

    public Friend findById(int id) {
        return friendRepository.getOne(id);
    }

    public void deleteById(int id) {
        friendRepository.deleteById(id);
    }
}
