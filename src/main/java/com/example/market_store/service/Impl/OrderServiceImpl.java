package com.example.market_store.service.Impl;

import com.example.market_store.criteria.OrderCriteria;
import com.example.market_store.dto.requestDto.RequestOrderDto;
import com.example.market_store.dto.responseDto.ResponseOrderDto;
import com.example.market_store.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Override
    public Page<ResponseOrderDto> findOrderByCriteria(OrderCriteria orderCriteria, int page, int size) {
        return null;
    }

    @Override
    public ResponseOrderDto addOrder(RequestOrderDto requestOrderDto) {
        return null;
    }

    @Override
    public ResponseOrderDto UpdateOrder(RequestOrderDto requestOrderDto) {
        return null;
    }

    @Override
    public void deleteOrder(long id) {

    }
}
