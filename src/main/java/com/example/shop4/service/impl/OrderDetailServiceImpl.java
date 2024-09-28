package com.example.shop4.service.impl;

import com.example.shop4.dto.OrderDetailDto;
import com.example.shop4.entity.Goods;
import com.example.shop4.entity.OrderDetail;
import com.example.shop4.entity.Orders;
import com.example.shop4.mapper.OrderDetailMapper;
import com.example.shop4.repository.GoodsRepository;
import com.example.shop4.repository.OrderDetailRepository;
import com.example.shop4.repository.OrdersRepository;
import com.example.shop4.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrdersRepository ordersRepository;
    private final GoodsRepository goodsRepository;

    @Override
    public OrderDetailDto createOrderDetail(OrderDetailDto orderDetailDto) {
        // 상품의 ID를 가져온다.
        Long goodsId = orderDetailDto.getGoods().getId();

        // 해당 상품을 DB에서 조회한다.
        Goods goods = goodsRepository.findById(goodsId)
                .orElseThrow(() -> new RuntimeException("Goods not found with id: " + goodsId));

        // 주문 수량을 가져온다.
        int orderCount = orderDetailDto.getOrderCount();

        // 재고를 감소시킨다.
        if (goods.getStock() < orderCount) {
            throw new RuntimeException("Not enough stock for Goods ID: " + goodsId);
        }
        goods.setStock(goods.getStock() - orderCount); // 재고 감소
        goodsRepository.save(goods); // 변경 사항 저장

        OrderDetail orderDetail = OrderDetailMapper.mapToOrderDetail(orderDetailDto);
        OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);
        return OrderDetailMapper.mapToOrderDetailDto(savedOrderDetail);
    }

    @Override
    public Optional<OrderDetailDto> getOrderDetailById(Long id) {
        return orderDetailRepository.findById(id)
                .map(OrderDetailMapper::mapToOrderDetailDto);
    }

    @Override
    public List<OrderDetailDto> getAllOrderDetails() {
        return orderDetailRepository.findAll().stream()
                .map(OrderDetailMapper::mapToOrderDetailDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetailDto updateOrderDetail(Long id, OrderDetailDto updatedOrderDetailDto) {
        return orderDetailRepository.findById(id)
                .map(orderDetail -> {
                    orderDetail.setOrderCount(updatedOrderDetailDto.getOrderCount());
                    // Set other properties if necessary
                    OrderDetail updatedOrderDetail = orderDetailRepository.save(orderDetail);
                    return OrderDetailMapper.mapToOrderDetailDto(updatedOrderDetail);
                }).orElseThrow(() -> new RuntimeException("OrderDetail not found with id: " + id));
    }

    @Override
    public void deleteOrderDetail(Long id) {
        if (orderDetailRepository.existsById(id)) {
            orderDetailRepository.deleteById(id);
        } else {
            throw new RuntimeException("OrderDetail not found with id: " + id);
        }
    }
    @Override
    public List<OrderDetailDto> getOrderDetailsByOrderId(Long orderId) {
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        return orderDetails.stream()
                .map(OrderDetailMapper::mapToOrderDetailDto)
                .collect(Collectors.toList());
    }
    @Override
    public void deleteOrderDetailsByOrderId(Long orderId) {
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        orderDetailRepository.deleteAll(orderDetails);
    }
    @Override
    public List<OrderDetailDto> getOrderDetailsByMemberId(Long memberId) {
        List<Orders> orders = ordersRepository.findByMemberId(memberId);
        List<OrderDetail> orderDetails = orders.stream()
                .flatMap(order -> orderDetailRepository.findByOrderId(order.getId()).stream())
                .collect(Collectors.toList());

        return orderDetails.stream()
                .map(OrderDetailMapper::mapToOrderDetailDto)
                .collect(Collectors.toList());
    }
}
