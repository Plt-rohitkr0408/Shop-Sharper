package com.shopsharper.auth_service.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class OrderItemResponse {
    private Long productId;

    private String productName;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal subtotal;
}
