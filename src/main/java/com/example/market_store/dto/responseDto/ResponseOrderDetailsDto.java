package com.example.market_store.dto.responseDto;

import com.example.market_store.dto.requestDto.RequestOrderDto;
import com.example.market_store.dto.requestDto.RequestProductVariantDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseOrderDetailsDto {
    private double price;
    private int quantity;
    private RequestProductVariantDto requestProductVariantDto ;
    private RequestOrderDto requestOrderDto;
}
