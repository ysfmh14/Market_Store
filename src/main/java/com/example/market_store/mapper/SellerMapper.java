package com.example.market_store.mapper;

import com.example.market_store.dto.RequestSellerDto;
import com.example.market_store.dto.RequestUsersDto;
import com.example.market_store.dto.ResponseSellerDto;
import com.example.market_store.dto.ResponseUsersDto;
import com.example.market_store.entitie.Seller;
import com.example.market_store.entitie.Users;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SellerMapper {
    Seller dtoToModel(RequestSellerDto requestSellerDto);

    ResponseSellerDto modelToDto(Seller seller);

    default Page<ResponseSellerDto> modelToDtos(Page<Seller> sellerPage) {
        return sellerPage.map(this::modelToDto);
    }
}
