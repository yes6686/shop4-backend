package com.example.shop4.service;

import com.example.shop4.dto.GoodsDto;

import java.util.List;

public interface GoodsService {

    GoodsDto createGoods(GoodsDto goodsDto);
    List<GoodsDto> getAllGoods();
    GoodsDto getGoodsById(Long Goods);
    GoodsDto updateGoods(Long goodsId, GoodsDto updatedGood);
    void deleteGoods(Long goodsId);
}
