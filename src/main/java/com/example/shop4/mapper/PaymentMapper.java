package com.example.shop4.mapper;

import com.example.shop4.dto.PaymentDto;
import com.example.shop4.entity.Payment;
import com.example.shop4.entity.PaymentStatus;

public class PaymentMapper {
    public static PaymentDto mapToPaymentDto(Payment payment) {
        return new PaymentDto(
                payment.getPaymentId(),
                payment.getTotalPrice(),
                payment.getDiscountPrice(),
                payment.getDeliveryFee(),
                payment.getFinalPrice(),
                payment.getPaymentDate(),
                payment.getPaymentStatus().name(), // Enum to String
                payment.getOrder()
        );
    }

    public static Payment mapToPayment(PaymentDto paymentDto) {
        return new Payment(
                paymentDto.getPaymentId(),
                paymentDto.getTotalPrice(),
                paymentDto.getDiscountPrice(),
                paymentDto.getDeliveryFee(),
                paymentDto.getFinalPrice(),
                paymentDto.getPaymentDate(),
                PaymentStatus.valueOf(paymentDto.getPaymentStatus()), // String to Enum
                paymentDto.getOrder()
        );
    }
}
