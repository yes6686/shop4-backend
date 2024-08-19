package com.example.shop4.entity;

import com.example.shop4.dto.CartDto;
import com.example.shop4.dto.GoodsDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quantity; //장바구니 수량
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;

    public void patch(CartDto dto){
        if(dto.getQuantity() != null){
            this.quantity = dto.getQuantity();
        }
        if(dto.getMember() != null){
            this.member = dto.getMember();
        }
        if(dto.getGoods() != null){
            this.goods = dto.getGoods();
        }


    }


}
