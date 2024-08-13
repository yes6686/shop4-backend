package com.example.shop4.mapper;

import com.example.shop4.dto.GoodsDto;
import com.example.shop4.entity.Goods;

public class GoodsMapper {
    public static GoodsDto mapToGoodsDto(Goods goods){
        return new GoodsDto(
                goods.getId(),
                goods.getPrice(),
                goods.getStock(),
                goods.getName(),
                goods.getDescription(),
                goods.getUrl(),
                goods.getCategory()
        );
    }

    public static Goods mapToGoods(GoodsDto goodsDto){
        return new Goods(
                goodsDto.getId(),
                goodsDto.getPrice(),
                goodsDto.getStock(),
                goodsDto.getName(),
                goodsDto.getDescription(),
                goodsDto.getUrl(),
                goodsDto.getCategory()
        );
    }
}
