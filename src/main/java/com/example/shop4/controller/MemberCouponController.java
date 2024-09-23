package com.example.shop4.controller;

import com.example.shop4.dto.CouponsDto;
import com.example.shop4.dto.MemberCouponDto;
import com.example.shop4.entity.Coupons;
import com.example.shop4.entity.MemberCoupon;
import com.example.shop4.service.MemberCouponService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Controller
@AllArgsConstructor
@RequestMapping("/api/memberCoupon")
public class MemberCouponController {

    @Autowired
    private MemberCouponService memberCouponService;

    // 쿠폰을 유저에게 발급
    @PostMapping("{userId}/{couponId}")
    public ResponseEntity<MemberCouponDto> distributeCoupon(@PathVariable("userId") Long userId,
                                                            @PathVariable("couponId") Long couponId) {
        MemberCouponDto memberCouponDto = memberCouponService.distributeCouponToUser(userId, couponId);

        return new ResponseEntity<>(memberCouponDto, HttpStatus.CREATED);
    }

    // 유저가 해당 쿠폰 사용
    @PatchMapping("{userCouponMapId}")
    public ResponseEntity<MemberCouponDto> useCoupon(@PathVariable("userCouponMapId") Long mapId) {
        MemberCouponDto memberCouponDto = memberCouponService.useCoupon(mapId);

        return new ResponseEntity<>(memberCouponDto, HttpStatus.OK);
    }

    // 유저의 사용쿠폰 목록 조회
    @GetMapping("{userId}")
    public ResponseEntity<List<MemberCouponDto>> getAvailableCoupons(@PathVariable("userId") Long userId) {
        List<MemberCouponDto> memberCouponDto = memberCouponService.getUserCoupons(userId);
        return new ResponseEntity<>(memberCouponDto, HttpStatus.OK);
    }

}
