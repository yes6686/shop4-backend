package com.example.shop4.service.impl;

import com.example.shop4.dto.GoodsDto;
import com.example.shop4.entity.Goods;
import com.example.shop4.mapper.GoodsMapper;
import com.example.shop4.repository.GoodsRepository;
import com.example.shop4.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public GoodsDto createGoods(GoodsDto goodsDto) {
        Goods goods = GoodsMapper.mapToGoods(goodsDto);
        Goods savedGoods = goodsRepository.save(goods);
        return GoodsMapper.mapToGoodsDto(savedGoods);
    }
    @Override
    public GoodsDto deleteGoods(Long id) {
        Goods target = goodsRepository.findById(id).orElse(null);
        if (target != null) {
            goodsRepository.delete(target);
        }
        return GoodsMapper.mapToGoodsDto(target);
    } //mm

    @Override
    public GoodsDto getGoodsById(Long goodsId) {
        Goods goods = goodsRepository.findById(goodsId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Goods is not exists with given id : "+goodsId));

        return GoodsMapper.mapToGoodsDto(goods);
    }
}
