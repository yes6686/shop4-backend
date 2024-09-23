package com.example.shop4.mapper;

import com.example.shop4.dto.MemberCouponDto;
import com.example.shop4.entity.MemberCoupon;

public class MemberCouponMapper {
    public static MemberCouponDto mapToMemberCouponDto(MemberCoupon memberCoupon){
        return new MemberCouponDto(
                memberCoupon.getId(),
                memberCoupon.getMember(),
                memberCoupon.getCoupons(),
                memberCoupon.isUsedCoupon(),
                memberCoupon.getUsedDate(),
                memberCoupon.getCount()
        );
    }

    public static MemberCoupon mapToMemberCoupon(MemberCouponDto memberCouponDto){
        return new MemberCoupon(
                memberCouponDto.getId(),
                memberCouponDto.getMember(),
                memberCouponDto.getCoupons(),
                memberCouponDto.isUsedCoupon(),
                memberCouponDto.getUsedDate(),
                memberCouponDto.getCount()
        );
    }
}
