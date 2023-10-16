package com.shubh.service;

import com.shubh.model.Cart;
import com.shubh.model.User;

public interface CartService {
    public Cart createCart(User user);

    public Cart findUserCart(Long userId);
}
