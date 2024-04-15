package com.example.market_store.service.Impl;

import com.example.market_store.criteria.DeliverymanCriteria;
import com.example.market_store.dto.requestDto.RequestDeliverymanDto;
import com.example.market_store.dto.responseDto.ResponseDeliverymanDto;
import com.example.market_store.service.DeliverymanService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeliverymanServiceImpl implements DeliverymanService {
    @Override
    public Page<ResponseDeliverymanDto> findDeliverymanByCriteria(DeliverymanCriteria deliverymanCriteria, int page, int size) {
        return null;
    }

    @Override
    public ResponseDeliverymanDto addDeliveryman(RequestDeliverymanDto requestDeliverymanDto) {
        return null;
    }

    @Override
    public ResponseDeliverymanDto UpdateDeliveryman(RequestDeliverymanDto requestDeliverymanDto) {
        return null;
    }

    @Override
    public void deleteDeliveryman(long id) {

    }
}
