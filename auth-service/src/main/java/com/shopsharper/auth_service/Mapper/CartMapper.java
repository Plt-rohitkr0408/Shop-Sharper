package com.shopsharper.auth_service.Mapper;
import com.shopsharper.auth_service.dto.response.CartItemResponse;
import com.shopsharper.auth_service.dto.response.CartResponse;
import com.shopsharper.auth_service.entity.Cart;
import com.shopsharper.auth_service.entity.CartItem;
import com.shopsharper.auth_service.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {

    public CartItemResponse toCartItemResponse(CartItem cartItem) {
        Product product = cartItem.getProduct();
        CartItemResponse cartItemResponse = new CartItemResponse();
        cartItemResponse.setProductId(product.getId());
        cartItemResponse.setProductName(product.getName());
        cartItemResponse.setPrice(product.getPrice());
        cartItemResponse.setQuantity(cartItem.getQuantity());
        cartItemResponse.setTotalPrice(cartItem.getSubtotal());
        return cartItemResponse;
    }

    public List<CartItemResponse> toCartItemResponseList(List<CartItem> list) {
        return list.stream()
                .map(this::toCartItemResponse)
                .toList();
    }

    public CartResponse toCartResponse(Cart cart) {
        return CartResponse.builder()
                .cartId(cart.getId())
                .totalItem(cart.getCartItems().size())
                .totalAmount(cart.getTotalPrice())
                .cartItemResponse(toCartItemResponseList(cart.getCartItems()))
                .build();
    }
}
