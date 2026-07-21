package com.shopsharper.auth_service.service.impl;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import com.shopsharper.auth_service.Mapper.PaymentMapper;
import com.shopsharper.auth_service.custom.ResourceNotFoundException;
import com.shopsharper.auth_service.dto.request.PaymentRequest;
import com.shopsharper.auth_service.dto.request.VerifyPaymentRequest;
import com.shopsharper.auth_service.dto.response.PaymentResponse;
import com.shopsharper.auth_service.entity.Order;
import com.shopsharper.auth_service.entity.Payment;
import com.shopsharper.auth_service.enums.OrderStatus;
import com.shopsharper.auth_service.enums.PaymentMethod;
import com.shopsharper.auth_service.enums.PaymentStatus;
import com.shopsharper.auth_service.repository.OrderRepository;
import com.shopsharper.auth_service.repository.PaymentRepository;
import com.shopsharper.auth_service.service.PaymentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private  final OrderRepository orderRepository;
    private final RazorpayClient  razorpayClient;
    @Value("${razorpay.secret.id}")
    private static String secret;

    public PaymentServiceImpl(PaymentRepository paymentRepository,  PaymentMapper paymentMapper, OrderRepository orderRepository, RazorpayClient razorpayClient) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.orderRepository = orderRepository;
        this.razorpayClient = razorpayClient;
    }

    @Override
    public PaymentResponse createPayment(PaymentRequest request) throws RazorpayException {
        Order order = orderRepository.findById(request.getOrderId()).orElseThrow(()->new ResourceNotFoundException("Order not found"));
        Payment payment = Payment.builder()
                .order(order)
                .paymentAmount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod())
                .build();

        if(order.getPaymentMethod() == PaymentMethod.CASH_ON_DELIVERY){
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
        }else{
            payment.setPaymentStatus(PaymentStatus.PENDING);
        }
        com.razorpay.Order order1 = createOrder(payment);
        payment.setRazorpayOrderId(order1.get("id"));
       Payment savePayment= paymentRepository.save(payment);
        return paymentMapper.toPaymentResponse(savePayment);
    }


    private com.razorpay.Order createOrder(Payment payment1) throws RazorpayException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("currency", "INR");
        jsonObject.put("amount", payment1.getPaymentAmount().multiply(BigDecimal.valueOf(100)).intValue());
        jsonObject.put("receipt", "receipt_" + payment1.getOrder().getId());

        return razorpayClient.orders.create(jsonObject);
    }

    @Override
    public String verifyPayment(VerifyPaymentRequest verifyPaymentRequest) throws RazorpayException {
        JSONObject attributes = new JSONObject();
        attributes.put(
                "razorpay_order_id",
                verifyPaymentRequest.getRazorpayPaymentId()
        );
        attributes.put(
                "razorpay_payment_id",
                verifyPaymentRequest.getRazorpayPaymentId()
        );
        attributes.put(
                "razorpay_signature",
                verifyPaymentRequest.getRazorpaySignature()
        );

        boolean verify = Utils.verifyPaymentLink(attributes,secret);

        if(!verify){
            throw new IllegalStateException("Invalid Signature");
        }

        Payment payment = paymentRepository.findByRazorpayOrderId(verifyPaymentRequest.getRazorpayOrderId()).orElse(null);

        payment.setRazorpayOrderId(verifyPaymentRequest.getRazorpayOrderId());
        payment.setRazorpayPaymentId(verifyPaymentRequest.getRazorpayPaymentId());
        payment.setSignature(verifyPaymentRequest.getRazorpaySignature());
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        payment.setPaymentAt(LocalDateTime.now());
        paymentRepository.save(payment);

        Order order =  payment.getOrder();
        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);
        return "Payment verified Successfully ";
    }

    @Override
    public PaymentResponse getPaymentByOrderId(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order not found"));

        Payment  payment = paymentRepository.findByOrder(order).orElseThrow(()->new ResourceNotFoundException("Payment not found"));

        return paymentMapper.toPaymentResponse(payment);

    }
}
