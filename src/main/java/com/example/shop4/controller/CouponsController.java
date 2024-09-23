package com.example.shop4.controller;

import com.example.shop4.dto.CouponsDto;
import com.example.shop4.entity.Coupons;
import com.example.shop4.service.CouponsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

import java.util.List;

@CrossOrigin("*")
@Controller
@AllArgsConstructor
@RequestMapping("/api/coupons")
public class CouponsController {

    @Autowired
    private CouponsService couponsService;

    @PostMapping
    public ResponseEntity<CouponsDto> createGoods(@RequestBody CouponsDto couponsDto) {
        CouponsDto savedCoupons = couponsService.createCoupons(couponsDto);
        return new ResponseEntity<>(savedCoupons, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CouponsDto>> getAllCoupons() {
        List<CouponsDto> coupons = couponsService.getAllCoupons();
        return ResponseEntity.ok(coupons);
    }

    @GetMapping("{id}")
    public ResponseEntity<CouponsDto> getCouponById(@PathVariable("id") Long couponsId) {
        CouponsDto couponsDto = couponsService.getCouponsById(couponsId);
        return ResponseEntity.ok(couponsDto);
    }

    @PatchMapping("{id}")
    public ResponseEntity<CouponsDto> updateCoupons(@PathVariable Long id,
                                                   @RequestBody CouponsDto updatedCoupons) {
        CouponsDto couponsDto = couponsService.updateCoupons(id,updatedCoupons);
        return ResponseEntity.ok(couponsDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCoupons(@PathVariable("id") Long couponsid) {
        couponsService.deleteCoupons(couponsid);
        return ResponseEntity.ok("Coupons deleted successfully...!");
    }
}
