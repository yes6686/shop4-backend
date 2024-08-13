package com.example.shop4.controller;


import com.example.shop4.dto.GoodsDto;
import com.example.shop4.entity.Goods;
import com.example.shop4.service.GoodsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("{id}")
    public ResponseEntity<GoodsDto> updateGoods(@PathVariable Long id,
                                                @RequestBody GoodsDto updatedGoods) {
        GoodsDto goodsDto = goodsService.updateGoods(id, updatedGoods);
        return ResponseEntity.ok(goodsDto);
    }
}
