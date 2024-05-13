package com.example.market_store.service;

import com.example.market_store.criteria.PaymentCriteria;
import com.example.market_store.criteria.ProductVariantCriteria;
import com.example.market_store.dto.requestDto.RequestInvoiceDto;
import com.example.market_store.dto.requestDto.RequestPaymentDto;
import com.example.market_store.dto.requestDto.RequestProductVariantDto;
import com.example.market_store.dto.responseDto.ResponsePaymentDto;
import com.example.market_store.dto.responseDto.ResponseProductVariantDto;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface PaymentService {
    Page<ResponsePaymentDto> findPaymentByCriteria(PaymentCriteria paymentCriteria, int page , int size);
    ResponsePaymentDto addPayment(RequestPaymentDto requestPaymentDto);
    ResponsePaymentDto UpdatePayment(RequestPaymentDto requestPaymentDto);
    void  deletePayment(long id);
    public void generatePdf(HttpServletResponse response , String paymentCode)throws DocumentException, IOException;
}
