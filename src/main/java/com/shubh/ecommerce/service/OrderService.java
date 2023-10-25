package com.shubh.ecommerce.service;

import com.shubh.ecommerce.exceptions.OrderException;
import com.shubh.ecommerce.model.Address;
import com.shubh.ecommerce.model.Order;
import com.shubh.ecommerce.model.User;

import java.util.List;

public interface OrderService {
    public Order createOrder(User user, Address shippingAddress);

    public Order findOrderById(Long orderId) throws OrderException;
    public List<Order>usersOrderHistory(Long userId);
    public Order placedOrder(Long orderId) throws OrderException;
    public Order confirmedOrder(Long orderId) throws OrderException;
    public Order shippedOrder(Long orderId) throws OrderException;
    public Order deliveredOrder(Long orderId) throws OrderException;
    public Order canceldOrder(Long orderId) throws OrderException;
    public List<Order>getAllOrders();
    public Order deletOrder(Long orderId) throws OrderException;
}
