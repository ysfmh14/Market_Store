package com.example.market_store.dto.responseDto;

import com.example.market_store.dto.requestDto.RequestProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseProductVariantDto {
    private String productVariantCode;
    private String color;
    private String size;
    private String quantity;
    private String unitPrice;
    private boolean available;
    private RequestProductDto requestProductDto;
}
