package com.shubh.ecommerce.service;

import com.shubh.ecommerce.exceptions.ProductException;
import com.shubh.ecommerce.model.Rating;
import com.shubh.ecommerce.model.User;
import com.shubh.ecommerce.request.RatingRequest;

import java.util.List;

public interface RatingService {

    public Rating createRating(RatingRequest req,User user) throws ProductException;

    public List<Rating> getProductsRating(Long productId);
}
