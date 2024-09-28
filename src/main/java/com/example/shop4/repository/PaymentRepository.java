package com.example.shop4.repository;

import com.example.shop4.entity.Payment;
import com.example.shop4.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByOrderId(Long orderId); // 주문 ID로 결제 조회
    Payment findByOrderIdAndPaymentStatus(Long orderId, PaymentStatus paymentStatus);

}
