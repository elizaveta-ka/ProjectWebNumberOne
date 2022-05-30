package com.example.exampleproject.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "business")
public class Business {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "business_id")
    private BusinessProduct businessProduct;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "business_id")
    private BusinessLogin businessLogin;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "businessProduct",
            joinColumns = { @JoinColumn(name = "business_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
   Set<Product> products = new HashSet<>();

    @OneToMany (mappedBy="business", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<BusinessReview> businessReviews;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_id", nullable = false)
    private int business_id;

    @Column(name = "bus_name")
    private String bus_name;

    @Column(name = "bus_img")
    private String bus_img;

    @Column(name = "location")
    private String location;

    public Business() {
        super();
    }

    public Business(Set<Product> products) {
        this.products = products;
    }

    public Business(BusinessLogin businessLogin) {
        this.businessLogin = businessLogin;
    }

    public Business(int business_id, String bus_name, String bus_img, String location) {
        this.business_id = business_id;
        this.bus_name = bus_name;
        this.bus_img = bus_img;
        this.location = location;
    }
    public Business(Set<Product> products, int business_id, String bus_name, String bus_img, String location) {
        this.products = products;
        this.business_id = business_id;
        this.bus_name = bus_name;
        this.bus_img = bus_img;
        this.location = location;
    }
    public int getBusiness_id() {
        return business_id;
    }

    public String getBusName() {
        return bus_name;
    }

    public String getBus_img() {
        return bus_img;
    }

    public String getLocation() {
        return location;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }

    public void setBusName(String busName) {
        this.bus_name = busName;
    }

    public void setBus_img(String bus_img) {
        this.bus_img = bus_img;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public BusinessLogin getBusinessLogin() {
        return businessLogin;
    }

    public void setBusinessLogin(BusinessLogin businessLogin) {
        this.businessLogin = businessLogin;
    }
}
