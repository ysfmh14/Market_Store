package com.example.market_store.service;

import com.example.market_store.dto.AssignRoleToUserDto;
import com.example.market_store.dto.LogoutUserDto;
import com.example.market_store.dto.ResetPasswordDto;
import com.example.market_store.dto.requestDto.RequestUsersDto;

public interface KeycloakService {
    public void createUser(RequestUsersDto requestUsersDto) ;
    public  void  deleteUser(String userName);
    public  void  assignRoleToUser(AssignRoleToUserDto assignRoleToUserDto);
    public  void  resetPassword(ResetPasswordDto resetPasswordDto);
    public  void  logoutUser(LogoutUserDto logoutUserDto);

}
