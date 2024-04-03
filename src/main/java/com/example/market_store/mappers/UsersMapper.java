package com.example.market_store.mappers;

import com.example.market_store.dtos.RequestUsersDto;
import com.example.market_store.dtos.ResponseUsersDto;
import com.example.market_store.entities.Users;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Service
public interface UsersMapper {
    Users dtoToModel(RequestUsersDto requestUserDto);

    ResponseUsersDto modelToDto(Users user);

    default Page<ResponseUsersDto> modelToDtos(Page<Users> usersList) {
        return usersList.map(this::modelToDto);
    }

}


