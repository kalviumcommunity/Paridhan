package com.shubh.service;

import com.shubh.exceptions.ProductException;
import com.shubh.model.Category;
import com.shubh.model.Product;
import com.shubh.repository.CategoryRepository;
import com.shubh.repository.ProductRepository;
//import com.shubh.repository.UserRepository;
import com.shubh.request.CreateProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class ProductServiceImplementation implements ProductService{

    private ProductRepository productRepository;
//    private UserRepository userRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceImplementation(ProductRepository productRepository,  CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
//        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(CreateProduct req) {

        Category firstLevel = categoryRepository.findByName(req.getFirstCategory());
        if(firstLevel==null){
            Category firstCategory = new Category();
            firstCategory.setName(req.getFirstCategory());
            firstCategory.setLevel(1);
            firstCategory=categoryRepository.save(firstCategory);
        }
        Category secondLevel = categoryRepository.findByNameAndParent(req.getSecondCategory(),firstLevel.getName());
        if(secondLevel==null){
            Category secondCategory = new Category();
            secondCategory.setName(req.getSecondCategory());
            secondCategory.setLevel(2);
            secondCategory=categoryRepository.save(secondCategory);
        }
        Category thirdLevel = categoryRepository.findByNameAndParent(req.getThirdCategory(),secondLevel.getName());
        if(thirdLevel==null){
            Category thirdCategory = new Category();
            thirdCategory.setName(req.getThirdCategory());
            thirdCategory.setLevel(3);
            thirdCategory=categoryRepository.save(thirdCategory);

        }
        Product product = new Product();
        product.setTitle(req.getTitle());
        product.setColour(req.getColour());
        product.setDescription(req.getDescription());
        product.setDiscountPrice(req.getDiscountPrice());
        product.setPresentDiscount(req.getPresentDiscount());
        product.setImage(req.getImage());
        product.setBrand(req.getBrand());
        product.setPrice(req.getPrice());
        product.setSizes(req.getSize());
        product.setQuantity(req.getQuantity());
        product.setCategory(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Override
    public String deleteProduct(long productId) throws ProductException {
        Product product = findProductById(productId);
        product.getSizes().clear();;
        productRepository.delete(product);
        return "product deleted successfully";
    }

    @Override
    public Product updateProduct(long productId, Product req) throws ProductException {
        Product product = findProductById(productId);
        if(req.getQuantity()!=0){
            product.setQuantity(req.getQuantity());

        }
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(long id) throws ProductException {
        Optional<Product>opt = productRepository.findById(id);
        if(opt.isPresent()){
            return  opt.get();
        }

       throw new ProductException("Product not found with id-"+ id);
    }

    @Override
    public List<Product> findProductByCategory(String Category) {
        return null;
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> colour, List<String> size, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pagNumber, Integer pagSize) {
        Pageable pageable = PageRequest.of(pagNumber,pagSize);
        List<Product>products = productRepository.filterProducts(category,minPrice,maxPrice,minDiscount,sort);
        if(!colour.isEmpty()){
            products=products.stream().filter(p->colour.stream().anyMatch(c->c.equalsIgnoreCase(p.getColour()))).collect(Collectors.toList());
        }
        if(stock!=null){
            if(stock.equals("in_stock")) {
                products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
            } else if (stock.equals("out_of_stock")) {

                products=products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
            }
        }
        int startIndex = (int)pageable.getOffset();
        int endIndex = Math.min(startIndex+pageable.getPageSize(),products.size());
        List<Product>pageContent = products.subList(startIndex,endIndex);
        Page<Product>filteredProducts=new PageImpl<>(pageContent,pageable,products.size());

        return filteredProducts;
    }
}
