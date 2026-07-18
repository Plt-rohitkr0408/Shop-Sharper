package com.shopsharper.auth_service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class OrderPlacedRequest {
    @NotNull(message = "Address Id is required")
    private Long addressId;

    @NotNull(message = "Payment Method is required")
    private String paymentMethod;

    private String couponCode;
}
