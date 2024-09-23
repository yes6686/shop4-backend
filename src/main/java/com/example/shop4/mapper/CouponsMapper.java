package com.example.shop4.mapper;

import com.example.shop4.dto.CouponsDto;
import com.example.shop4.entity.Coupons;

public class CouponsMapper {
    public static CouponsDto mapToCouponsDto(Coupons coupons){
        return new CouponsDto(
                coupons.getId(),
                coupons.getName(),
                coupons.getDiscount(),
                coupons.getCreateDate(),
                coupons.getLimitDate(),
                coupons.getCount()
        );
    }

    public static Coupons mapToCoupons(CouponsDto couponsDto){
        return new Coupons(
                couponsDto.getId(),
                couponsDto.getName(),
                couponsDto.getDiscount(),
                couponsDto.getCreateDate(),
                couponsDto.getLimitDate(),
                couponsDto.getCount()
        );
    }
}
