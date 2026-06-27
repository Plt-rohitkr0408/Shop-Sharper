package com.shopsharper.auth_service.repository;

import com.shopsharper.auth_service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo  extends JpaRepository<Category,Long> {
    boolean existsByName(String name);
}
