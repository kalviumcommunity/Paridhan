package com.shubh.repository;

import com.shubh.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p" +
    "Where(p.category.name=:category OR :category='' )" +
    "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (P.discountedPrice BETWEEN :minPrice AND :maxPrice))"+
    "AND (:minDiscount IS NULL OR p.presentDiscount>=:minDiscount)"+
    "ORDER BY"+
    "CASE WHEN :sort='price_low'THEN P.discountPrice END ASC,"+
            "CASE WHEN :sort='price_high'THEN P.discountPrice END DESC")
    public List<Product>filterProducts(@Param("category")String category,
                                       @Param("minPrice") Integer minPrice,
                                       @Param("maxPrice")Integer maxPrice,
                                       @Param("minDiscount")Integer minDiscount,
                                       @Param("sort")String sort);
}