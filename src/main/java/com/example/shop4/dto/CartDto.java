package com.example.shop4.dto;


import com.example.shop4.entity.Goods;
import com.example.shop4.entity.Member;
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
public class CartDto {
    private Long id;
    private Long quantity;
    private Member member;
    private Goods goods;

}
