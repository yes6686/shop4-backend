package com.example.shop4.service;

import com.example.shop4.dto.OrdersDto;
import com.example.shop4.entity.Orders;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrdersDto createOrder(OrdersDto order); // 주문 생성

    Optional<OrdersDto> getOrderById(Long orderId); // 주문 조회

    List<OrdersDto> getAllOrders(); // 모든 주문 조회

    OrdersDto updateOrder(Long orderId, OrdersDto updatedOrder); // 주문 수정

    void deleteOrder(Long orderId); // 주문 삭제
}
