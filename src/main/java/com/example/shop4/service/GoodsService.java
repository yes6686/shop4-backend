package com.example.shop4.service;

import com.example.shop4.dto.GoodsDto;

public interface GoodsService {

    GoodsDto createGoods(GoodsDto goodsDto);

    GoodsDto getGoodsById(Long Goods);
}
