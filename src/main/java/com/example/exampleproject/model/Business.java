package com.example.exampleproject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "business")
public class Business {
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "productList",
            joinColumns = { @JoinColumn(name = "business_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
   Set<Product> products = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "business_id")
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

    public Business(int business_id, String busName, String bus_img, String location) {
        this.business_id = business_id;
        this.busName = busName;
        this.bus_img = bus_img;
        this.location = location;
    }
    public Business(Set<Product> products, int business_id, String busName, String bus_img, String location) {
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


    public Business(Set<Product> products) {
        this.products = products;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
