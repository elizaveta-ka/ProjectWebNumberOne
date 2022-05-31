package com.example.exampleproject.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table
public class Friend {

    @OneToOne(mappedBy = "friend", cascade = CascadeType.ALL)
    private Buddy buddy;

    @ManyToMany
    @JoinTable(name="friend",
            joinColumns=@JoinColumn(name="friend_id"),
            inverseJoinColumns=@JoinColumn(name="buddy_id"))
    private List<Buddy> buddies;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buddy_id", nullable = false)
    private int buddy_id;
    @Column(name = "friend_id")
    private int friend_id;

    public Friend(){}

    public Friend(int buddy_id, int friend_id) {
        this.buddy_id = buddy_id;
        this.friend_id = friend_id;
    }

    public Friend(int friend_id) {
        this.friend_id = friend_id;
    }

    public int getBuddy_id() {
        return buddy_id;
    }

    public void setBuddy_id(int buddy_id) {
        this.buddy_id = buddy_id;
    }

    public int getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(int friend_id) {
        this.friend_id = friend_id;
    }
}
