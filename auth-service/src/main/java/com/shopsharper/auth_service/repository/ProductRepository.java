package com.shopsharper.auth_service.repository;

import com.shopsharper.auth_service.entity.Product;
import com.shopsharper.auth_service.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;


public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByNameContainingIgnoreCase(String name ,  Pageable pageable);
    Page<Product> findByCategory_Id(Long id, Pageable pageable);
    Page<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
    Page<Product> findByStatus(ProductStatus status, Pageable pageable);
}
