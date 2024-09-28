package com.example.shop4.service;

import com.example.shop4.dto.CouponsDto;

import java.util.List;

public interface CouponsService {
    CouponsDto createCoupons(CouponsDto couponsDto);
    List<CouponsDto> getAllCoupons();
    CouponsDto getCouponsById(Long couponsId);
    CouponsDto updateCoupons(Long couponsId, CouponsDto updatedCoupons);
    void deleteCoupons(Long couponsId);
}