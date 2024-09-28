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
        // DTO-> Entity로 변환
        Goods goods = GoodsMapper.mapToGoods(goodsDto);
        //데이터베이스에 저장
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
    public GoodsDto updateGoods(Long goodsId, GoodsDto updatedGoods) {
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(
                ()->new ResourceNotFoundException("Employee is not exists with given id : "+goodsId)
        );
        goods.patch(updatedGoods);

        Goods updatedGoodsObj = goodsRepository.save(goods);

        return GoodsMapper.mapToGoodsDto(updatedGoodsObj);
    }

    @Override
    public List<GoodsDto> getGoodsByCategory(String category) {
        List<Goods> goods = goodsRepository.findAll();

        return goods.stream().filter(good -> good.getCategory().equals(category))
                .map(good -> GoodsMapper.mapToGoodsDto(good)).collect(Collectors.toList());
    }

    @Override
    public void deleteGoods(Long goodsId) {
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(
                ()-> new ResourceNotFoundException("Goods is not exists with given id : "+goodsId)
        );
        goodsRepository.deleteById(goodsId);
    }
}
