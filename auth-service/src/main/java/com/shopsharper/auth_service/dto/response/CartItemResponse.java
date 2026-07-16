package com.shopsharper.auth_service.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CartItemResponse {
    private Long productId;
    private String productName;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal totalPrice;
}
