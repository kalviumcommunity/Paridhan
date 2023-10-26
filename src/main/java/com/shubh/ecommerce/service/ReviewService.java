package com.shubh.ecommerce.service;

import com.shubh.ecommerce.exceptions.ProductException;
import com.shubh.ecommerce.model.Review;
import com.shubh.ecommerce.model.User;
import com.shubh.ecommerce.request.ReviewRequest;

import java.util.List;

public interface ReviewService {

    public Review createReview(ReviewRequest req, User user) throws ProductException;

    public List<Review> getAllReview(Long productId);
}
