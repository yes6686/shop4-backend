package com.example.shop4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MemberCoupon {
    // 매핑테이블 기본키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Member 테이블의 외래키 참조
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    // 쿠폰 테이블의 외래키 참조
    @ManyToOne
    @JoinColumn(name = "coupon_Id", nullable = false)
    private Coupons coupons;

    // 사용 여부
    @Column(nullable = false)
    private boolean usedCoupon;

    // 사용 날짜
    @Column
    private LocalDate usedDate;

    // 유저가 가지고 있는 쿠폰 개수
    @Column
    private Long count;
}
