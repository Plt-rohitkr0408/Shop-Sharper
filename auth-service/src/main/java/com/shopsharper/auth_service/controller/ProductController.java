package com.shopsharper.auth_service.controller;


import com.shopsharper.auth_service.dto.request.ProductRequest;
import com.shopsharper.auth_service.dto.response.ApiResponse;
import com.shopsharper.auth_service.dto.response.ResponseProduct;
import com.shopsharper.auth_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ResponseProduct>>>  getAllProducts(
            @PageableDefault(size = 5,sort = "name")
            Pageable pageable) {
      Page<ResponseProduct> responseProduct =  productService.getAllProducts(pageable);
        return ResponseEntity.ok(ApiResponse.<Page<ResponseProduct>>builder()
                .success(true)
                .message("Success")
                .data(responseProduct)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ResponseProduct>> getProductById( @PathVariable Long id){
       ResponseProduct responseProduct =productService.getProductById(id);
       return ResponseEntity.ok(ApiResponse.<ResponseProduct>builder()
               .success(true)
               .data(responseProduct)
               .message("Success")
               .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ResponseProduct>> createProduct(@Valid @RequestBody ProductRequest productRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<ResponseProduct>builder()
                .success(true)
                .message("product created successfully")
                .data(productService.createProduct(productRequest))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ResponseProduct>> updateProduct(@PathVariable Long id ,@Valid @RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(ApiResponse.<ResponseProduct>builder()
                        .success(true)
                        .message("Product updated successfully")
                        .data(productService.updateProduct(id,productRequest))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.<String>builder()
                        .success(true)
                        .data(productService.deleteProduct(id))
                        .message("product deleted successfully")
                .build());
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<ResponseProduct>>> searchProducts(
            @RequestParam String keyword,
            @PageableDefault(size = 5, sort = "name") Pageable pageable) {

        Page<ResponseProduct> response =
                productService.searchProductByName(keyword, pageable);

        return ResponseEntity.ok(
                ApiResponse.<Page<ResponseProduct>>builder()
                        .success(true)
                        .message("Products fetched successfully")
                        .data(response)
                        .build()
        );
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ApiResponse<Page<ResponseProduct>>> getProductsByCategory( @PathVariable Long id, @PageableDefault(size = 5 ,sort = "name") Pageable pageable) {
       Page<ResponseProduct> responseProducts = productService.getProductsByCategory(id, pageable);
        return ResponseEntity.ok(ApiResponse.<Page<ResponseProduct>>builder()
                .success(true)
                        .message("Products fetched successfully")
                .data(responseProducts).build());
    }

    @GetMapping("/price")
    public ResponseEntity<ApiResponse<Page<ResponseProduct>>> getProductsByPriceFilter(@RequestParam BigDecimal minPrice ,@RequestParam BigDecimal maxPrice , @PageableDefault(size = 5,sort = "price") Pageable pageable) {
      Page<ResponseProduct> responseProducts =  productService.getProductByPriceFilter( minPrice, maxPrice, pageable);
      return ResponseEntity.ok(
              ApiResponse.<Page<ResponseProduct>>builder()
                      .success(true)
                      .message("Products fetched successfully")
                      .data(responseProducts)
                      .build()
      );
    }
}
