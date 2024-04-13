package com.example.market_store.dto.requestDto;

import com.example.market_store.entitie.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestInvoiceDto {
    private Long id;
    private String invoiceCode;
    private String amount;
    private LocalDateTime dateTimePayment;
    private RequestPaymentDto requestPaymentDto;
}
