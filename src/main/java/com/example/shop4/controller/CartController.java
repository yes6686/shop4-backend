package com.example.shop4.controller;

import com.example.shop4.dto.CartDto;
import com.example.shop4.dto.GoodsDto;
import com.example.shop4.entity.Cart;
import com.example.shop4.exception.ResourceNotFoundException;
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

    @GetMapping("member/{id}")
    public ResponseEntity<List<CartDto>> getAllCarts(@PathVariable("id") Long memberId) {
        List<CartDto> carts = cartService.getAllCarts(memberId);
        return ResponseEntity.ok(carts);
    }
    @GetMapping("{id}")
    public ResponseEntity<CartDto> getCartById(@PathVariable("id") Long cartId){
        CartDto cartDto = cartService.getCartById(cartId);
        return ResponseEntity.ok(cartDto);
    }
    //cartId로 가져오기 일단 안쓸거같아서 주석처리

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCart(@PathVariable("id") Long cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.ok("Carts deleted successfully..!");
    }
    @PostMapping("/clear-after-payment")
    public ResponseEntity<String> clearCartAfterPayment(@RequestBody List<Long> goodsIds) {
        try {
            cartService.deleteCartsByOrderDetails(goodsIds);
            return ResponseEntity.ok("장바구니에서 상품이 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("장바구니 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // memberId와 goodsId가 매핑된 장바구니가 있는지 확인
    @GetMapping("/{memberId}/{goodsId}")
    public ResponseEntity<CartDto> getCartByMemberIdAndGoodsId(@PathVariable("memberId") Long memberId,
                                                               @PathVariable("goodsId") Long goodsId) {
        CartDto cartDto = cartService.getCartByMemberIdAndGoodsId(memberId, goodsId);
        return ResponseEntity.ok(cartDto);
    }

}
