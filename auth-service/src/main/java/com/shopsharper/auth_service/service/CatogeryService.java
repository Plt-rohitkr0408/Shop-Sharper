package com.shopsharper.auth_service.service;

import com.shopsharper.auth_service.dto.request.CategoryRequest;
import com.shopsharper.auth_service.dto.response.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CatogeryService {

    CategoryResponse createCategory(CategoryRequest request);
    Page<CategoryResponse> getCategories(org.springframework.data.domain.Pageable pageable);
    CategoryResponse getCategoryById(Long id);
    CategoryResponse updateCategory(Long id ,CategoryRequest request);
    CategoryResponse deleteCategoryById(Long id);
    Page<CategoryResponse> searchCategory(String name , Pageable pageable);
}
