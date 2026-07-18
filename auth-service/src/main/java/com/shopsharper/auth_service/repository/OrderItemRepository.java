package com.shopsharper.auth_service.repository;

import com.shopsharper.auth_service.entity.OrderItem;
import com.shopsharper.auth_service.entity.Product;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    List<OrderItem>  findByOrder(Order order);
    List<OrderItem> findByProduct(Product product);
    boolean existsByOrderAndProduct(Order order, Product product);
}
