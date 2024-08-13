package com.example.shop4.service;

import com.example.shop4.dto.GoodsDto;

import java.util.List;

public interface GoodsService {

    GoodsDto createGoods(GoodsDto goodsDto);

    GoodsDto getGoodsById(Long Goods);

    List<GoodsDto> getAllGoods();

    GoodsDto updateGoods(Long goodsId, GoodsDto updatedGood);

    void deleteGoods(Long goodsId);
}
