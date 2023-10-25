package com.shubh.ecommerce.controller;


import com.shubh.ecommerce.exceptions.ProductException;
import com.shubh.ecommerce.exceptions.UserException;
import com.shubh.ecommerce.model.Cart;
import com.shubh.ecommerce.model.User;
import com.shubh.ecommerce.request.AddItemRequest;
import com.shubh.ecommerce.response.ApiResponse;
import com.shubh.ecommerce.service.CartService;
import com.shubh.ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private CartService cartService;
    private UserService userService;

    public CartController(CartService cartService,UserService userService) {
        this.cartService=cartService;
        this.userService=userService;
    }

    @GetMapping("/")
    public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws UserException {

        User user=userService.findUserProfileByJwt(jwt);

        Cart cart=cartService.findUserCart(user.getId());

        System.out.println("cart - "+cart.getUser().getEmail());

        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException {

        User user=userService.findUserProfileByJwt(jwt);

        cartService.addCartItem(user.getId(), req);

        ApiResponse res= new ApiResponse("Item Added To Cart Successfully",true);

        return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);

    }


}