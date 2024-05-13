package com.example.market_store.dto.responseDto;

import com.example.market_store.dto.requestDto.RequestInvoiceDto;
import com.example.market_store.dto.requestDto.RequestOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponsePaymentDto {
    private String paymentCode;
    private String paymentMode;
    private long amount;
    private LocalDateTime dateTimePayment;
    private ResponseOrderDto responseOrderDto;
}
