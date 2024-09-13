package com.example.shop4.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.beans.beancontext.BeanContextMembershipEvent;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrdersDto {
    private Long orderId; // 주문 ID
    private String buyerName; // 구매자 이름
    private String buyerPhone; // 구매자 전화번호
    private String buyerAddress; // 구매자 주소
    private String orderStatus; // 주문 상태
    private LocalDateTime orderDate; // 주문 날짜
}