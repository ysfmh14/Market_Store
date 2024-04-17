package com.example.market_store.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestProductVariantDto {
    private Long id;
    private String productVariantCode;
    private String color;
    private String size;
    private String quantity;
    private String unitPrice;
    private boolean available;
    private Long productId;
}
