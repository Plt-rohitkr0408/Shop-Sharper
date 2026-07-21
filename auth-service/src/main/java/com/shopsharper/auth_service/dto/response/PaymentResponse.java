package com.shopsharper.auth_service.dto.response;


import com.shopsharper.auth_service.enums.PaymentMethod;
import com.shopsharper.auth_service.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {

    private Long paymentId;

    private Long orderId;

    private BigDecimal amount;

    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    private String razorpayOrderId;

}