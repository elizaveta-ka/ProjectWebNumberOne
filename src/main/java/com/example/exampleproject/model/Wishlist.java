package com.example.exampleproject.model;

import javax.persistence.*;

@Entity
@Table
public class Wishlist {

    @OneToOne(mappedBy = "wishlist", cascade = CascadeType.ALL)
    private Buddy buddy;

    @OneToOne(mappedBy = "wishlist", cascade = CascadeType.ALL)
    private Product product;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buddy_id", nullable = false)
    private int buddy_id;

    @Column(name = "product_id")
    private int product_id;

    public Wishlist(int buddy_id, int product_id) {
        this.buddy_id = buddy_id;
        this.product_id = product_id;
    }

    public Wishlist(int product_id) {
        this.product_id = product_id;
    }

    public Wishlist() {}

    public int getBuddy_id() {
        return buddy_id;
    }

    public void setBuddy_id(int buddy_id) {
        this.buddy_id = buddy_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
