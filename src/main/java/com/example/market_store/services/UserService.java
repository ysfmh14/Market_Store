package com.example.market_store.services;

import com.example.market_store.dtos.RequestUsersDto;
import com.example.market_store.dtos.ResponseUsersDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    Page<ResponseUsersDto> getAllUsers(int page , int size);
    ResponseUsersDto getUserById(long id) ;
    ResponseUsersDto addUser(RequestUsersDto requestUserDto);
    ResponseUsersDto UpdateUser(RequestUsersDto requestUserDto);
    void  deleteUser(long id);
}
