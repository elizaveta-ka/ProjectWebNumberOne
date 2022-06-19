package com.example.exampleproject.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @ManyToMany
    @JoinTable(name="businessProduct",
            joinColumns=@JoinColumn(name="product_id"),
            inverseJoinColumns=@JoinColumn(name="business_id"))
    private Set<Business> businesses;

    @ManyToMany
    @JoinTable(name="wishlist",
            joinColumns=@JoinColumn(name="product_id"),
            inverseJoinColumns=@JoinColumn(name="buddy_id"))
    private Set<Buddy> buddies;

    @OneToMany (mappedBy="product", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<ProductReview> productReviews;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private int productId;

    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_img")
    private String productImg;

    public Product(){}

    public Product(Set<Business> businesses, Set<Buddy> buddies, Collection<ProductReview> productReviews, ProductCategory productCategory, int productId, String productName, String productImg) {
        this.businesses = businesses;
        this.buddies = buddies;
        this.productReviews = productReviews;
        this.productCategory = productCategory;
        this.productId = productId;
        this.productName = productName;
        this.productImg = productImg;
    }

    public Product(String productName, String productImg) {
        this.productName = productName;
        this.productImg = productImg;
    }

    @Override
    public String toString() {
        return "Product{" +
                "businesses=" + businesses +
//                ", buddies=" + buddies +
//                ", productReviews=" + productReviews +
                ", productCategory=" + productCategory +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productImg='" + productImg + '\'' +
                '}';
    }

    public String getProductImg() {
        return productImg;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Set<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(Set<Business> businesses) {
        this.businesses = businesses;
    }

    public Set<Buddy> getBuddies() {
        return buddies;
    }

    public void setBuddies(Set<Buddy> buddies) {
        this.buddies = buddies;
    }

    public Collection<ProductReview> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(Collection<ProductReview> productReviews) {
        this.productReviews = productReviews;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }


    public String getProdImg() {
        return productImg;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }



    public void addBusiness(Business business){
        this.businesses.add(business);
        business.getProducts().add(this);
    }
    public void removeBusiness(Business business){
        this.businesses.remove(business);
        business.getProducts().remove(this);
    }

}
