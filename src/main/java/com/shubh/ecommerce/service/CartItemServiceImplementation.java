package com.shubh.ecommerce.service;


import com.shubh.ecommerce.exceptions.CartItemException;
import com.shubh.ecommerce.exceptions.UserException;
import com.shubh.ecommerce.model.Cart;
import com.shubh.ecommerce.model.CartItem;
import com.shubh.ecommerce.model.Product;
import com.shubh.ecommerce.model.User;
import com.shubh.ecommerce.repository.CartItemRepository;
import com.shubh.ecommerce.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImplementation implements CartItemService {

    private CartItemRepository cartItemRepository;
    private UserService userService;
    private CartRepository cartRepository;

    public CartItemServiceImplementation(CartItemRepository cartItemRepository,UserService userService) {
        this.cartItemRepository=cartItemRepository;
        this.userService=userService;
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) {

        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice((int) (cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity()));

        CartItem createdCartItem=cartItemRepository.save(cartItem);

        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {

        CartItem item=findCartItemById(id);
        User user=userService.findUserById(item.getUserId());


        if(user.getId().equals(userId)) {

            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity()*item.getProduct().getPrice());
            item.setDiscountedPrice((int) (item.getQuantity()*item.getProduct().getDiscountedPrice()));

            return cartItemRepository.save(item);


        }
        else {
            throw new CartItemException("You can't update  another users cart_item");
        }

    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {

        CartItem cartItem=cartItemRepository.isCartItemExist(cart, product, size, userId);

        return cartItem;
    }



    @Override
    public void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException {

        System.out.println("userId- "+userId+" cartItemId "+cartItemId);

        CartItem cartItem=findCartItemById(cartItemId);

        User user=userService.findUserById(cartItem.getUserId());
        User reqUser=userService.findUserById(userId);

        if(user.getId().equals(reqUser.getId())) {
            cartItemRepository.deleteById(cartItem.getId());
        }
        else {
            throw new UserException("you can't remove anothor users item");
        }

    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> opt=cartItemRepository.findById(cartItemId);

        if(opt.isPresent()) {
            return opt.get();
        }
        throw new CartItemException("cartItem not found with id : "+cartItemId);
    }

}
