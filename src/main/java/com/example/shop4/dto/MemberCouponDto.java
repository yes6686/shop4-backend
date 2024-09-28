package com.example.shop4.dto;

import com.example.shop4.entity.Coupons;
import com.example.shop4.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberCouponDto {
    private Long id;
    private Member member;
    private Coupons coupons;
    private boolean usedCoupon;
    private LocalDate usedDate;
    private Long count;
}
