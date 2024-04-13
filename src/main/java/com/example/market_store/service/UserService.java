package com.example.market_store.service;

import com.example.market_store.criteria.UserCriteria;
import com.example.market_store.dto.requestDto.RequestUsersDto;
import com.example.market_store.dto.responseDto.ResponseUsersDto;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<ResponseUsersDto> getAllUsers(int page , int size);
    Page<ResponseUsersDto> findUsersByCriteria(UserCriteria userCriteria,int page , int size);
    ResponseUsersDto getUserById(long id) ;
    ResponseUsersDto addUser(RequestUsersDto requestUserDto);
    ResponseUsersDto UpdateUser(RequestUsersDto requestUserDto);
    void  deleteUser(long id);
}
