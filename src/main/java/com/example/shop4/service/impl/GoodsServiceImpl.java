package com.example.shop4.service.impl;

import com.example.shop4.dto.GoodsDto;
import com.example.shop4.entity.Goods;
import com.example.shop4.exception.ResourceNotFoundException;
import com.example.shop4.mapper.GoodsMapper;
import com.example.shop4.repository.GoodsRepository;
import com.example.shop4.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    public GoodsDto getGoodsById(Long goodsId) {
        Goods goods = goodsRepository.findById(goodsId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Goods is not exists with given id : " + goodsId));

        return GoodsMapper.mapToGoodsDto(goods);
    }

    @Override
    public List<GoodsDto> getAllGoods() { // goods의 단수형은 good
        List<Goods> goods = goodsRepository.findAll();
        return goods.stream().map((good)->GoodsMapper.mapToGoodsDto(good))
                .collect(Collectors.toList());
    }

    @Override
    public GoodsDto updateGoods(Long goodsId, GoodsDto updatedGood) {
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(
                ()->new ResourceNotFoundException("Employee is not exists with given id : "+goodsId)
        );
        goods.setPrice(updatedGood.getPrice());
        goods.setStock(updatedGood.getStock());
        goods.setName(updatedGood.getName());
        goods.setDescription(updatedGood.getDescription());
        goods.setUrl(updatedGood.getUrl());
        goods.setCategory(updatedGood.getCategory());
        Goods updatedGoodsObj = goodsRepository.save(goods);
        return GoodsMapper.mapToGoodsDto(updatedGoodsObj);
    }

    @Override
    public void deleteGoods(Long goodsId) {
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(
                ()-> new ResourceNotFoundException("Goods is not exists with given id : "+goodsId)
        );
        goodsRepository.deleteById(goodsId);
    }
}
