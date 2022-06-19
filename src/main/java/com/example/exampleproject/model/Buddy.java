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
@Table(name = "buddies")
public class Buddy {

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
    private Set<Product> products = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
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
    @Column(name = "avatar_img", length = 2000)
    private byte[] avatarImg;

    public Buddy() {
    }

    public Buddy(Collection friends, String firstName, String lastName, int age, String city, byte[] avatarImg) {
        this.friends = friends;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
        this.avatarImg = avatarImg;
    }

    public Buddy(Collection<BusinessReview> businessAuthors, Collection<ProductReview> productAuthors, Collection<Friend> friends, Set<Product> products, User user, int buddyId, String firstName, String lastName, int age, String city, byte[] avatarImg) {
        this.businessAuthors = businessAuthors;
        this.productAuthors = productAuthors;
        this.friends = friends;
        this.products = products;
        this.user = user;
        this.buddyId = buddyId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
        this.avatarImg = avatarImg;
    }

    public Buddy(int buddyId, String firstName, String lastName, int age, String city, byte[] avatarImg) {
        this.buddyId = buddyId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
        this.avatarImg = avatarImg;
    }

    @Override
    public String toString() {
        return "Buddy{" +
                ", businessAuthors=" + businessAuthors +
                ", productAuthors=" + productAuthors +
                ", friends=" + friends +
//                ", products=" + products +
                ", buddyId=" + buddyId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", avatarImg='" + avatarImg + '\'' +
                '}';
    }
    public void addProduct(Product product){
        this.products.add(product);
        product.getBuddies().add(this);
    }

    public void removeProduct(Product product){
        this.products.remove(product);
        product.getBuddies().remove(this);
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

    public Collection<BusinessReview> getBusinessAuthors() {
        return businessAuthors;
    }

    public void setBusinessAuthors(Collection<BusinessReview> businessAuthors) {
        this.businessAuthors = businessAuthors;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public int getBuddyId() {
        return buddyId;
    }

    public void setBuddyId(int buddyId) {
        this.buddyId = buddyId;
    }

    public void setAvatarImg(byte[] avatarImg) {
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

    public byte[] getAvatarImg() {
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

    public Collection<ProductReview> getProductAuthors() {
        return productAuthors;
    }

    public void setProductAuthors(Collection<ProductReview> productAuthors) {
        this.productAuthors = productAuthors;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
