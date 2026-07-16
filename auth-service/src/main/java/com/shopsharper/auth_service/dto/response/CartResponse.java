package com.shopsharper.auth_service.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CartResponse {
    private Long cartId;
    private BigDecimal totalAmount;
    private Integer totalItem;
    private List<CartItemResponse> cartItemResponse;
}
