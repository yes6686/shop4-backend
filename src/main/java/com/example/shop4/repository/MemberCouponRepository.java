package com.example.shop4.repository;

import com.example.shop4.entity.MemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long> {
    // userId 필드로 MemberCoupon 찾기
    List<MemberCoupon> findByMemberId(Long memberId);
}
