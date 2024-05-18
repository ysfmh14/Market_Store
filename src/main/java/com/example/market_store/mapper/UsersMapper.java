package com.example.market_store.mapper;

import com.example.market_store.dto.requestDto.RequestUsersDto;
import com.example.market_store.dto.responseDto.ResponseUsersDto;
import com.example.market_store.entitie.Users;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;


@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UsersMapper {
    Users dtoToModel(RequestUsersDto requestUserDto);
    @Mapping(source = "orders", target = "responseOrderDtoList")
    ResponseUsersDto modelToDto(Users user);

    default Page<ResponseUsersDto> modelToDtos(Page<Users> usersList) {
        return usersList.map(this::modelToDto);
    }

}


