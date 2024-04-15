package com.example.market_store.service.Impl;

import com.example.market_store.criteria.PaymentCriteria;
import com.example.market_store.dto.requestDto.RequestPaymentDto;
import com.example.market_store.dto.responseDto.ResponsePaymentDto;
import com.example.market_store.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    @Override
    public Page<ResponsePaymentDto> findPaymentByCriteria(PaymentCriteria paymentCriteria, int page, int size) {
        return null;
    }

    @Override
    public ResponsePaymentDto addPayment(RequestPaymentDto requestPaymentDto) {
        return null;
    }

    @Override
    public ResponsePaymentDto UpdatePayment(RequestPaymentDto requestPaymentDto) {
        return null;
    }

    @Override
    public void deletePayment(long id) {

    }
}
