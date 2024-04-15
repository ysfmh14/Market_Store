package com.example.market_store.service.Impl;

import com.example.market_store.criteria.OrderDetailsCriteria;
import com.example.market_store.dto.requestDto.RequestOrderDetailsDto;
import com.example.market_store.dto.responseDto.ResponseOrderDetailsDto;
import com.example.market_store.service.OrderDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {
    @Override
    public Page<ResponseOrderDetailsDto> findOrderDetailsByCriteria(OrderDetailsCriteria orderDetailsCriteria, int page, int size) {
        return null;
    }

    @Override
    public ResponseOrderDetailsDto addOrderDetails(RequestOrderDetailsDto requestOrderDetailsDto) {
        return null;
    }

    @Override
    public ResponseOrderDetailsDto UpdateOrderDetails(RequestOrderDetailsDto requestOrderDetailsDto) {
        return null;
    }

    @Override
    public void deleteOrderDetails(long id) {

    }
}
