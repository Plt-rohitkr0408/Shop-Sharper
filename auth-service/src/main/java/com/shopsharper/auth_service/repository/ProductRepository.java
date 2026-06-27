package com.shopsharper.auth_service.repository;

import com.shopsharper.auth_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product,Long> {

}
