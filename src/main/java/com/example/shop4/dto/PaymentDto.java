package com.example.shop4.dto;

import com.example.shop4.entity.Orders;
import com.example.shop4.entity.PaymentMethod;
import com.example.shop4.entity.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PaymentDto {
    private Long id; // 결제 ID
    private String paymentUid; // 결제 고유 번호
    private BigDecimal totalPrice; // 총 금액
    private BigDecimal discountPrice; // 할인 금액
    private BigDecimal deliveryFee; // 배송비
    private BigDecimal finalPrice; // 최종 결제 금액
    private LocalDateTime paymentDate; // 결제 생성일
    private PaymentStatus paymentStatus; // 결제 상태
    private PaymentMethod paymentMethod; // 결제 방식
    private Orders order; // 주문 정보
}
