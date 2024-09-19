package com.example.shop4.controller;

import com.example.shop4.dto.OrdersDto;
import com.example.shop4.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;

    // 주문 생성
    @PostMapping
    public ResponseEntity<OrdersDto> createOrder(@RequestBody OrdersDto ordersDto) {
        OrdersDto createdOrder = orderService.createOrder(ordersDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    // 주문 ID로 조회
    @GetMapping("/{orderId}")
    public ResponseEntity<OrdersDto> getOrderById(@PathVariable Long orderId) {
        Optional<OrdersDto> order = orderService.getOrderById(orderId);
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 모든 주문 조회
    @GetMapping
    public ResponseEntity<List<OrdersDto>> getAllOrders() {
        List<OrdersDto> ordersList = orderService.getAllOrders();
        return ResponseEntity.ok(ordersList);
    }

    // 주문 업데이트
    @PutMapping("/{orderId}")
    public ResponseEntity<OrdersDto> updateOrder(
            @PathVariable Long orderId,
            @RequestBody OrdersDto updatedOrderDto) {
        try {
            OrdersDto updatedOrder = orderService.updateOrder(orderId, updatedOrderDto);
            return ResponseEntity.ok(updatedOrder);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 주문 삭제
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        try {
            orderService.deleteOrder(orderId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
