package com.example.shop4.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long id; // 주문 ID

    @Column(name = "order_Uid")
    private String orderUid; // 주문 고유 번호

    @Column(name = "buyer_name")
    private String buyerName; // 이름

    @Column(name = "buyer_phone")
    private String buyerPhone; // 전화번호

    @Column(name = "buyer_address")
    private String buyerAddress; // 주소

    @Column(name = "buyer_post_code", length = 100)
    private String buyerPostCode; // 우편번호

    @Column(name = "order_price")
    private BigDecimal orderPrice; // 주문 가격

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus; // 주문 상태

    @Column(name = "order_date")
    private LocalDateTime orderDate; // 주문 날짜

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // Member 테이블과 외래키 연결

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment; // Payment와의 1:1 관계 설정

    // 주문에 포함된 주문 상세 리스트 추가
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;

}
