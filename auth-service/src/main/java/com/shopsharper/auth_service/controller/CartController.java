package com.shopsharper.auth_service.controller;

import com.shopsharper.auth_service.dto.request.AddToCartRequest;
import com.shopsharper.auth_service.dto.response.ApiResponse;
import com.shopsharper.auth_service.dto.response.CartResponse;
import com.shopsharper.auth_service.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<ApiResponse<CartResponse>> addToCart(@PathVariable Long userId,@Valid @RequestBody AddToCartRequest addToCartRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<CartResponse>builder().success(true)
                                .data(cartService.addToCart(userId, addToCartRequest))
                                .message("Cart Add successfully")
                                .build()
                );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<CartResponse>> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(
                ApiResponse.<CartResponse>builder().success(true)
                        .data(cartService.getCart(userId))
                        .message("Cart get successfully")
                        .build()
        );
    }

    @DeleteMapping("/delete/{userId}/product/{productId}")
    public ResponseEntity<ApiResponse<String>> removeCart(@PathVariable Long userId ,@PathVariable Long productId) {
        return ResponseEntity.ok(
                ApiResponse.<String>builder().success(true)
                        .data(cartService.removeProduct(userId, productId))
                        .message("Cart remove successfully")
                        .build()
        );
    }

    @PutMapping("/{userId}/items/{productId}")
    public ResponseEntity<ApiResponse<CartResponse>> updateCartItem(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestParam Integer quantity) {

        CartResponse response =
                cartService.updateCartItem(userId, productId, quantity);

        return ResponseEntity.ok(
                ApiResponse.<CartResponse>builder()
                        .success(true)
                        .message("Cart updated successfully")
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<ApiResponse<String>> clearCart(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Cart cleared successfully")
                        .data(cartService.clearCart(userId))
                        .build()
        );
    }

}
