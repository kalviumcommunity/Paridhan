package com.shubh.ecommerce.service;

import com.shubh.ecommerce.exceptions.CartItemException;
import com.shubh.ecommerce.exceptions.UserException;
import com.shubh.ecommerce.model.Cart;
import com.shubh.ecommerce.model.CartItem;
import com.shubh.ecommerce.model.Product;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Long userId, Long id,CartItem cartItem) throws CartItemException, UserException;

    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

    public void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException;

    public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
