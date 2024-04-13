package com.example.market_store.mapper;

import com.example.market_store.dto.requestDto.RequestOrderDto;
import com.example.market_store.dto.requestDto.RequestPaymentDto;
import com.example.market_store.dto.responseDto.ResponseOrderDto;
import com.example.market_store.dto.responseDto.ResponsePaymentDto;
import com.example.market_store.entitie.Order;
import com.example.market_store.entitie.Payment;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface OrderMapper {
    Order dtoToModel(RequestOrderDto requestOrderDto);

    ResponseOrderDto modelToDto(Order order);

    default Page<ResponseOrderDto> modelToDtos(Page<Order> orderPage) {
        return orderPage.map(this::modelToDto);
    }
}
