package com.example.market_store.mapper;

import com.example.market_store.dto.requestDto.RequestDeliverymanDto;
import com.example.market_store.dto.responseDto.ResponseDeliverymanDto;
import com.example.market_store.entitie.Deliveryman;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface DeliverymanMapper {
    Deliveryman dtoToModel(RequestDeliverymanDto requestDeliverymanDto);

    ResponseDeliverymanDto modelToDto(Deliveryman deliveryman);

    default Page<ResponseDeliverymanDto> modelToDtos(Page<Deliveryman> deliverymanPage) {
        return deliverymanPage.map(this::modelToDto);
    }
}
