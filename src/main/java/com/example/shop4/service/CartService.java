package com.example.shop4.service;

import com.example.shop4.dto.CartDto;
import com.example.shop4.entity.Cart;
import com.example.shop4.entity.Goods;
import com.example.shop4.entity.Member;

import java.util.List;

public interface CartService {
    CartDto createCart(CartDto cartDto);
    List<CartDto> getAllCarts();
    CartDto getCartById(Long cartId);
    CartDto updateCart(Long cartId, CartDto updatedCart);
    void deleteCart(Long cartId);

}
