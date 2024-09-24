package com.example.shop4.service.impl;

import com.example.shop4.dto.PaymentDto;
import com.example.shop4.entity.Cart;
import com.example.shop4.entity.Orders;
import com.example.shop4.entity.Payment;
import com.example.shop4.exception.ResourceNotFoundException;
import com.example.shop4.mapper.CartMapper;
import com.example.shop4.mapper.PaymentMapper;
import com.example.shop4.repository.OrdersRepository;
import com.example.shop4.repository.PaymentRepository;
import com.example.shop4.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrdersRepository ordersRepository;

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        Payment payment = PaymentMapper.mapToPayment(paymentDto);
        Payment savedPayment = paymentRepository.save(payment); // 결제 저장
        return PaymentMapper.mapToPaymentDto(savedPayment);
    }

    @Override
    public PaymentDto getPaymentById(Long paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Cart not found id : "+paymentId));
        return PaymentMapper.mapToPaymentDto(payment);
    }

    @Override
    public List<PaymentDto> getAllPayments() {
        List<Payment> paymentsList = paymentRepository.findAll(); // 모든 결제 조회
        return paymentsList.stream()
                .map(PaymentMapper::mapToPaymentDto)
                .collect(Collectors.toList()); // 리스트를 DTO로 변환
    }

    @Override
    public PaymentDto updatePayment(Long paymentId, PaymentDto updatedPaymentDto) {
        return paymentRepository.findById(paymentId)
                .map(payment -> {
                    // 업데이트할 데이터 설정
                    payment.setTotalPrice(updatedPaymentDto.getTotalPrice());
                    payment.setPaymentUid(updatedPaymentDto.getPaymentUid());
                    payment.setDiscountPrice(updatedPaymentDto.getDiscountPrice());
                    payment.setDeliveryFee(updatedPaymentDto.getDeliveryFee());
                    payment.setFinalPrice(updatedPaymentDto.getFinalPrice());
                    payment.setPaymentDate(updatedPaymentDto.getPaymentDate());
                    payment.setPaymentStatus(updatedPaymentDto.getPaymentStatus());
                    payment.setPaymentMethod(updatedPaymentDto.getPaymentMethod());
                    Payment updatedPayment = paymentRepository.save(payment); // 수정된 결제 저장
                    return PaymentMapper.mapToPaymentDto(updatedPayment); // DTO로 변환 후 반환
                })
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }

    @Override
    public void deletePayment(Long paymentId) {
        paymentRepository.deleteById(paymentId); // 결제 삭제
    }
    @Override
    public List<PaymentDto> getPaymentsByMemberId(Long memberId) {
        List<Orders> orders = ordersRepository.findByMemberId(memberId);
        List<Payment> payments = orders.stream()
                .map(order -> paymentRepository.findByOrderId(order.getId()))
                .filter(payment -> payment != null)
                .collect(Collectors.toList());

        return payments.stream()
                .map(PaymentMapper::mapToPaymentDto)
                .collect(Collectors.toList());
    }
}
