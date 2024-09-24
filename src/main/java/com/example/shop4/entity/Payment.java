package com.example.shop4.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Long id; // 결제 ID

    @Column(name = "payment_Uid", nullable = false)
    private String paymentUid; // 결제 고유 번호

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice; // 총 금액

    @Column(name = "discount_price", nullable = false)
    private BigDecimal discountPrice; // 할인 금액

    @Column(name = "delivery_fee", nullable = false)
    private BigDecimal deliveryFee; // 배송비

    @Column(name = "final_price", nullable = false)
    private BigDecimal finalPrice; // 최종 결제 금액

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate; // 결제 날짜

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus; // 결제 상태

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod; // 결제 방식

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order; // Orders 테이블과 외래키 연결

}
