package com.shopsharper.auth_service.service;

import com.shopsharper.auth_service.dto.request.AddToCartRequest;
import com.shopsharper.auth_service.dto.response.CartResponse;

public interface CartService {
    CartResponse addToCart(Long userId, AddToCartRequest addToCartRequest);
    CartResponse getCart(Long userId);
    CartResponse updateCartItem(Long userId, Long productId, Integer quantity);
    String removeProduct(Long userId, Long productId);
    String clearCart(Long userId);
}
