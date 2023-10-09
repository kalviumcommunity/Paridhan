package com.shubh.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "order_id")
    private String order_id;
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem>orderItems=new ArrayList<>();
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;

    @OneToOne
    private Address shippingAddress;

    @Embedded
    private PaymentDetails paymentDetails =new PaymentDetails();
    private  double totalPrice;
    private Integer totalDiscountPrice;
    private Integer discount;
    private String orderStatus;
    private int totalItems;
    private LocalDateTime createAt;


    public Order(){

    }

    public Order(Long id, String order_id, User user, List<OrderItem> orderItems, LocalDateTime orderDate, LocalDateTime deliveryDate, Address shippingAddress, PaymentDetails paymentDetails, double totalPrice, Integer totalDiscountPrice, Integer discount, String orderStatus, int totalItems, LocalDateTime createAt) {
        this.id = id;
        this.order_id = order_id;
        this.user = user;
        this.orderItems = orderItems;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.shippingAddress = shippingAddress;
        this.paymentDetails = paymentDetails;
        this.totalPrice = totalPrice;
        this.totalDiscountPrice = totalDiscountPrice;
        this.discount = discount;
        this.orderStatus = orderStatus;
        this.totalItems = totalItems;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public void setTotalDiscountPrice(Integer totalDiscountPrice) {
        this.totalDiscountPrice = totalDiscountPrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
