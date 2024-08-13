package com.example.shop4.controller;


import com.example.shop4.dto.GoodsDto;
import com.example.shop4.entity.Goods;
import com.example.shop4.service.GoodsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/Goods")
public class GoodsController {
    private GoodsService goodsService;

    @PostMapping
    public ResponseEntity<GoodsDto> createGoods(@RequestBody GoodsDto goodsDto) {
        GoodsDto savedGoods = goodsService.createGoods(goodsDto);
        return new ResponseEntity<>(savedGoods, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<GoodsDto> getGoodsById(@PathVariable("id") Long goodsId){
        GoodsDto goodsDto = goodsService.getGoodsById(goodsId);
        return ResponseEntity.ok(goodsDto);
    }
    @GetMapping
    public ResponseEntity<List<GoodsDto>> getAllGoods() {
        List<GoodsDto> goods = goodsService.getAllGoods();
        return ResponseEntity.ok(goods);
    }

    @PutMapping("{id}")
    public ResponseEntity<GoodsDto> updateGoods(@PathVariable("id") Long goodsId,
                                                @RequestBody GoodsDto updatedGoods) {
        GoodsDto goodsDto = goodsService.updateGoods(goodsId, updatedGoods);
        return ResponseEntity.ok(goodsDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteGoods(@PathVariable("id") Long goodsId) {
        goodsService.deleteGoods(goodsId);
        return ResponseEntity.ok("Goods deleted successfully..!");
    }
}
