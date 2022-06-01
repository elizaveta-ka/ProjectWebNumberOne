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
    private int businessId;

    @Column(name = "bus_name")
    private String busName;

    @Column(name = "bus_img")
    private String busImg;

    @Column(name = "location")
    private String location;

    public Business() {
        super();
    }

    public Business(int businessId, String busName, String busImg, String location) {
        this.businessId = businessId;
        this.busName = busName;
        this.busImg = busImg;
        this.location = location;
    }

    public Business(BusinessLogin businessLogin, List<Product> products, Collection<BusinessReview> businessReviews, int businessId, String busName, String busImg, String location) {
        this.businessLogin = businessLogin;
        this.products = products;
        this.businessReviews = businessReviews;
        this.businessId = businessId;
        this.busName = busName;
        this.busImg = busImg;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Business{" +
                "businessLogin=" + businessLogin +
                ", products=" + products +
                ", businessReviews=" + businessReviews +
                ", businessId=" + businessId +
                ", busName='" + busName + '\'' +
                ", busImg='" + busImg + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public BusinessLogin getBusinessLogin() {
        return businessLogin;
    }

    public void setBusinessLogin(BusinessLogin businessLogin) {
        this.businessLogin = businessLogin;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Collection<BusinessReview> getBusinessReviews() {
        return businessReviews;
    }

    public void setBusinessReviews(Collection<BusinessReview> businessReviews) {
        this.businessReviews = businessReviews;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusImg() {
        return busImg;
    }

    public void setBusImg(String busImg) {
        this.busImg = busImg;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
