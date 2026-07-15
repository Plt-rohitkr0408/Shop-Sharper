package com.shopsharper.auth_service.service.impl;

import com.shopsharper.auth_service.Mapper.ProductMapper;
import com.shopsharper.auth_service.custom.ResourceNotFoundException;
import com.shopsharper.auth_service.dto.request.ProductRequest;
import com.shopsharper.auth_service.dto.response.ResponseProduct;
import com.shopsharper.auth_service.entity.Category;
import com.shopsharper.auth_service.entity.Product;
import com.shopsharper.auth_service.repository.CategoryRepo;
import com.shopsharper.auth_service.repository.ProductRepository;
import com.shopsharper.auth_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper mapper;
    private final ProductRepository productRepository;
    private final CategoryRepo  categoryRepo;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepo  categoryRepo , ProductMapper mapper) {
        this.productRepository = productRepository;
        this.categoryRepo = categoryRepo;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseProduct getProductById(Long id) {

        Product product = productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Product not found with id " + id)
        );

        return  mapper.toResponse(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResponseProduct> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(mapper::toResponse);
    }

    @Override
    public ResponseProduct createProduct(ProductRequest productRequest) {
        Category category = categoryRepo.findById(productRequest.getCategoryId()).orElseThrow(()->
                new ResourceNotFoundException("Category not found with id " + productRequest.getCategoryId()));
       Product product =  mapper.toEntity(productRequest);
       product.setCategory(category);
        Product saveproduct = productRepository.save(product);
        return mapper.toResponse(saveproduct);
    }

    @Override
    public ResponseProduct updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Product not found with id " + id)
        );
        Category category= categoryRepo.findById(productRequest.getCategoryId()).orElseThrow(()->new ResourceNotFoundException("Category not found with id " + productRequest.getCategoryId()));
        product.setName(!product.getName().toLowerCase().equals(productRequest.getName()) ? productRequest.getName() : product.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setCategory(category);
        product.setStock(productRequest.getStock());
        Product saveProduct= productRepository.save(product);
        return mapper.toResponse(saveProduct);
    }

    @Override
    public String deleteProduct(Long id) {

        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return "Product with id " + id + " has been deleted";
        }else{
            throw new ResourceNotFoundException("Product not found with id " + id);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResponseProduct> searchProductByName(String name , Pageable pageable) {
        Page<Product> products = productRepository.findByNameContainingIgnoreCase(name, pageable);
        return products.map(mapper::toResponse);

    }

    @Override
    public Page<ResponseProduct> getProductsByCategory(Long id, Pageable pageable) {
        categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category not found with id " + id));
        Page<Product> products = productRepository.findByCategory_Id(id, pageable);
        return products.map(mapper::toResponse);
    }

    @Override
    public Page<ResponseProduct> getProductByPriceFilter(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {

        if(minPrice.compareTo(maxPrice) > 0) {
            throw new RuntimeException("Minimum price must be greater than or equal to Maximum price");
        }

        Page<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
        return products.map(mapper::toResponse);
    }
}
