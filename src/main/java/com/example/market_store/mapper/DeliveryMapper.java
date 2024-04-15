package com.example.market_store.mapper;

import com.example.market_store.dto.requestDto.RequestDeliveryDto;
import com.example.market_store.dto.responseDto.ResponseDeliveryDto;
import com.example.market_store.entitie.Delivery;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface DeliveryMapper {
    Delivery dtoToModel(RequestDeliveryDto requestDeliveryDto);

    ResponseDeliveryDto modelToDto(Delivery delivery);

    default Page<ResponseDeliveryDto> modelToDtos(Page<Delivery> deliveryPage) {
        return deliveryPage.map(this::modelToDto);
    }
}
