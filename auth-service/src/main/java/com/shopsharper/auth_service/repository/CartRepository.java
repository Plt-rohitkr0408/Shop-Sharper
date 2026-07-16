package com.shopsharper.auth_service.repository;

import com.shopsharper.auth_service.entity.Cart;
import com.shopsharper.auth_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUser(User user);
}
