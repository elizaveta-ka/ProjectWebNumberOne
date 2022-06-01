package com.example.exampleproject.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table
public class Suggestion {
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private ProductCategory productCategory;
    @Id
    @Column(name = "food_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int foodId;

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "food_img")
    private String foodImg;

    public Suggestion() {

    }

    public Suggestion(int foodId, String foodName, int categoryId, String foodImg) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.categoryId = categoryId;
        this.foodImg = foodImg;
    }

    public int getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getFoodImg() {
        return foodImg;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setFoodImg(String foodImg) {
        this.foodImg = foodImg;
    }
}
