package com.example.exampleproject.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "buddies")
public class Buddy {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buddy_id")
    private BuddyLogin buddyLogin;

    @OneToMany(mappedBy = "buddy", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<BusinessReview> businessAuthors;

    @OneToMany(mappedBy = "buddy", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<ProductReview> productAuthors;

    @OneToMany(mappedBy = "buddy", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Friend> friends;

    @ManyToMany
    @JoinTable (name="wishlist",
            joinColumns=@JoinColumn (name="buddy_id"),
            inverseJoinColumns=@JoinColumn(name="product_id"))
    private List<Product> products;

    @Id
    @Column(name = "buddy_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buddyId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private int age;
    @Column(name = "city")
    private String city;
    @Column(name = "avatar_img")
    private String avatarImg;

    public Buddy() {
    }

    public Buddy(Collection friends, String firstName, String lastName, int age, String city, String avatarImg) {
        this.friends = friends;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
        this.avatarImg = avatarImg;
    }

    public Buddy(Collection friends, BuddyLogin buddyLogin, Collection<BusinessReview> businessAuthors, List<Product> products, int buddyId, String firstName, String lastName, int age, String city, String avatarImg) {
        this.friends = friends;
        this.buddyLogin = buddyLogin;
        this.businessAuthors = businessAuthors;
        this.products = products;
        this.buddyId = buddyId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
        this.avatarImg = avatarImg;
    }

    public Buddy(int buddyId, String firstName, String lastName, int age, String city, String avatarImg) {
        this.buddyId = buddyId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
        this.avatarImg = avatarImg;
    }

    public void setFriends(Collection<Friend> friends) {
        this.friends = friends;
    }

    public Collection<Friend> getFriends() {
        return friends;
    }

    public void setFriend(Collection friends) {
        this.friends = friends;
    }

    public BuddyLogin getBuddyLogin() {
        return buddyLogin;
    }

    public void setBuddyLogin(BuddyLogin buddyLogin) {
        this.buddyLogin = buddyLogin;
    }

    public Collection<BusinessReview> getBusinessAuthors() {
        return businessAuthors;
    }

    public void setBusinessAuthors(Collection<BusinessReview> businessAuthors) {
        this.businessAuthors = businessAuthors;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getBuddyId() {
        return buddyId;
    }

    public void setBuddyId(int buddyId) {
        this.buddyId = buddyId;
    }

    public void setAvatarImg(String avatarImg) {
        this.avatarImg = avatarImg;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getAvatarImg() {
        return avatarImg;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAvatar_img(String avatarImg) {
        this.avatarImg = avatarImg;
    }

    public Collection<ProductReview> getProductAuthors() {
        return productAuthors;
    }

    public void setProductAuthors(Collection<ProductReview> productAuthors) {
        this.productAuthors = productAuthors;
    }

    @Override
    public String toString() {
        return "Buddy{" +
                "buddyLogin=" + buddyLogin +
                ", businessAuthors=" + businessAuthors +
                ", productAuthors=" + productAuthors +
                ", friends=" + friends +
                ", products=" + products +
                ", buddyId=" + buddyId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", avatarImg='" + avatarImg + '\'' +
                '}';
    }
}
