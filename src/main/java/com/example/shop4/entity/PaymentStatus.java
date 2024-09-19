package com.example.shop4.entity;

public enum PaymentStatus {
    PENDING,   // 결제 대기 중
    COMPLETED, // 결제 완료
    FAILED,    // 결제 실패
    REFUNDED   // 결제 환불 완료
}
