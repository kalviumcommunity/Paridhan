package com.shubh.ecommerce.service;

import com.shubh.ecommerce.exceptions.ProductException;
import com.shubh.ecommerce.model.Product;
import com.shubh.ecommerce.model.Review;
import com.shubh.ecommerce.model.User;

import com.shubh.ecommerce.repository.ProductRepository;
import com.shubh.ecommerce.repository.ReviewRepository;
import com.shubh.ecommerce.request.ReviewRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService{

  private ProductService productService;
  private ReviewRepository reviewRepository;
    private ProductRepository productRepository;

    public ReviewServiceImplementation(ProductService productService, ReviewRepository reviewRepository,ProductRepository productRepository) {
        this.productService = productService;
        this.reviewRepository = reviewRepository;
        this.productRepository=productRepository;
    }

    @Override
    public Review createReview(ReviewRequest req, User user) throws ProductException {

        Product product = productService.findProductById(req.getProductId());
        Review review = new Review();
        review.setProduct(product);
        review.setUser(user);
        review.setReview(req.getReview());
        review.setCreatedAt(LocalDateTime.now());
        productRepository.save(product);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return reviewRepository.getAllProductsReview(productId);
    }
}
