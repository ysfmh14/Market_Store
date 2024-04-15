package com.example.market_store.mapper;

import com.example.market_store.dto.requestDto.RequestProductVariantDto;
import com.example.market_store.dto.responseDto.ResponseProductVariantDto;
import com.example.market_store.entitie.ProductVariant;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProductVariantMapper {
    ProductVariant dtoToModel(RequestProductVariantDto requestProductVariantDto);

    ResponseProductVariantDto modelToDto(ProductVariant productVariant);

    default Page<ResponseProductVariantDto> modelToDtos(Page<ProductVariant> productVariantPage) {
        return productVariantPage.map(this::modelToDto);
    }
}
