package com.example.market_store.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCriteria {
    private Long id;
    private String productCode;
    private String name;
    private String description;
    private List<String> imagesUrl;
    private int quantity;
    private double unitPrice;
    private boolean available;
    private String sellerCode;
}
