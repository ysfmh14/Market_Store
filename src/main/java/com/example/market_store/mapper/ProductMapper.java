package com.example.market_store.mapper;

import com.example.market_store.dto.requestDto.RequestProductDto;
import com.example.market_store.dto.requestDto.RequestProductVariantDto;
import com.example.market_store.dto.responseDto.ResponseProductDto;
import com.example.market_store.dto.responseDto.ResponseProductVariantDto;
import com.example.market_store.entitie.Product;
import com.example.market_store.entitie.ProductVariant;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProductMapper {
    Product dtoToModel(RequestProductDto requestProductDto);

    ResponseProductDto modelToDto(Product product);

    default Page<ResponseProductDto> modelToDtos(Page<Product> productPage) {
        return productPage.map(this::modelToDto);
    }
}
