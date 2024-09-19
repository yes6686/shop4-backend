package com.example.shop4.dto;

import com.example.shop4.entity.Goods;
import com.example.shop4.entity.Orders;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderDetailDto {
    private Long id; // 주문 상세 ID
    private int orderCount; // 주문 수량
    private Goods goods; // 상품 정보
    private Orders order; // 주문 정보
}
