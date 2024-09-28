package com.example.shop4.mapper;

import com.example.shop4.dto.OrdersDto;
import com.example.shop4.entity.Member;
import com.example.shop4.entity.Orders;

public class OrdersMapper {
    public static OrdersDto mapToOrdersDto(Orders orders) {
        return new OrdersDto(
                orders.getId(),
                orders.getOrderUid(),
                orders.getBuyerName(),
                orders.getBuyerPhone(),
                orders.getBuyerAddress(),
                orders.getBuyerPostCode(),
                orders.getOrderPrice(),
                orders.getOrderStatus(), // Enum directly
                orders.getOrderDate(),
                orders.getMember().getId(), // Include member if necessary
                orders.getPayment(), // Include payment if necessary
                orders.getOrderDetails()
        );
    }

    public static Orders mapToOrders(OrdersDto ordersDto, Member member) {
        return new Orders(
                ordersDto.getId(),
                ordersDto.getOrderUid(),
                ordersDto.getBuyerName(),
                ordersDto.getBuyerPhone(),
                ordersDto.getBuyerAddress(),
                ordersDto.getBuyerPostCode(),
                ordersDto.getOrderPrice(),
                ordersDto.getOrderStatus(), // Directly use OrderStatus Enum
                ordersDto.getOrderDate(),
                member, // Include member if necessary
                ordersDto.getPayment(), // Include payment if necessary
                ordersDto.getOrderDetails()
        );
    }
}
