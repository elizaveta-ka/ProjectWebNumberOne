package com.example.exampleproject.model;

import javax.persistence.*;

@Entity
@Table
public class Suggestion {

    @OneToOne(mappedBy = "suggestion", cascade = CascadeType.ALL)
    private ProductCategory productCategory;

    @Id
    @Column(name = "food_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int food_id;

    @Column(name = "food_name")
    private String food_name;

    @Column(name = "category_id")
    private int category_id;

    @Column(name = "food_img")
    private String food_img;

    public Suggestion() {

    }

    public Suggestion(int food_id, String food_name, int category_id, String food_img) {
        this.food_id = food_id;
        this.food_name = food_name;
        this.category_id = category_id;
        this.food_img = food_img;
    }

    public int getFood_id() {
        return food_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getFood_img() {
        return food_img;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setFood_img(String food_img) {
        this.food_img = food_img;
    }
}
