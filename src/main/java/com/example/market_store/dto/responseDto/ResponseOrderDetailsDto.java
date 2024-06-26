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
    private String orderDetailsCode;
    private double unitPrice;
    private int quantity;
    private ResponseProductVariantDto productVariant ;
}
