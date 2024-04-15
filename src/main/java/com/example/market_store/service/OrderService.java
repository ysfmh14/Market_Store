package com.example.market_store.service;

import com.example.market_store.criteria.OrderCriteria;
import com.example.market_store.criteria.PaymentCriteria;
import com.example.market_store.dto.requestDto.RequestOrderDto;
import com.example.market_store.dto.requestDto.RequestPaymentDto;
import com.example.market_store.dto.responseDto.ResponseOrderDto;
import com.example.market_store.dto.responseDto.ResponsePaymentDto;
import org.springframework.data.domain.Page;

public interface OrderService {
    Page<ResponseOrderDto> findOrderByCriteria(OrderCriteria orderCriteria, int page , int size);
    ResponseOrderDto addOrder(RequestOrderDto requestOrderDto);
    ResponseOrderDto UpdateOrder(RequestOrderDto requestOrderDto);
    void  deleteOrder(long id);
}
