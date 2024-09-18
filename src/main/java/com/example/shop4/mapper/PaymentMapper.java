package com.example.shop4.mapper;

import com.example.shop4.dto.PaymentDto;
import com.example.shop4.entity.Payment;
import com.example.shop4.entity.PaymentStatus;

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
                payment.getPaymentStatus(), // Enum to String 변환
                payment.getPaymentMethod(),        // PaymentMethod 추가
                payment.getOrder()
        );
    }

    // PaymentDto -> Payment Entity 변환
    public static Payment mapToPayment(PaymentDto paymentDto) {
        return new Payment(
                paymentDto.getId(),
                paymentDto.getPaymentUid(),
                paymentDto.getTotalPrice(),
                paymentDto.getDiscountPrice(),
                paymentDto.getDeliveryFee(),
                paymentDto.getFinalPrice(),
                paymentDto.getPaymentDate(),
                paymentDto.getPaymentStatus(),
                paymentDto.getPaymentMethod(),
                paymentDto.getOrder()
        );
    }
}
