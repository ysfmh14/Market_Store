package com.example.market_store.service;

import com.example.market_store.criteria.OrderCriteria;
import com.example.market_store.criteria.OrderDetailsCriteria;
import com.example.market_store.dto.requestDto.RequestOrderDetailsDto;
import com.example.market_store.dto.requestDto.RequestOrderDto;
import com.example.market_store.dto.responseDto.ResponseOrderDetailsDto;
import com.example.market_store.dto.responseDto.ResponseOrderDto;
import org.springframework.data.domain.Page;

public interface OrderDetailsService {
    Page<ResponseOrderDetailsDto> findOrderDetailsByCriteria(OrderDetailsCriteria orderDetailsCriteria, int page , int size);
    ResponseOrderDetailsDto addOrderDetails(RequestOrderDetailsDto requestOrderDetailsDto);
    ResponseOrderDetailsDto UpdateOrderDetails(RequestOrderDetailsDto requestOrderDetailsDto);
    void  deleteOrderDetails(long id);
}
