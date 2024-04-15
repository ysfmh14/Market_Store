package com.example.market_store.service;

import com.example.market_store.criteria.PaymentCriteria;
import com.example.market_store.criteria.ProductVariantCriteria;
import com.example.market_store.dto.requestDto.RequestPaymentDto;
import com.example.market_store.dto.requestDto.RequestProductVariantDto;
import com.example.market_store.dto.responseDto.ResponsePaymentDto;
import com.example.market_store.dto.responseDto.ResponseProductVariantDto;
import org.springframework.data.domain.Page;

public interface PaymentService {
    Page<ResponsePaymentDto> findPaymentByCriteria(PaymentCriteria paymentCriteria, int page , int size);
    ResponsePaymentDto addPayment(RequestPaymentDto requestPaymentDto);
    ResponsePaymentDto UpdatePayment(RequestPaymentDto requestPaymentDto);
    void  deletePayment(long id);
}
