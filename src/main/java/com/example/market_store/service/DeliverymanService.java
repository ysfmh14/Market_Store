package com.example.market_store.service;

import com.example.market_store.criteria.DeliveryCriteria;
import com.example.market_store.criteria.DeliverymanCriteria;
import com.example.market_store.dto.requestDto.RequestDeliveryDto;
import com.example.market_store.dto.requestDto.RequestDeliverymanDto;
import com.example.market_store.dto.responseDto.ResponseDeliveryDto;
import com.example.market_store.dto.responseDto.ResponseDeliverymanDto;
import org.springframework.data.domain.Page;

public interface DeliverymanService {
    Page<ResponseDeliverymanDto> findDeliverymanByCriteria(DeliverymanCriteria deliverymanCriteria, int page , int size);
    ResponseDeliverymanDto addDeliveryman(RequestDeliverymanDto requestDeliverymanDto);
    ResponseDeliverymanDto UpdateDeliveryman(RequestDeliverymanDto requestDeliverymanDto);
    void  deleteDeliveryman(long id);
}
