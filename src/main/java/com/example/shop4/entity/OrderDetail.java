package com.example.shop4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_detail", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"order_id", "goods_id"}) // 복합 고유 제약 조건 추가
})
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 주문된 상품의 고유 ID

    @Column(name = "order_count", nullable = false)
    private int orderCount; // 주문 수량

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id", nullable = false)
    private Goods goods; // Goods 테이블과 외래키 연결 (주문된 상품)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order; // Orders 테이블과 외래키 연결 (주문 ID)
}
