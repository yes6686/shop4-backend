package com.example.shop4.service.impl;

import com.example.shop4.dto.CouponsDto;
import com.example.shop4.entity.Coupons;
import com.example.shop4.exception.ResourceNotFoundException;
import com.example.shop4.mapper.CouponsMapper;
import com.example.shop4.mapper.GoodsMapper;
import com.example.shop4.repository.CouponsRepository;
import com.example.shop4.service.CouponsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponsServiceImpl implements CouponsService {

    @Autowired
    private CouponsRepository couponsRepository;

    @Override
    public CouponsDto createCoupons(CouponsDto couponsDto) {
        Coupons coupons = CouponsMapper.mapToCoupons(couponsDto);
        Coupons savedCoupons = couponsRepository.save(coupons);
        return CouponsMapper.mapToCouponsDto(savedCoupons);
    }

    @Override
    public List<CouponsDto> getAllCoupons() {
        List<Coupons> coupons = couponsRepository.findAll();
        return coupons.stream().map((coupon)->CouponsMapper.mapToCouponsDto(coupon))
                .collect(Collectors.toList());
    }

    @Override
    public CouponsDto getCouponsById(Long couponsId) {
        Coupons coupons = couponsRepository.findById(couponsId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Coupon is not exists with given id : " + couponsId)
        );
        return CouponsMapper.mapToCouponsDto(coupons);
    }

    @Override
    public CouponsDto updateCoupons(Long couponsId, CouponsDto updatedCoupons) {
        Coupons coupons = couponsRepository.findById(couponsId).orElseThrow(
                () -> new ResourceNotFoundException("Coupon is not exists with given id : " + couponsId)
        );
        coupons.patch(updatedCoupons);

        Coupons updatedCouponsObj = couponsRepository.save(coupons);

        return CouponsMapper.mapToCouponsDto(updatedCouponsObj);
    }

    @Override
    public void deleteCoupons(Long couponsId) {
        Coupons coupons = couponsRepository.findById(couponsId).orElseThrow(
                ()-> new ResourceNotFoundException("Coupons id not exists with given id : " + couponsId)
        );
        couponsRepository.deleteById(couponsId);
    }
}
