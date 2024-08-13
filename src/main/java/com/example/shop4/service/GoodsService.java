package com.example.shop4.service;

import com.example.shop4.dto.GoodsDto;

import java.util.List;

public interface GoodsService {

    GoodsDto createGoods(GoodsDto goodsDto);
    GoodsDto updateGoods(Long goodsId, GoodsDto updatedGoods);
    List<GoodsDto> getAllGoods();
    GoodsDto getGoodsById(Long Goods);
}
