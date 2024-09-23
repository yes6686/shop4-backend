package com.example.shop4.entity;


import com.example.shop4.dto.CouponsDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Coupons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Long discount;

    @Column
    private LocalDate createDate;

    @Column
    private LocalDate limitDate;

    @Column
    private Long count;

    public void patch(CouponsDto dto) {
        if(dto.getName() != null){
            this.name = dto.getName();
        }
        if(dto.getDiscount() != null){
            this.discount = dto.getDiscount();
        }
        if(dto.getCreateDate() != null){
            this.createDate = dto.getCreateDate();
        }
        if(dto.getLimitDate() != null){
            this.limitDate = dto.getLimitDate();
        }
        if(dto.getCount() != null){
            this.count = dto.getCount();
        }
    }
}
