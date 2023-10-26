package com.shubh.ecommerce.service;

import com.shubh.ecommerce.exceptions.ProductException;
import com.shubh.ecommerce.model.Product;
import com.shubh.ecommerce.model.Rating;
import com.shubh.ecommerce.model.User;
import com.shubh.ecommerce.repository.RatingRepository;
import com.shubh.ecommerce.request.RatingRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImplementation implements RatingService{

    private RatingRepository ratingRepository;
    private ProductService productService;

    public RatingServiceImplementation(RatingRepository ratingRepository,ProductService productService) {
        this.ratingRepository=ratingRepository;
        this.productService=productService;
    }

    @Override
    public Rating createRating(RatingRequest req,User user) throws ProductException {

        Product product=productService.findProductById(req.getProductId());

        Rating rating=new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {

        return ratingRepository.getAllProductsRating(productId);
    }



}
