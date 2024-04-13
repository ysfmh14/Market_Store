package com.example.market_store.dto.requestDto;

import com.example.market_store.entitie.Invoice;
import com.example.market_store.entitie.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestPaymentDto {
    private Long id;
    private String paymentCode;
    private String paymentMode;
    private String amount;
    private LocalDateTime dateTimePayment;
    private RequestOrderDto requestOrderDto;
    private RequestInvoiceDto requestInvoiceDto;
}
