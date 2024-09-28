package com.example.shop4.service;

import com.example.shop4.dto.OrderDetailDto;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
    OrderDetailDto createOrderDetail(OrderDetailDto orderDetailDto);
    Optional<OrderDetailDto> getOrderDetailById(Long id);
    List<OrderDetailDto> getAllOrderDetails();
    OrderDetailDto updateOrderDetail(Long id, OrderDetailDto updatedOrderDetailDto);
    void deleteOrderDetail(Long id);

    // 주문 ID로 관리
    List<OrderDetailDto> getOrderDetailsByOrderId(Long orderId); // 주문 ID로 조회
    void deleteOrderDetailsByOrderId(Long orderId); // 주문 ID로 삭제
    List<OrderDetailDto> getOrderDetailsByMemberId(Long memberId); // 회원 ID로 조회
}
