package com.example.shop4.controller;

import com.example.shop4.dto.CartDto;
import com.example.shop4.dto.GoodsDto;
import com.example.shop4.entity.Cart;
import com.example.shop4.service.CartService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Controller
@AllArgsConstructor
@RequestMapping("/api/cart")

public class CartController {

    private CartService cartService;


    @PostMapping
    public ResponseEntity<CartDto> createGoods(@RequestBody CartDto cartDto) {
        CartDto savedCart = cartService.createCart(cartDto);
        return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public ResponseEntity<CartDto> updateGoods(@PathVariable Long id,
                                                @RequestBody CartDto updatedCart) {
        CartDto cartDto = cartService.updateCart(id, updatedCart);
        return ResponseEntity.ok(cartDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<CartDto>> getAllCarts(@PathVariable("id") Long memberId) {
        List<CartDto> carts = cartService.getAllCarts(memberId);
        return ResponseEntity.ok(carts);
    }
//    @GetMapping("{id}")
//    public ResponseEntity<CartDto> getCartById(@PathVariable("id") Long cartId){
//        CartDto cartDto = cartService.getCartById(cartId);
//        return ResponseEntity.ok(cartDto);
//    }
// cartId로 가져오기 일단 안쓸거같아서 주석처리

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCart(@PathVariable("id") Long cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.ok("Carts deleted successfully..!");
    }


}
