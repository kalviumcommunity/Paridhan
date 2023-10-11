package com.shubh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;
    private String size;
    private Integer price;
    private int quantity;
    private Long user_Id;
    private Integer discountedPrice;
    private LocalDateTime delivery_date;

    public OrderItem() {

    }

    public OrderItem(Long id, Order order, Product product, String size, Integer price, int quantity, Long user_Id, Integer discountedPrice, LocalDateTime delivery_date) {
       super();
        this.id = id;
        this.order = order;
        this.product = product;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
        this.user_Id = user_Id;
        this.discountedPrice = discountedPrice;
        this.delivery_date = delivery_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Long user_Id) {
        this.user_Id = user_Id;
    }

    public Integer getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Integer discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public LocalDateTime getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(LocalDateTime delivery_date) {
        this.delivery_date = delivery_date;
    }
}
