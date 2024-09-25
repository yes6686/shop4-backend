package com.example.shop4.service.impl;

import com.example.shop4.dto.CartDto;
import com.example.shop4.entity.Cart;
import com.example.shop4.entity.Goods;
import com.example.shop4.entity.Member;
import com.example.shop4.entity.OrderDetail;
import com.example.shop4.exception.ResourceNotFoundException;
import com.example.shop4.mapper.CartMapper;
import com.example.shop4.repository.CartRepository;
import com.example.shop4.repository.GoodsRepository;
import com.example.shop4.repository.MemberRepository;
import com.example.shop4.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public CartDto createCart(CartDto cartDto) {
        Member member = memberRepository.findById(cartDto.getMember().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));
        Goods goods = goodsRepository.findById(cartDto.getGoods().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Goods not found"));

        Cart existingCart = cartRepository.findByMemberAndGoods(member, goods);

        if (existingCart != null) {
            //  같은 (member_id,goods_id) 존재하면, quantity 증가
            existingCart.setQuantity(existingCart.getQuantity() + cartDto.getQuantity());
            Cart updatedCart = cartRepository.save(existingCart);
            return CartMapper.mapToCartDto(updatedCart);
        } else {
            // 존재하지 않으면, 새로운 Cart 엔티티 생성
            Cart cart = CartMapper.mapToCart(cartDto);
            Cart savedCart = cartRepository.save(cart);
            return CartMapper.mapToCartDto(savedCart);
        }
    }



    @Override
    public List<CartDto> getAllCarts(Long memberId) {

        List<Cart> selectedCarts = cartRepository.findAll().stream()
                .filter(cart -> cart.getMember().getId()
                        .equals(memberId)).collect(Collectors.toList());
        return selectedCarts.stream().map((cart)->CartMapper.mapToCartDto(cart))
                .collect(Collectors.toList());
    }

    @Override
    public CartDto getCartById(Long cartId) {
       Cart cart = cartRepository.findById(cartId)
               .orElseThrow(()->
                       new ResourceNotFoundException("Cart not found id : "+cartId));
       return CartMapper.mapToCartDto(cart);
    }

    @Override
    public CartDto updateCart(Long cartId, CartDto updatedCart) {
       Cart cart = cartRepository.findById(cartId).orElseThrow(
               ()->new ResourceNotFoundException("Cart not found id : "+cartId)
       );
       cart.patch(updatedCart);

       Cart updatedCartObj = cartRepository.save(cart);
       return CartMapper.mapToCartDto(updatedCartObj);
    }

    @Override
    public void deleteCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(
                ()->new ResourceNotFoundException("Cart not found id : "+cartId)
        );
        cartRepository.delete(cart);
    }
    @Override
    public void removeOrderedItemsFromCart(List<OrderDetail> orderDetails, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with ID: " + memberId));

        for (OrderDetail orderDetail : orderDetails) {
            Goods orderedGoods = orderDetail.getGoods(); // 주문된 상품 가져오기
            Cart cart = cartRepository.findByMemberAndGoods(member, orderedGoods); // 회원의 장바구니에서 해당 상품 찾기

            if (cart != null) {
                cartRepository.delete(cart); // 장바구니에서 해당 상품 삭제
            }
        }
    }
    @Override
    @Transactional
    public void deleteCartsByOrderDetails(List<Long> goodsIds) {
        for (Long goodsId : goodsIds) {
            // 해당 goodsId를 가진 장바구니 항목 삭제
            cartRepository.deleteByGoodsId(goodsId);
        }
    }

    @Override
    public CartDto getCartByMemberIdAndGoodsId(Long memberId, Long goodsId) {
        Cart cart = cartRepository.findByMemberIdAndGoodsId(memberId, goodsId); //해당 값이 없으면 null을 반환함
        if (cart == null) { //장바구니에 목록이 없다면 null로 반환
            return null;
        }
        return CartMapper.mapToCartDto(cart); //바로 반환해준다
    }

}
