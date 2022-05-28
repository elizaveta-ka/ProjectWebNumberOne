package com.example.exampleproject.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int product_id;

    @Column(name = "product_name")
    private String product_name;
    @Column(name = "category_id")
    private int category_id;
    @Column(name = "product_img")
    private String prod_img;

    @ManyToMany(mappedBy = "products")
    private Set<Business> businesses = new HashSet<>();
    public int getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getProd_img() {
        return prod_img;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setProd_img(String prod_img) {
        this.prod_img = prod_img;
    }
}
