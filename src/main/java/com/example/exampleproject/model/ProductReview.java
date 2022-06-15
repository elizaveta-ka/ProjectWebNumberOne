package com.example.exampleproject.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

@Entity
@Table
public class ProductReview {
    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "buddy_id", insertable = false, updatable = false)
    private Buddy buddy;

    @Id
    @Column(name = "productReview_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productReviewId;

    @Column(name = "rateP1")
    private int rateP1;

    @Column(name = "rateP2")
    private int rateP2;

    @Column(name = "rateP3")
    private int rateP3;

    @Column(name = "rateP4")
    private int rateP4;

    @Column(name = "reviewProduct")
    private String reviewProduct;

    @Column(name = "photo")
    private String photo;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "reviewTitle")
    private String reviewTitle;
    //
    @Column(name = "buddy_id")
    private int buddyId;

    public ProductReview(){

    }

    public ProductReview(Product product, Buddy buddy, int buddyId, int productReviewId, int rateP1, int rateP2, int rateP3, int rateP4, String reviewProduct, String photo, int productId, String reviewTitle) {
        this.product = product;
        this.buddy = buddy;
        this.productReviewId = productReviewId;
        this.rateP1 = rateP1;
        this.rateP2 = rateP2;
        this.rateP3 = rateP3;
        this.rateP4 = rateP4;
        this.reviewProduct = reviewProduct;
        this.photo = photo;
        this.productId = productId;
        this.buddyId = buddyId;
        this.reviewTitle = reviewTitle;
    }

    public int getProductReviewId() {
        return productReviewId;
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

    public int getProductId() {
        return productId;
    }

    public void setProductReviewId(int productReviewId) {
        this.productReviewId = productReviewId;
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

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Buddy getBuddy() {
        return buddy;
    }

    public void setBuddy(Buddy buddy) {
        this.buddy = buddy;
    }

    public int getRateP4() {
        return rateP4;
    }

    public void setRateP4(int rateP4) {
        this.rateP4 = rateP4;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    @Override
    public String toString() {
        return "ProductReview{" +
                "product=" + product +
                ", productReviewId=" + productReviewId +
                ", rateP1=" + rateP1 +
                ", rateP2=" + rateP2 +
                ", rateP3=" + rateP3 +
                ", reviewProduct='" + reviewProduct +
                ", photo='" + photo +
                ", productId=" + productId +
                '}';
    }
}