package com.example.shop4.mapper;

import com.example.shop4.dto.OrderDetailDto;
import com.example.shop4.entity.OrderDetail;

public class OrderDetailMapper {
    public static OrderDetailDto mapToOrderDetailDto(OrderDetail orderDetail) {
        return new OrderDetailDto(
                orderDetail.getId(),
                orderDetail.getOrderCount(),
                orderDetail.getGoods(),
                orderDetail.getOrder()
        );
    }

    public static OrderDetail mapToOrderDetail(OrderDetailDto orderDetailDto) {
        return new OrderDetail(
                orderDetailDto.getId(),
                orderDetailDto.getOrderCount(),
                orderDetailDto.getGoods(),
                orderDetailDto.getOrder()
        );
    }
}
