package com.example.shop4.repository;

import com.example.shop4.entity.Cart;
import com.example.shop4.entity.Goods;
import com.example.shop4.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByMemberAndGoods(Member member, Goods goods);
}


