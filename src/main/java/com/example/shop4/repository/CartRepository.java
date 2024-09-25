package com.example.shop4.repository;

import com.example.shop4.entity.Cart;
import com.example.shop4.entity.Goods;
import com.example.shop4.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByMemberAndGoods(Member member, Goods goods);
    List<Cart> findByMemberId(Long memberId); // 특정 회원의 장바구니 상품 검색
    void deleteByGoodsId(Long goodsId); // goodsId로 삭제하는 메서드
    Cart findByMemberIdAndGoodsId(Long memberId,Long goodsId); //멤버아이디와 상품아이디로 있는지 찾기
}


