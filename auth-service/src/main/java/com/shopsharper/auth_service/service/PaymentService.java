package com.shopsharper.auth_service.service;

import com.razorpay.RazorpayException;
import com.shopsharper.auth_service.dto.request.PaymentRequest;
import com.shopsharper.auth_service.dto.request.VerifyPaymentRequest;
import com.shopsharper.auth_service.dto.response.PaymentResponse;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest request) throws RazorpayException;

    String verifyPayment(VerifyPaymentRequest request) throws RazorpayException;

    PaymentResponse getPaymentByOrderId(Long orderId);
}
