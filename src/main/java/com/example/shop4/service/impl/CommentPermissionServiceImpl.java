package com.example.shop4.service.impl;

import com.example.shop4.entity.*;
import com.example.shop4.repository.OrderDetailRepository;
import com.example.shop4.repository.OrdersRepository;
import com.example.shop4.repository.PaymentRepository;
import com.example.shop4.service.CommentPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentPermissionServiceImpl implements CommentPermissionService {

    private final OrdersRepository ordersRepository;
    private final PaymentRepository paymentRepository;
    private final OrderDetailRepository orderDetailRepository;
    @Override
    public boolean canComment(Long memberId, Long goodsId) {
        // 1. Orders 테이블에서 PENDING 상태의 주문 목록 가져오기
        List<Orders> pendingOrders = ordersRepository.findByMemberIdAndOrderStatus(memberId, OrderStatus.PENDING);

        // 2. 각 주문 ID를 통해 Payment 테이블에서 COMPLETED 상태의 결제 내역 필터링
        for (Orders order : pendingOrders) {
            Payment payment = paymentRepository.findByOrderIdAndPaymentStatus(order.getId(), PaymentStatus.COMPLETED);

            if (payment != null) {
                // 3. OrderDetail에서 해당 주문의 상품 목록 조회
                List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(order.getId());

                // 주문 내역에 해당 상품이 포함되어 있는지 확인
                for (OrderDetail orderDetail : orderDetails) {
                    if (orderDetail.getGoods().getId().equals(goodsId)) {
                        return true; // 상품을 구매한 경우 댓글 작성 가능
                    }
                }
            }
        }
        return false; // 상품을 구매하지 않았거나 결제가 완료되지 않은 경우 댓글 작성 불가
    }
}
