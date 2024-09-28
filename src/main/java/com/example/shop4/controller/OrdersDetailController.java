package com.example.shop4.controller;

import com.example.shop4.dto.OrderDetailDto;
import com.example.shop4.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-details")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrdersDetailController {

    private final OrderDetailService orderDetailService;

    // 주문 상세 내역 생성
    @PostMapping
    public ResponseEntity<OrderDetailDto> createOrderDetail(@RequestBody OrderDetailDto orderDetailDto) {
        OrderDetailDto createdOrderDetail = orderDetailService.createOrderDetail(orderDetailDto);
        System.out.println("createdOrderDetail1 = " + createdOrderDetail.getOrderCount());
        System.out.println("createdOrderDetail2 = " + createdOrderDetail.getGoods().getId());
        System.out.println("createdOrderDetail3 = " + createdOrderDetail.getGoods().getUrl());
        System.out.println("createdOrderDetail4 = " + createdOrderDetail.getOrder().getId());
        System.out.println("createdOrderDetail5 = " + createdOrderDetail.getOrder().getOrderDetails());
        return new ResponseEntity<>(createdOrderDetail, HttpStatus.CREATED);
    }

    // 주문 상세 내역 ID로 조회
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDto> getOrderDetailById(@PathVariable Long id) {
        Optional<OrderDetailDto> orderDetail = orderDetailService.getOrderDetailById(id);
        return orderDetail.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 모든 주문 상세 내역 조회
    @GetMapping
    public ResponseEntity<List<OrderDetailDto>> getAllOrderDetails() {
        List<OrderDetailDto> orderDetailsList = orderDetailService.getAllOrderDetails();
        return ResponseEntity.ok(orderDetailsList);
    }

    // 주문 상세 내역 업데이트
    @PutMapping("/{id}")
    public ResponseEntity<OrderDetailDto> updateOrderDetail(
            @PathVariable Long id,
            @RequestBody OrderDetailDto updatedOrderDetailDto) {
        try {
            OrderDetailDto updatedOrderDetail = orderDetailService.updateOrderDetail(id, updatedOrderDetailDto);
            return ResponseEntity.ok(updatedOrderDetail);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 주문 상세 내역 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long id) {
        try {
            orderDetailService.deleteOrderDetail(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 주문 ID로 주문 상세 내역 조회
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderDetailDto>> getOrderDetailsByOrderId(@PathVariable Long orderId) {
        List<OrderDetailDto> orderDetails = orderDetailService.getOrderDetailsByOrderId(orderId);
        if (orderDetails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(orderDetails);
    }

    // 주문 ID로 주문 상세 내역 삭제
    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<Void> deleteOrderDetailsByOrderId(@PathVariable Long orderId) {
        orderDetailService.deleteOrderDetailsByOrderId(orderId);
        return ResponseEntity.noContent().build();
    }

    // 회원 ID로 주문 상세 내역 조회
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<OrderDetailDto>> getOrderDetailsByMemberId(@PathVariable Long memberId) {
        List<OrderDetailDto> orderDetails = orderDetailService.getOrderDetailsByMemberId(memberId);
        if (orderDetails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(orderDetails);
    }
}
