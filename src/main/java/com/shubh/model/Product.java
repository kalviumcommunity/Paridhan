package com.shubh.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private int price;
    private int discountPrice;
    private int presentDiscount;
    private int quantity;
    private String brand;
    private String colour;
    private String image;
    @Embedded
    @ElementCollection
    private Set<Size> sizes = new HashSet<>();
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating>ratings = new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review>reviews = new ArrayList<>();
    private int num_rating;
    @ManyToOne()
    @JoinColumn(name="category_id")
    private Category category;

    private LocalDateTime createdAt;

    public Product(){

    }

    public Product(long id, String title, String description, int price, int discountPrice,int presentDiscount, int quantity, String brand, String colour, String image, Set<Size> sizes, List<Rating> ratings, List<Review> reviews, int num_rating, Category category, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.discountPrice = discountPrice;
        this.presentDiscount=presentDiscount;
        this.quantity = quantity;
        this.brand = brand;
        this.colour = colour;
        this.image = image;
        this.sizes = sizes;
        this.ratings = ratings;
        this.reviews = reviews;
        this.num_rating = num_rating;
        this.category = category;
        this.createdAt = createdAt;
    }

    public int getPresentDiscount() {
        return presentDiscount;
    }

    public void setPresentDiscount(int presentDiscount) {
        this.presentDiscount = presentDiscount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Size> getSizes() {
        return sizes;
    }

    public void setSizes(Set<Size> sizes) {
        this.sizes = sizes;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public int getNum_rating() {
        return num_rating;
    }

    public void setNum_rating(int num_rating) {
        this.num_rating = num_rating;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime now) {
        this.createdAt = createdAt;
    }
}
