package com.example.exampleproject.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table
public class Friend {

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="friend_id", insertable = false, updatable = false)
    private Buddy buddy;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buddy_id", nullable = false)
    private int buddyId;
    @Column(name = "friend_id", nullable = false)
    private int friendId;

    public Friend(){}

    public Friend(int buddyId, int friendId) {
        this.buddyId = buddyId;
        this.friendId = friendId;
    }

    @Override
    public String toString() {
        return "Friend{" +
//                "buddy=" + buddy +
                ", buddyId=" + buddyId +
                ", friendId=" + friendId +
                '}';
    }

    public Friend(int friendId) {
        this.friendId = friendId;
    }

    public int getBuddyId() {
        return buddyId;
    }

    public void setBuddyId(int buddyId) {
        this.buddyId = buddyId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }
}
