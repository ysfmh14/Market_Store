package com.example.market_store.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCriteria {
    private String paymentCode;
    private String paymentMode;
    private String amount;
    private LocalDateTime dateTimePayment;
}
