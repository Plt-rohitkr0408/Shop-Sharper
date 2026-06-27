package com.shopsharper.auth_service.service.impl;

import com.shopsharper.auth_service.Mapper.CategoryMapper;
import com.shopsharper.auth_service.custom.DuplicateResourceException;
import com.shopsharper.auth_service.custom.ResourceNotFoundException;
import com.shopsharper.auth_service.dto.request.CategoryRequest;
import com.shopsharper.auth_service.dto.response.CategoryResponse;
import com.shopsharper.auth_service.entity.Category;
import com.shopsharper.auth_service.repository.CategoryRepo;
import com.shopsharper.auth_service.service.CatogeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CatogeryService {

    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        if(categoryRepo.existsByName(request.getName())){
            throw new DuplicateResourceException("Category already exists");
        }

        CategoryMapper  categoryMapper = new CategoryMapper();
        categoryMapper.toEntity(request);
        Category saved = categoryRepo.save(categoryMapper.toEntity(request));

        return categoryMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategoryResponse> getCategories(Pageable pageable) {

        CategoryMapper categoryMapper = new CategoryMapper();
        return  categoryRepo.findAll(pageable)
                .map(categoryMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getCategoryById(Long id) {
       Category category = categoryRepo.findById(id).orElseThrow(
               ()-> new ResourceNotFoundException("Category Not Found")
       );
       CategoryMapper categoryMapper = new CategoryMapper();
       CategoryResponse categoryResponse =categoryMapper.toResponse(category);
        return categoryResponse;
    }

    @Override
    public CategoryResponse updateCategory(Long id,CategoryRequest request) {
        Category category = categoryRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Category Not Found")
        );

        if(categoryRepo.existsByName(request.getName())){
            throw new DuplicateResourceException("Category already exists");
        }

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        categoryRepo.save(category);
        return new CategoryMapper().toResponse(category);
    }

    @Override
    public CategoryResponse deleteCategoryById(Long id) {
       CategoryResponse categoryResponse = getCategoryById(id);
       categoryRepo.deleteById(id);
        return categoryResponse;
    }
}
