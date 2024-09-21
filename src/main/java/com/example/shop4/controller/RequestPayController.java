package com.example.shop4.controller;

import com.example.shop4.entity.PaymentStatus;  // PaymentStatus enum import
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@CrossOrigin("*")
public class RequestPayController {

    @Value("${iamport.key}")
    private String restApiKey;

    @Value("${iamport.secret}")
    private String restApiSecret;

    private IamportClient iamportClient;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(restApiKey, restApiSecret);
    }

    // 결제 검증
    @PostMapping("/verifyIamport/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid(@PathVariable("imp_uid") String imp_uid)
            throws IamportResponseException, IOException {
        return iamportClient.paymentByImpUid(imp_uid);
    }

    // 결제 취소 처리
    @PostMapping("/cancelPayment")
    public IamportResponse<Payment> cancelPayment(@RequestBody CancelData cancelData) throws IamportResponseException, IOException {
        // 기본적으로 모든 금액을 환불하는 방식으로 취소 요청
        return iamportClient.cancelPaymentByImpUid(cancelData);
    }

    // 결제 상태 조회 및 처리 (PaymentStatus 사용)
    @GetMapping("/paymentStatus/{imp_uid}")
    public PaymentStatus checkPaymentStatus(@PathVariable("imp_uid") String imp_uid) throws IamportResponseException, IOException {
        IamportResponse<Payment> response = iamportClient.paymentByImpUid(imp_uid);
        Payment payment = response.getResponse();

        // 결제 상태에 따른 PaymentStatus 매핑
        switch (payment.getStatus()) {
            case "paid":
                return PaymentStatus.COMPLETED; // 결제 완료 상태
            case "failed":
                return PaymentStatus.FAILED; // 결제 실패 상태
            case "cancelled":
                return PaymentStatus.REFUNDED; // 결제 환불 완료 상태
            case "ready":
                return PaymentStatus.PENDING; // 결제 대기 중 (가상계좌 대기)
            default:
                return null; // 알 수 없는 상태
        }
    }

    // 특정 결제 정보 가져오기
    @GetMapping("/getPaymentInfo/{imp_uid}")
    public IamportResponse<Payment> getPaymentInfo(@PathVariable("imp_uid") String imp_uid)
            throws IamportResponseException, IOException {
        return iamportClient.paymentByImpUid(imp_uid);
    }

}
