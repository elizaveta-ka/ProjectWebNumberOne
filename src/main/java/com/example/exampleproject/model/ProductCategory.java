package com.example.exampleproject.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table
public class ProductCategory {

    @OneToMany(mappedBy = "productCategory", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Suggestion> suggestions;

    @OneToMany(mappedBy = "productCategory", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Product> product;

    @Id
    @Column(name = "category_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "firstAtt")
    private String firstAtt;

    @Column(name = "secondAtt")
    private String secondAtt;

    @Column(name = "thirdAtt")
    private String thirdAtt;

    public ProductCategory() {

    }

    public ProductCategory(int categoryId, String categoryName, String firstAtt, String secondAtt, String thirdAtt) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.firstAtt = firstAtt;
        this.secondAtt = secondAtt;
        this.thirdAtt = thirdAtt;
    }

    public ProductCategory(Collection<Suggestion> suggestions, Collection<Product> product, int categoryId, String categoryName, String firstAtt, String secondAtt, String thirdAtt) {
        this.suggestions = suggestions;
        this.product = product;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.firstAtt = firstAtt;
        this.secondAtt = secondAtt;
        this.thirdAtt = thirdAtt;
    }

    public Collection<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(Collection<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }

    public Collection<Product> getProduct() {
        return product;
    }

    public void setProduct(Collection<Product> product) {
        this.product = product;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
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

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    @Override
    public String toString() {
        return "ProductCategory{" +
                "suggestions=" + suggestions +
                ", product=" + product +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", firstAtt='" + firstAtt + '\'' +
                ", secondAtt='" + secondAtt + '\'' +
                ", thirdAtt='" + thirdAtt + '\'' +
                '}';
    }
}
