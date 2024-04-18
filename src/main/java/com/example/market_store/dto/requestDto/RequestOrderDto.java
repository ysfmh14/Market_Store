package com.example.market_store.dto.requestDto;

import com.example.market_store.entitie.Delivery;
import com.example.market_store.entitie.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestOrderDto {
    private Long id;
    private String orderCode;
    private String status;
    private double totalPrice;
    private LocalDateTime dateTimeOrder;
    private RequestUsersDto requestUsersDto;
}
