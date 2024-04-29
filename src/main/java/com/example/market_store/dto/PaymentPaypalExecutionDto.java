package com.example.market_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentPaypalExecutionDto {
    private String paymentId;
    private String payerId;
}
