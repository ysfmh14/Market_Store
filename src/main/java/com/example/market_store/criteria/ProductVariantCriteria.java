package com.example.market_store.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariantCriteria {
    private Long id;
    private String productVariantCode;
    private String color;
    private String size;
    private String quantity;
    private String unitPrice;
    private boolean available;
    private Long productId;
}

