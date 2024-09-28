package com.example.shop4.mapper;

import com.example.shop4.dto.PaymentDto;
import com.example.shop4.entity.Orders;
import com.example.shop4.entity.Payment;

public class PaymentMapper {
    public static PaymentDto mapToPaymentDto(Payment payment) {
        return new PaymentDto(
                payment.getId(),
                payment.getPaymentUid(),
                payment.getTotalPrice(),
                payment.getDiscountPrice(),
                payment.getDeliveryFee(),
                payment.getFinalPrice(),
                payment.getPaymentDate(),
                payment.getPaymentStatus(),
                payment.getPaymentMethod(),
                payment.getOrder() != null ? payment.getOrder().getId() : null // 주문 ID를 가져옴
        );
    }

    // PaymentDto -> Payment Entity 변환
    public static Payment mapToPayment(PaymentDto paymentDto, Orders orders) {
        Payment payment = new Payment(
                paymentDto.getId(),
                paymentDto.getPaymentUid(),
                paymentDto.getTotalPrice(),
                paymentDto.getDiscountPrice(),
                paymentDto.getDeliveryFee(),
                paymentDto.getFinalPrice(),
                paymentDto.getPaymentDate(),
                paymentDto.getPaymentStatus(),
                paymentDto.getPaymentMethod(),
                orders
        );

        return payment;
    }
}
