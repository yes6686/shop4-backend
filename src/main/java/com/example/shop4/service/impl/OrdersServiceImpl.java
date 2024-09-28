package com.example.shop4.service.impl;

import com.example.shop4.dto.OrdersDto;
import com.example.shop4.entity.*;
import com.example.shop4.exception.ResourceNotFoundException;
import com.example.shop4.mapper.OrdersMapper;
import com.example.shop4.repository.CartRepository;
import com.example.shop4.repository.MemberRepository;
import com.example.shop4.repository.OrderDetailRepository;
import com.example.shop4.repository.OrdersRepository;
import com.example.shop4.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrderService {

    private final OrdersRepository ordersRepository;
    private final MemberRepository memberRepository;

    @Override
    public OrdersDto createOrder(OrdersDto ordersDto) {
        Member member = memberRepository.findById(ordersDto.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        Orders orders = OrdersMapper.mapToOrders(ordersDto, member);
        Orders savedOrder = ordersRepository.save(orders); // 주문 저장
        return OrdersMapper.mapToOrdersDto(savedOrder);
    }

    @Override
    public Optional<OrdersDto> getOrderById(Long orderId) {
        return ordersRepository.findById(orderId)
                .map(OrdersMapper::mapToOrdersDto); // 주문 ID로 조회 후 DTO 변환
    }

    @Override
    public List<OrdersDto> getAllOrders() {
        List<Orders> ordersList = ordersRepository.findAll(); // 모든 주문 조회
        return ordersList.stream()
                .map(OrdersMapper::mapToOrdersDto)
                .collect(Collectors.toList()); // 리스트를 DTO로 변환
    }

    @Override
    public OrdersDto updateOrder(Long orderId, OrdersDto updatedOrderDto) {
        return ordersRepository.findById(orderId)
                .map(order -> {
                    // 업데이트할 데이터 설정
                    order.setBuyerName(updatedOrderDto.getBuyerName());
                    order.setBuyerPhone(updatedOrderDto.getBuyerPhone());
                    order.setBuyerAddress(updatedOrderDto.getBuyerAddress());
                    order.setBuyerPostCode(updatedOrderDto.getBuyerPostCode());
                    order.setOrderPrice(updatedOrderDto.getOrderPrice());
                    order.setOrderStatus(updatedOrderDto.getOrderStatus());
                    Orders updatedOrder = ordersRepository.save(order); // 수정된 주문 저장
                    return OrdersMapper.mapToOrdersDto(updatedOrder); // DTO로 변환 후 반환
                })
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    @Override
    public void deleteOrder(Long orderId) {
        ordersRepository.deleteById(orderId); // 주문 삭제
    }
}
