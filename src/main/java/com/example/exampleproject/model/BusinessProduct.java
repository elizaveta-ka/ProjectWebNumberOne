package com.example.exampleproject.model;

import javax.persistence.*;

@Entity
@Table
public class BusinessProduct {

    @OneToOne(mappedBy = "businessProduct", cascade = CascadeType.ALL)
    private Business business;

    @Id
    @Column(name = "business_id")
    private int bussiness_id;

    @Column(name = "product_id")
    private int product_id;

    public BusinessProduct(){}

    public BusinessProduct(Business business, int bussiness_id, int product_id) {
        this.business = business;
        this.bussiness_id = bussiness_id;
        this.product_id = product_id;
    }

    public BusinessProduct(int product_id) {
        this.product_id = product_id;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public int getBussiness_id() {
        return bussiness_id;
    }

    public void setBussiness_id(int bussiness_id) {
        this.bussiness_id = bussiness_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
