package com.example.shop4.dto;

import com.example.shop4.entity.Orders;
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
    private Long paymentId; // 결제 ID
    private BigDecimal totalPrice; // 총 금액
    private BigDecimal discountPrice; // 할인 금액
    private BigDecimal deliveryFee; // 배송비
    private BigDecimal finalPrice; // 최종 결제 금액
    private LocalDateTime paymentDate; // 결제 생성일
    private String paymentStatus; // 결제 상태
    private Orders order; // 주문 정보
}
