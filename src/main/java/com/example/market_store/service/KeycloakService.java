package com.example.market_store.service;

import com.example.market_store.dto.requestDto.RequestUsersDto;

public interface KeycloakService {
    public void createUser(RequestUsersDto requestUsersDto) ;
    public  void  deleteUser(String userName);
    public  void  assignRoleToUser(String userName,String roleName);
}
