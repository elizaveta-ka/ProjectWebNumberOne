package com.example.exampleproject.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductCategory productCategory;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private BusinessProduct businessProduct;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Wishlist wishlist;

    @ManyToMany
    @JoinTable(name="businessProduct",
            joinColumns=@JoinColumn(name="product_id"),
            inverseJoinColumns=@JoinColumn(name="business_id"))
    private List<Business> businesses;

    @ManyToMany
    @JoinTable(name="wishlist",
            joinColumns=@JoinColumn(name="product_id"),
            inverseJoinColumns=@JoinColumn(name="buddy_id"))
    private List<Buddy> buddies;

    @OneToMany (mappedBy="product", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<ProductReview> productReviews;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private int product_id;

    @Column(name = "product_name")
    private String product_name;
    @Column(name = "category_id")
    private int category_id;
    @Column(name = "product_img")
    private String prod_img;

    public Product(){}

    public Product(ProductCategory productCategory, BusinessProduct businessProduct, Wishlist wishlist, List<Business> businesses, List<Buddy> buddies, Collection<ProductReview> productReviews, int product_id, String product_name, int category_id, String prod_img) {
        this.productCategory = productCategory;
        this.businessProduct = businessProduct;
        this.wishlist = wishlist;
        this.businesses = businesses;
        this.buddies = buddies;
        this.productReviews = productReviews;
        this.product_id = product_id;
        this.product_name = product_name;
        this.category_id = category_id;
        this.prod_img = prod_img;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public BusinessProduct getBusinessProduct() {
        return businessProduct;
    }

    public void setBusinessProduct(BusinessProduct businessProduct) {
        this.businessProduct = businessProduct;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public List<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }

    public List<Buddy> getBuddies() {
        return buddies;
    }

    public void setBuddies(List<Buddy> buddies) {
        this.buddies = buddies;
    }

    public Collection<ProductReview> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(Collection<ProductReview> productReviews) {
        this.productReviews = productReviews;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getProd_img() {
        return prod_img;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setProd_img(String prod_img) {
        this.prod_img = prod_img;
    }
}
