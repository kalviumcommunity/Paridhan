package com.shubh.service;
import com.shubh.model.Product;
import com.shubh.request.CreateProduct;
import com.shubh.exceptions.ProductException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public  Product createProduct(CreateProduct req);
    public String deleteProduct(long productId) throws ProductException;
    public Product updateProduct(long productId, Product req)  throws ProductException;
    public Product findProductById(long id) throws ProductException;
    public List<Product>findProductByCategory(String Category);
    public Page<Product>getAllProduct(String category,List<String>colour,List<String>size,Integer minPrice,Integer maxPrice,
    Integer minDiscount,String sort,String stock,Integer pagNumber,Integer pagSize);




}
