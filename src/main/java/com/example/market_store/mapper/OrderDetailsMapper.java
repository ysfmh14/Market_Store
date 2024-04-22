package com.example.market_store.mapper;

import com.example.market_store.dto.requestDto.RequestOrderDetailsDto;
import com.example.market_store.dto.responseDto.ResponseOrderDetailsDto;
import com.example.market_store.entitie.OrderDetails;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface OrderDetailsMapper {
    OrderDetails dtoToModel(RequestOrderDetailsDto requestOrderDetailsDto);
    @Mapping(source = "productVariant", target = "productVariant")
    ResponseOrderDetailsDto modelToDto(OrderDetails orderDetails);

    default Page<ResponseOrderDetailsDto> modelToDtos(Page<OrderDetails> orderDetailsPage) {
        return orderDetailsPage.map(this::modelToDto);
    }
}
