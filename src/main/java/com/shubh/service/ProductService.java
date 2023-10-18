package com.shubh.service;
import com.shubh.model.Product;
import com.shubh.request.CreateProduct;
import com.shubh.exceptions.ProductException;
import org.springframework.data.domain.Page;
import java.util.List;
public interface ProductService {

    public Product createProduct(CreateProduct req) throws ProductException;

    public String deleteProduct(Long productId) throws ProductException;

    public Product updateProduct(Long productId,Product product)throws ProductException;

    public List<Product> getAllProducts();

    public Product findProductById(Long id) throws ProductException;

    public List<Product> findProductByCategory(String category);

    public List<Product> searchProduct(String query);
    public Page<Product> getAllProduct(String category, List<String>colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount,String sort, String stock, Integer pageNumber, Integer pageSize);




}
