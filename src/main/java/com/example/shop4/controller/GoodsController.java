package com.example.shop4.controller;


import com.example.shop4.dto.GoodsDto;
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

    @DeleteMapping("{id}")
    public ResponseEntity<GoodsDto> deleteGoods(@PathVariable("id") Long id) {
        GoodsDto target = goodsService.deleteGoods(id);
        return new ResponseEntity<>(target, HttpStatus.OK);
    }
}//mm
