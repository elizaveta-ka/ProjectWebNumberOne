package com.example.exampleproject.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
    List<Product> products;

    @OneToMany (mappedBy="business", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<BusinessReview> businessReviews;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_id", nullable = false)
    private int business_id;

    @Column(name = "bus_name")
    private String busName;

    @Column(name = "bus_img")
    private String bus_img;

    @Column(name = "location")
    private String location;

    public Business() {
        super();
    }

    public Business(BusinessProduct businessProduct, BusinessLogin businessLogin, List<Product> products, Collection<BusinessReview> businessReviews, int business_id, String busName, String bus_img, String location) {
        this.businessProduct = businessProduct;
        this.businessLogin = businessLogin;
        this.products = products;
        this.businessReviews = businessReviews;
        this.business_id = business_id;
        this.busName = busName;
        this.bus_img = bus_img;
        this.location = location;
    }

    public Business(BusinessLogin businessLogin) {
        this.businessLogin = businessLogin;
    }

    public Business(int business_id, String busName, String bus_img, String location) {
        this.business_id = business_id;
        this.busName = busName;
        this.bus_img = bus_img;
        this.location = location;
    }
    public Business(List<Product> products, int business_id, String busName, String bus_img, String location) {
        this.products = products;
        this.business_id = business_id;
        this.busName = busName;
        this.bus_img = bus_img;
        this.location = location;
    }
    public int getBusiness_id() {
        return business_id;
    }

    public String getBusName() {
        return busName;
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
        this.busName = busName;
    }

    public void setBus_img(String bus_img) {
        this.bus_img = bus_img;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public BusinessLogin getBusinessLogin() {
        return businessLogin;
    }

    public void setBusinessLogin(BusinessLogin businessLogin) {
        this.businessLogin = businessLogin;
    }

    @Override
    public String toString() {
        return "Business{" +
                "businessProduct=" + businessProduct +
                ", businessLogin=" + businessLogin +
                ", products=" + products +
                ", businessReviews=" + businessReviews +
                ", business_id=" + business_id +
                ", busName='" + busName + '\'' +
                ", bus_img='" + bus_img + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public BusinessProduct getBusinessProduct() {
        return businessProduct;
    }

    public void setBusinessProduct(BusinessProduct businessProduct) {
        this.businessProduct = businessProduct;
    }

    public Collection<BusinessReview> getBusinessReviews() {
        return businessReviews;
    }

    public void setBusinessReviews(Collection<BusinessReview> businessReviews) {
        this.businessReviews = businessReviews;
    }
}
