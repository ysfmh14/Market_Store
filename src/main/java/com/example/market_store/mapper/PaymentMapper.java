package com.example.market_store.mapper;

import com.example.market_store.dto.requestDto.RequestPaymentDto;
import com.example.market_store.dto.requestDto.RequestProductVariantDto;
import com.example.market_store.dto.responseDto.ResponsePaymentDto;
import com.example.market_store.dto.responseDto.ResponseProductVariantDto;
import com.example.market_store.entitie.Payment;
import com.example.market_store.entitie.ProductVariant;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PaymentMapper {
    Payment dtoToModel(RequestPaymentDto requestPaymentDto);

    ResponsePaymentDto modelToDto(Payment payment);

    default Page<ResponsePaymentDto> modelToDtos(Page<Payment> paymentPage) {
        return paymentPage.map(this::modelToDto);
    }
}