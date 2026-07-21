package com.shopsharper.auth_service.controller;

import com.razorpay.RazorpayException;
import com.shopsharper.auth_service.dto.request.PaymentRequest;
import com.shopsharper.auth_service.dto.request.VerifyPaymentRequest;
import com.shopsharper.auth_service.dto.response.ApiResponse;
import com.shopsharper.auth_service.dto.response.PaymentResponse;
import com.shopsharper.auth_service.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vi/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(
            PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PaymentResponse>> createPayment(

            @Valid
            @RequestBody
            PaymentRequest request)

            throws RazorpayException {

        PaymentResponse response =
                paymentService.createPayment(request);

        return ResponseEntity.ok(

                ApiResponse.<PaymentResponse>builder()

                        .success(true)

                        .message("Payment initiated")

                        .data(response)

                        .build()

        );

    }

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<String>> verify(

            @RequestBody VerifyPaymentRequest request) throws RazorpayException {

        String message =
                paymentService.verifyPayment(
                        request
                );

        return ResponseEntity.ok(

                ApiResponse.<String>builder()

                        .success(true)

                        .message(message)

                        .data(message)

                        .build()

        );

    }


    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<PaymentResponse>>
    getPayment(

            @PathVariable Long orderId){

        PaymentResponse response =
                paymentService.getPaymentByOrderId(orderId);

        return ResponseEntity.ok(

                ApiResponse.<PaymentResponse>builder()

                        .success(true)

                        .message("Success")

                        .data(response)

                        .build()

        );

    }
}

