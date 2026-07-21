package com.shopsharper.auth_service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {
    @NotNull(message = "Order Id is required")
    private Long orderId;
}
