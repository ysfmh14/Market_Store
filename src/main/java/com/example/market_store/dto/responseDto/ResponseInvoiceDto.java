package com.example.market_store.dto.responseDto;

import com.example.market_store.dto.requestDto.RequestPaymentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseInvoiceDto {
    private String invoiceCode;
    private String amount;
    private LocalDateTime dateTimePayment;
    private RequestPaymentDto requestPaymentDto;
}
