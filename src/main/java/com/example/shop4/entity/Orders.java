package com.example.shop4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId; // 주문 ID

    @Column(name = "buyer_name", nullable = false)
    private String buyerName; // 구매자 이름

    @Column(name = "buyer_phone", nullable = false)
    private String buyerPhone; // 구매자 전화번호

    @Column(name = "buyer_address", nullable = false)
    private String buyerAddress; // 구매자 주소

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus; // 주문 상태

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate; // 주문 날짜

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member; // Member 테이블과 외래키 연결

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment; // Payment와의 1:1 관계 설정
}
