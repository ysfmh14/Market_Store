package com.example.market_store.service;

import com.example.market_store.criteria.InvoiceCriteria;
import com.example.market_store.criteria.OrderCriteria;
import com.example.market_store.dto.requestDto.RequestInvoiceDto;
import com.example.market_store.dto.requestDto.RequestOrderDto;
import com.example.market_store.dto.responseDto.ResponseInvoiceDto;
import com.example.market_store.dto.responseDto.ResponseOrderDto;
import org.springframework.data.domain.Page;

public interface InvoiceService {
    Page<ResponseInvoiceDto> findInvoiceByCriteria(InvoiceCriteria invoiceCriteria, int page , int size);
    ResponseInvoiceDto addInvoice(RequestInvoiceDto requestInvoiceDto);
    ResponseInvoiceDto UpdateInvoice(RequestInvoiceDto requestInvoiceDto);
    void  deleteInvoice(long id);
}
