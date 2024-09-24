package com.example.shop4.controller;

import com.example.shop4.dto.PaymentDto;
import com.example.shop4.service.PaymentService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PaymentController {

    private final PaymentService paymentService;

    // 결제 생성
    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {
        System.out.println("paymentDto = " + paymentDto);
        PaymentDto createdPayment = paymentService.createPayment(paymentDto);
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    // 결제 조회 (ID로)
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable Long paymentId) {
        PaymentDto paymentDto = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(paymentDto);
    }

    // 모든 결제 조회
    @GetMapping
    public ResponseEntity<List<PaymentDto>> getAllPayments() {
        List<PaymentDto> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    // 결제 수정 (ID로)
    @PutMapping("/{paymentId}")
    public ResponseEntity<PaymentDto> updatePayment(@PathVariable Long paymentId, @RequestBody PaymentDto paymentDto) {
        PaymentDto updatedPayment = paymentService.updatePayment(paymentId, paymentDto);
        return ResponseEntity.ok(updatedPayment);
    }

    // 결제 삭제 (ID로)
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable Long paymentId) {
        paymentService.deletePayment(paymentId);
        return ResponseEntity.ok("Payment deleted successfully.");
    }

    // 회원 ID로 결제 내역 조회
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<PaymentDto>> getPaymentsByMemberId(@PathVariable Long memberId) {
        List<PaymentDto> payments = paymentService.getPaymentsByMemberId(memberId);
        return ResponseEntity.ok(payments);
    }
}
