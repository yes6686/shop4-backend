package com.example.shop4.repository;

import com.example.shop4.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {
    List<Orders> findByMemberId(Long memberId); // 회원 ID로 주문 조회
}
