package com.example.shop4.mapper;

import com.example.shop4.dto.CartDto;
import com.example.shop4.entity.Cart;

public class CartMapper {
    public static CartDto mapToCartDto(Cart cart) {
        return new CartDto(
                cart.getId(),
                cart.getQuantity(),
                cart.getMember(),
                cart.getGoods()
        );

    }

    public static Cart mapToCart(CartDto cartDto) {
        return new Cart(
                cartDto.getId(),
                cartDto.getQuantity(),
                cartDto.getMember(),
                cartDto.getGoods()
        );
    }
}
