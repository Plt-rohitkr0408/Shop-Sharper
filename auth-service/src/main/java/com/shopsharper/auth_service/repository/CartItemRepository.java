package com.shopsharper.auth_service.repository;

import com.shopsharper.auth_service.entity.Cart;
import com.shopsharper.auth_service.entity.CartItem;
import com.shopsharper.auth_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
    void deleteByCartAndProduct(Cart cart, Product product);
    List<CartItem> findByCart(Cart cart);
    boolean existsByCartAndProduct(Cart cart, Product product);
}
