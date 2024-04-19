package com.example.market_store.dto.requestDto;

import com.example.market_store.entitie.Order;
import com.example.market_store.entitie.ProductVariant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestOrderDetailsDto {
    private Long id;
    private String orderDetailsCode;
    private double unitPrice;
    private int quantity;
    private Long productVariantId;
    private Long orderId;
}
