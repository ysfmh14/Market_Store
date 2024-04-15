package com.example.market_store.service.Impl;

import com.example.market_store.criteria.InvoiceCriteria;
import com.example.market_store.dto.requestDto.RequestInvoiceDto;
import com.example.market_store.dto.responseDto.ResponseInvoiceDto;
import com.example.market_store.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    @Override
    public Page<ResponseInvoiceDto> findInvoiceByCriteria(InvoiceCriteria invoiceCriteria, int page, int size) {
        return null;
    }

    @Override
    public ResponseInvoiceDto addInvoice(RequestInvoiceDto requestInvoiceDto) {
        return null;
    }

    @Override
    public ResponseInvoiceDto UpdateInvoice(RequestInvoiceDto requestInvoiceDto) {
        return null;
    }

    @Override
    public void deleteInvoice(long id) {

    }
}
