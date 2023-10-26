package com.shubh.ecommerce.service;

import com.shubh.ecommerce.exceptions.ProductException;
import com.shubh.ecommerce.model.Cart;
import com.shubh.ecommerce.model.User;
import com.shubh.ecommerce.request.AddItemRequest;

public interface CartService {
    public Cart createCart(User user);

    public String addCartItem(Long userId, AddItemRequest req) throws ProductException;

    public Cart findUserCart(Long userId);
}
