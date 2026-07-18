package com.shopsharper.auth_service.dto.response;

import com.shopsharper.auth_service.enums.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Long orderId;

    private OrderStatus status;

    private LocalDateTime orderDate;

    private BigDecimal totalAmount;

    private String paymentMethod;

    private List<OrderItemResponse> items;
}
