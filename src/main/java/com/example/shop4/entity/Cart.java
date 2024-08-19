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
    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType. EAGER) //LAZY이용시 json 직렬화 오류  EAGER 쓰면 즉시로딩되서 오류안남 자세한건 모름
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
