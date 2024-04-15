package com.example.market_store.service.Impl;

import com.example.market_store.criteria.DeliveryCriteria;
import com.example.market_store.dto.requestDto.RequestDeliveryDto;
import com.example.market_store.dto.responseDto.ResponseDeliveryDto;
import com.example.market_store.service.DeliveryService;
import com.example.market_store.service.DeliverymanService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    @Override
    public Page<ResponseDeliveryDto> findDeliveryByCriteria(DeliveryCriteria deliveryCriteria, int page, int size) {
        return null;
    }

    @Override
    public ResponseDeliveryDto addDelivery(RequestDeliveryDto requestDeliveryDto) {
        return null;
    }

    @Override
    public ResponseDeliveryDto UpdateDelivery(RequestDeliveryDto requestDeliveryDto) {
        return null;
    }

    @Override
    public void deleteDelivery(long id) {

    }
}
