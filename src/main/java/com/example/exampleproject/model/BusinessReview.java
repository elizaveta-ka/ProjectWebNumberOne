package com.example.exampleproject.model;

import javax.persistence.*;

@Entity
@Table
public class BusinessReview {

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "buddy_id", insertable = false, updatable = false)
    private Buddy buddy;
    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="business_id", insertable = false, updatable = false)
    private Business business;

    @Id
    @Column(name = "businessReview_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int businessReviewId;

    @Column(name = "rateB1")
    private int rateB1;

    @Column(name = "rateB2")
    private int rateB2;

    @Column(name = "rateB3")
    private int rateB3;

    @Column(name = "rateB4")
    private int rateB4;

    @Column(name = "reviewBusiness")
    private String reviewBusiness;

    @Column(name = "photo")
    private String photo;

    @Column(name = "business_id")
    private int businessId;
    @Column(name = "review_title")
    private String reviewTitle;


    public BusinessReview() {
    }

    public BusinessReview(Buddy buddy, Business business, int businessReviewId, int rateB1, int rateB2, int rateB3, int rateB4, String reviewBusiness, String photo, int businessId) {
        this.buddy = buddy;
        this.business = business;
        this.businessReviewId = businessReviewId;
        this.rateB1 = rateB1;
        this.rateB2 = rateB2;
        this.rateB3 = rateB3;
        this.rateB4 = rateB4;
        this.reviewBusiness = reviewBusiness;
        this.photo = photo;
        this.businessId = businessId;
        this.reviewTitle = reviewTitle;
    }

    public int getBusinessReviewId() {
        return businessReviewId;
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

    public String getReviewBusiness() {
        return reviewBusiness;
    }

    public String getPhoto() {
        return photo;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessReviewId(int businessReviewId) {
        this.businessReviewId = businessReviewId;
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

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public Buddy getBuddy() {
        return buddy;
    }

    public void setBuddy(Buddy buddy) {
        this.buddy = buddy;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public int getRateB4() {
        return rateB4;
    }

    public void setRateB4(int rateB4) {
        this.rateB4 = rateB4;
    }

    public void setReviewBusiness(String reviewBusiness) {
        this.reviewBusiness = reviewBusiness;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    @Override
    public String toString() {
        return "BusinessReview{" +
                ", businessReview_id=" + businessReviewId +
                ", rateB1=" + rateB1 +
                ", rateB2=" + rateB2 +
                ", rateB3=" + rateB3 +
                ", reviewBusiness='" + reviewBusiness + '\'' +
                ", photo='" + photo + '\'' +
                ", business_id=" + businessId +
                '}';
    }
}
