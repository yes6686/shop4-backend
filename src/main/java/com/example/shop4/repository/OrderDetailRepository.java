package com.example.shop4.repository;

import com.example.shop4.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrderId(Long orderId); // 주문 ID로 검색하는 메서드
}
