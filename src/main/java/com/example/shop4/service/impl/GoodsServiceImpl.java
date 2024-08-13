package com.example.shop4.service.impl;

import com.example.shop4.dto.GoodsDto;
import com.example.shop4.entity.Goods;
import com.example.shop4.mapper.GoodsMapper;
import com.example.shop4.repository.GoodsRepository;
import com.example.shop4.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public GoodsDto updateGoods(Long goodsId, GoodsDto updatedGoods) {
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(
                ()-> new ResourceNotFoundException("Goods is not exists with given id: " + goodsId)
        );

        goods.setPrice(updatedGoods.getPrice());
        goods.setName(updatedGoods.getName());
        goods.setStock(updatedGoods.getStock());
        goods.setCategory(updatedGoods.getCategory());
        goods.setDescription(updatedGoods.getDescription());
        goods.setUrl(updatedGoods.getUrl());

        Goods updatedGoodsObj = goodsRepository.save(goods);
        return GoodsMapper.mapToGoodsDto(updatedGoodsObj);
    }

    public List<GoodsDto> getAllGoods() {
        List<Goods> goods = goodsRepository.findAll();
        return goods.stream().map((member)->
                GoodsMapper.mapToGoodsDto(member)).collect(Collectors.toList());
}
  
    public GoodsDto getGoodsById(Long goodsId) {
        Goods goods = goodsRepository.findById(goodsId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Goods is not exists with given id : " + goodsId));

        return GoodsMapper.mapToGoodsDto(goods);
    }
}
