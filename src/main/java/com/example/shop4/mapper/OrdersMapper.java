package com.example.shop4.mapper;

import com.example.shop4.dto.OrdersDto;
import com.example.shop4.entity.Orders;

public class OrdersMapper {
    public static OrdersDto mapToOrdersDto(Orders orders) {
        return new OrdersDto(
                orders.getOrderId(),
                orders.getBuyerName(),
                orders.getBuyerPhone(),
                orders.getBuyerAddress(),
                orders.getOrderStatus(), // Enum directly
                orders.getOrderDate(),
                orders.getMember(), // Include member if necessary
                orders.getPayment() // Include payment if necessary
        );
    }

    public static Orders mapToOrders(OrdersDto ordersDto) {
        return new Orders(
                ordersDto.getOrderId(),
                ordersDto.getBuyerName(),
                ordersDto.getBuyerPhone(),
                ordersDto.getBuyerAddress(),
                ordersDto.getOrderStatus(), // Directly use OrderStatus Enum
                ordersDto.getOrderDate(),
                ordersDto.getMember(), // Include member if necessary
                ordersDto.getPayment() // Include payment if necessary
        );
    }
}
