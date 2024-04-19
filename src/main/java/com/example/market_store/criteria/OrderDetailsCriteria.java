package com.example.market_store.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsCriteria {
    private Long id;
    private String orderDetailsCode;
    private double unitPrice;
    private int quantity;
    private Long productVariantId;
    private Long orderId;
}
