package com.example.shop4.repository;

import com.example.shop4.entity.Coupons;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponsRepository extends JpaRepository<Coupons, Long> {
}
