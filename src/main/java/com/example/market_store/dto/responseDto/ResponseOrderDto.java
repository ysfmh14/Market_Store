package com.example.market_store.dto.responseDto;

import com.example.market_store.dto.requestDto.RequestDeliveryDto;
import com.example.market_store.dto.requestDto.RequestPaymentDto;
import com.example.market_store.dto.requestDto.RequestUsersDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseOrderDto {
    private String orderCode;
    private String status;
    private LocalDateTime dateTimeOrder;
    private double totalPrice;
    private RequestDeliveryDto delivery;
    private RequestUsersDto user;
}
