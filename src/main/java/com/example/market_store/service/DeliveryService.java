package com.example.market_store.service;

import com.example.market_store.criteria.DeliveryCriteria;
import com.example.market_store.criteria.InvoiceCriteria;
import com.example.market_store.dto.requestDto.RequestDeliveryDto;
import com.example.market_store.dto.requestDto.RequestInvoiceDto;
import com.example.market_store.dto.responseDto.ResponseDeliveryDto;
import com.example.market_store.dto.responseDto.ResponseInvoiceDto;
import org.springframework.data.domain.Page;

public interface DeliveryService {
    Page<ResponseDeliveryDto> findDeliveryByCriteria(DeliveryCriteria deliveryCriteria, int page , int size);
    ResponseDeliveryDto addDelivery(RequestDeliveryDto requestDeliveryDto);
    ResponseDeliveryDto UpdateDelivery(RequestDeliveryDto requestDeliveryDto);
    void  deleteDelivery(long id);
}
