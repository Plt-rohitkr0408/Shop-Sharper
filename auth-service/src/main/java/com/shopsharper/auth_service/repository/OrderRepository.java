package com.shopsharper.auth_service.repository;

import com.shopsharper.auth_service.entity.Order;
import com.shopsharper.auth_service.entity.User;
import com.shopsharper.auth_service.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order,Long> {
    Page<Order> findByUser(User user, Pageable pageable);
    Page<Order> findByStatus(OrderStatus status, Pageable pageable);
    Page<Order> findByUserAndStatus(User user, OrderStatus status, Pageable pageable);
}
