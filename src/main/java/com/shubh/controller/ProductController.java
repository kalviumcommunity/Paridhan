package com.shubh.controller;

import com.shubh.model.Product;
import com.shubh.request.CreateProduct;
import com.shubh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product>createProduct(@RequestBody CreateProduct req){
        Product product= productService.createProduct(req);
    return  new ResponseEntity<Product>(product, HttpStatus.CREATED);

    }


}
