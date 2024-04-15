package com.example.market_store.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryCriteria {
    private Long id;
    private String deliveryCode;
    private String deliveryStatus;
    private String deliveryAddress;
    private String deliveryDate;
}
