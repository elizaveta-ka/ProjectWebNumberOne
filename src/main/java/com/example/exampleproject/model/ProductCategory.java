package com.example.exampleproject.model;

import javax.persistence.*;

@Entity
@Table
public class ProductCategory {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Suggestion suggestion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Product product;

    @Id
    @Column(name = "category_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;

    @Column(name = "category_name")
    private String category_name;

    @Column(name = "firstAtt")
    private String firstAtt;

    @Column(name = "secondAtt")
    private String secondAtt;

    @Column(name = "thirdAtt")
    private String thirdAtt;

    public ProductCategory() {

    }

    public ProductCategory(int category_id, String category_name, String firstAtt, String secondAtt, String thirdAtt) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.firstAtt = firstAtt;
        this.secondAtt = secondAtt;
        this.thirdAtt = thirdAtt;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getFirstAtt() {
        return firstAtt;
    }

    public String getSecondAtt() {
        return secondAtt;
    }

    public String getThirdAtt() {
        return thirdAtt;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setFirstAtt(String firstAtt) {
        this.firstAtt = firstAtt;
    }

    public void setSecondAtt(String secondAtt) {
        this.secondAtt = secondAtt;
    }

    public void setThirdAtt(String thirdAtt) {
        this.thirdAtt = thirdAtt;
    }
}
