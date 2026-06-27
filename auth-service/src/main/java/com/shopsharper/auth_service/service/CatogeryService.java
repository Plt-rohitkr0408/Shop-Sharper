package com.shopsharper.auth_service.service;

import com.shopsharper.auth_service.dto.request.CategoryRequest;
import com.shopsharper.auth_service.dto.response.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CatogeryService {

    CategoryResponse createCategory(CategoryRequest request);
    Page<CategoryResponse> getCategories(Pageable pageable);
    CategoryResponse getCategoryById(Long id);
    CategoryResponse updateCategory(Long id ,CategoryRequest request);
    CategoryResponse deleteCategoryById(Long id);
}
