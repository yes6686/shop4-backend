package com.example.shop4.service;

import com.example.shop4.dto.CouponsDto;
import com.example.shop4.dto.MemberCouponDto;

import java.util.List;

public interface MemberCouponService {
    public MemberCouponDto distributeCouponToUser(Long userId, Long couponId);

    MemberCouponDto useCoupon(Long mapId);

    List<MemberCouponDto> getUserCoupons(Long userId);
}
