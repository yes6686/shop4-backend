package com.example.shop4.repository;

import com.example.shop4.entity.MemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long> {
    // userId 필드로 MemberCoupon 찾기
    @Query("SELECT mc FROM MemberCoupon mc WHERE mc.member.id = :userId")
    List<MemberCoupon> findByUserId(@Param("userId") Long userId);
}
