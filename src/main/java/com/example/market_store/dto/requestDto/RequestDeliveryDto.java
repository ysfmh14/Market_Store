package com.example.market_store.dto.requestDto;

import com.example.market_store.entitie.Deliveryman;
import com.example.market_store.entitie.Order;
import com.example.market_store.entitie.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDeliveryDto {
    private Long id;
    private String deliveryCode;
    private String deliveryStatus;
    private String deliveryAddress;
    private String deliveryDate;
    private RequestOrderDto requestOrderDto;
    private  Long deliverymanId;
}
