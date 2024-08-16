package com.example.shop4.entity;

import com.example.shop4.dto.GoodsDto;
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
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long price;
    private Long stock;
    private String name;
    @Lob
    private String description;
    private String url;
    private String category;

    public void patch(GoodsDto dto){
        if(dto.getName() != null){
            this.name = dto.getName();
        }
        if(dto.getDescription() != null){
            this.description = dto.getDescription();
        }
        if(dto.getUrl() != null){
            this.url = dto.getUrl();
        }
        if(dto.getCategory() != null){
            this.category = dto.getCategory();
        }
        if(dto.getPrice() != null){
            this.price = dto.getPrice();
        }
        if(dto.getStock() != null){
            this.stock = dto.getStock();
        }
    }
}


