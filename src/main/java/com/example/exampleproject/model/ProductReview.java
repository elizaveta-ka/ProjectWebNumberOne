package com.example.exampleproject.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

@Entity
@Table
public class ProductReview {

    @OneToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="review_id")
    private Review review;
    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="product_id", insertable = false, updatable = false)
    private Product product;


    @Id
    @Column(name = "productReview_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productReview_id;

    @Column(name = "rateP1")
    private int rateP1;

    @Column(name = "rateP2")
    private int rateP2;

    @Column(name = "rateP3")
    private int rateP3;

    @Column(name = "reviewProduct")
    private String reviewProduct;

    @Column(name = "photo")
    private String photo;

    @Column(name = "product_id")
    private int product_id;

    public ProductReview(){

    }

    public ProductReview(int productReview_id, int rateP1, int rateP2, int rateP3, String reviewProduct, String photo, int product_id) {
        this.productReview_id = productReview_id;
        this.rateP1 = rateP1;
        this.rateP2 = rateP2;
        this.rateP3 = rateP3;
        this.reviewProduct = reviewProduct;
        this.photo = photo;
        this.product_id = product_id;
    }

    public int getProductReview_id() {
        return productReview_id;
    }

    public int getRateP1() {
        return rateP1;
    }

    public int getRateP2() {
        return rateP2;
    }

    public int getRateP3() {
        return rateP3;
    }

    public String getReviewProduct() {
        return reviewProduct;
    }

    public String getPhoto() {
        return photo;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProductReview_id(int productReview_id) {
        this.productReview_id = productReview_id;
    }

    public void setRateP1(int rateP1) {
        this.rateP1 = rateP1;
    }

    public void setRateP2(int rateP2) {
        this.rateP2 = rateP2;
    }

    public void setRateP3(int rateP3) {
        this.rateP3 = rateP3;
    }

    public void setReviewProduct(String reviewProduct) {
        this.reviewProduct = reviewProduct;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

}
