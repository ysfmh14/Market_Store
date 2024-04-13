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
    private double price;
    private int quantity;
    private RequestProductVariantDto requestProductVariantDto ;
    private RequestOrderDto requestOrderDto;
}
