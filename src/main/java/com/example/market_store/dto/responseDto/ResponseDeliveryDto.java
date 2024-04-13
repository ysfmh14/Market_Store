package com.example.market_store.dto.responseDto;

import com.example.market_store.dto.requestDto.RequestDeliverymanDto;
import com.example.market_store.dto.requestDto.RequestOrderDto;
import com.example.market_store.dto.requestDto.RequestPaymentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDeliveryDto {
    private String deliveryCode;
    private String deliveryStatus;
    private String deliveryAddress;
    private String deliveryDate;
    private RequestOrderDto requestOrderDto;
    private RequestDeliverymanDto requestDeliverymanDto;
}
