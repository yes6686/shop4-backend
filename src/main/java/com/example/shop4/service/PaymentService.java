package com.example.shop4.service;

import com.example.shop4.dto.PaymentDto;
import com.example.shop4.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    PaymentDto createPayment(PaymentDto paymentDto);
    Optional<PaymentDto> getPaymentById(Long paymentId);
    List<PaymentDto> getAllPayments();
    PaymentDto updatePayment(Long paymentId, PaymentDto updatedPaymentDto);
    void deletePayment(Long paymentId);
    List<PaymentDto> getPaymentsByMemberId(Long memberId); // 회원 ID로 결제 내역 조회
}
