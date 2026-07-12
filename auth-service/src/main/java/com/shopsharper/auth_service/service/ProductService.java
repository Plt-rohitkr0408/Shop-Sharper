package com.shopsharper.auth_service.service;

import com.shopsharper.auth_service.dto.request.ProductRequest;
import com.shopsharper.auth_service.dto.response.ResponseProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
     ResponseProduct getProductById(Long id);
     Page<ResponseProduct> getAllProducts(Pageable pageable);
     ResponseProduct createProduct(ProductRequest  productRequest);
     ResponseProduct updateProduct (Long id, ProductRequest productRequest);
     String deleteProduct(Long id);

     Page<ResponseProduct> searchProductByName(String name ,  Pageable pageable);

}
