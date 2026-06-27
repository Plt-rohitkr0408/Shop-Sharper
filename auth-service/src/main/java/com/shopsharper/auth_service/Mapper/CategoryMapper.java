package com.shopsharper.auth_service.Mapper;

import com.shopsharper.auth_service.dto.request.CategoryRequest;
import com.shopsharper.auth_service.dto.response.CategoryResponse;
import com.shopsharper.auth_service.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryRequest categoryRequest){
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        return category;
    }

    public CategoryResponse toResponse(Category category){
        CategoryResponse categoryResponse = CategoryResponse.builder()
                .id(category.getId())
        .name(category.getName()).description(category.getDescription()).build();
        return categoryResponse;
    }
}
