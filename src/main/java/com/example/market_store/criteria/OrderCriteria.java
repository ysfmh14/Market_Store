package com.example.market_store.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCriteria {
    private Long id;
    private String orderCode;
    private String status;
    private LocalDateTime dateTimeOrder;
}
