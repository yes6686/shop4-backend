package com.example.shop4.service;

import com.example.shop4.dto.CartDto;
import com.example.shop4.entity.Cart;
import com.example.shop4.entity.Goods;
import com.example.shop4.entity.Member;
import com.example.shop4.entity.OrderDetail;

import java.util.List;

public interface CartService {
    CartDto createCart(CartDto cartDto);
    List<CartDto> getAllCarts(Long memberId);
    CartDto getCartById(Long cartId);
    CartDto updateCart(Long cartId, CartDto updatedCart);
    void deleteCart(Long cartId);
    // 주문 후 주문한 상품 장바구니 비우기
    public void removeOrderedItemsFromCart(List<OrderDetail> orderDetails, Long memberId);
    void deleteCartsByOrderDetails(List<Long> goodsIds);
    //memberId와 goodsId로 장바구니 목록 찾기
    CartDto getCartByMemberIdAndGoodsId(Long memberId, Long goodsId);
}
