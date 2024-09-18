package com.example.shop4.controller;

import com.example.shop4.dto.PaymentDto;
import com.example.shop4.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // 결제 생성
    @PostMapping
    public PaymentDto createPayment(@RequestBody PaymentDto paymentDto) {
        return paymentService.createPayment(paymentDto);
    }

    // 결제 조회 (ID로)
    @GetMapping("/{paymentId}")
    public PaymentDto getPaymentById(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }

    // 모든 결제 조회
    @GetMapping
    public List<PaymentDto> getAllPayments() {
        return paymentService.getAllPayments();
    }

    // 결제 수정 (ID로)
    @PutMapping("/{paymentId}")
    public PaymentDto updatePayment(@PathVariable Long paymentId, @RequestBody PaymentDto paymentDto) {
        return paymentService.updatePayment(paymentId, paymentDto);
    }

    // 결제 삭제 (ID로)
    @DeleteMapping("/{paymentId}")
    public void deletePayment(@PathVariable Long paymentId) {
        paymentService.deletePayment(paymentId);
    }

    // 회원 ID로 결제 내역 조회
    @GetMapping("/member/{memberId}")
    public List<PaymentDto> getPaymentsByMemberId(@PathVariable Long memberId) {
        return paymentService.getPaymentsByMemberId(memberId);
    }
}
