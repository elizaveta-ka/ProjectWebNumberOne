package com.example.exampleproject.model;

import javax.persistence.*;

@Entity
@Table
public class BusinessReview {

    @OneToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="review_id")
    private Review review;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="business_id", insertable = false, updatable = false)
    private Business business;

    @Id
    @Column(name = "businessReview_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int businessReview_id;

    @Column(name = "rateB1")
    private int rateB1;

    @Column(name = "rateB2")
    private int rateB2;

    @Column(name = "rateB3")
    private int rateB3;

    @Column(name = "reviewB")
    private String reviewB;

    @Column(name = "photo")
    private String photo;

    @Column(name = "business_id")
    private int business_id;

    @Override
    public String toString() {
        return "BusinessReview{" +
                "review=" + review +
                ", businessReview_id=" + businessReview_id +
                ", rateB1=" + rateB1 +
                ", rateB2=" + rateB2 +
                ", rateB3=" + rateB3 +
                ", reviewB='" + reviewB + '\'' +
                ", photo='" + photo + '\'' +
                ", business_id=" + business_id +
                '}';
    }

    public BusinessReview() {
    }

    public BusinessReview(int businessReview_id, int rateB1, int rateB2, int rateB3, String reviewB, String photo, int business_id) {
        this.businessReview_id = businessReview_id;
        this.rateB1 = rateB1;
        this.rateB2 = rateB2;
        this.rateB3 = rateB3;
        this.reviewB = reviewB;
        this.photo = photo;
        this.business_id = business_id;
    }

    public int getBusinessReview_id() {
        return businessReview_id;
    }

    public int getRateB1() {
        return rateB1;
    }

    public int getRateB2() {
        return rateB2;
    }

    public int getRateB3() {
        return rateB3;
    }

    public String getReviewB() {
        return reviewB;
    }

    public String getPhoto() {
        return photo;
    }

    public int getBusiness_id() {
        return business_id;
    }

    public void setBusinessReview_id(int businessReview_id) {
        this.businessReview_id = businessReview_id;
    }

    public void setRateB1(int rateB1) {
        this.rateB1 = rateB1;
    }

    public void setRateB2(int rateB2) {
        this.rateB2 = rateB2;
    }

    public void setRateB3(int rateB3) {
        this.rateB3 = rateB3;
    }

    public void setReviewB(String reviewB) {
        this.reviewB = reviewB;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }
}
