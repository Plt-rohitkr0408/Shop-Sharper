package com.shopsharper.auth_service.controller;

import com.shopsharper.auth_service.dto.request.CategoryRequest;
import com.shopsharper.auth_service.dto.response.ApiResponse;
import com.shopsharper.auth_service.dto.response.CategoryResponse;
import com.shopsharper.auth_service.service.impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
       CategoryResponse categoryResponse= categoryService.createCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<CategoryResponse>builder()
                        .message("Category create successfully")
                        .success(true)
                        .data(categoryResponse)
                        .build()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<CategoryResponse>builder()
                        .message("Category fetched Successfully")
                        .data(categoryService.getCategoryById(id))
                        .success(true)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CategoryResponse>>> getCategories(
            @PageableDefault(size = 5,sort = "name")
            Pageable pageable
    ){
       return  ResponseEntity.ok(
               ApiResponse.<Page<CategoryResponse>>builder()
                       .success(true)
                       .message("Categories fetched successfully")
                       .data(categoryService.getCategories(pageable))
                       .build()
       );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(@PathVariable Long id,  @Valid @RequestBody CategoryRequest request){
        return ResponseEntity.ok(
                ApiResponse.<CategoryResponse>builder()
                        .success(true)
                        .message("Category update successfully")
                        .data(categoryService.updateCategory(id,request))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> deleteCategory(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<CategoryResponse>builder()
                        .message("Category delete successfully")
                        .success(true)
                        .data(categoryService.deleteCategoryById(id))
                .build()
        );
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<CategoryResponse>>> search(@RequestParam String name , Pageable pageable){
        return ResponseEntity.ok(
                ApiResponse.<Page<CategoryResponse>>builder()
                        .success(true)
                        .message("Search category successfully")
                        .data(categoryService.searchCategory(name,pageable))
                        .build()
        );
    }
}
