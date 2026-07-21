package com.shopsharper.auth_service.repository;

import com.shopsharper.auth_service.entity.Order;
import com.shopsharper.auth_service.entity.Payment;
import com.shopsharper.auth_service.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByOrder(Order order);

    Optional<Payment> findByRazorpayOrderId(String razorpayOrderId);

    Optional<Payment> findByRazorpayPaymentId(String razorpayPaymentId);

    List<Payment> findByPaymentStatus(PaymentStatus paymentStatus);

}
