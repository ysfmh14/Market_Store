package com.example.market_store.service;

import com.example.market_store.dto.AssignRoleToUserDto;
import com.example.market_store.dto.ConfirmationMailDto;
import com.example.market_store.dto.LogoutUserDto;
import com.example.market_store.dto.ResetPasswordDto;
import com.example.market_store.dto.requestDto.RequestDeliverymanDto;
import com.example.market_store.dto.requestDto.RequestSellerDto;
import com.example.market_store.dto.requestDto.RequestUsersDto;
import com.example.market_store.dto.requestDto.RequestValidationCodeDto;
import com.example.market_store.dto.responseDto.ResponseValidationCodeDto;

public interface KeycloakService {
    public void createUser(RequestUsersDto requestUsersDto) ;
    public void createSeller(RequestSellerDto requestSellerDto) ;
    public void createDeliveryMan(RequestDeliverymanDto requestDeliverymanDto) ;
    public  void  deleteUser(String userName);
    public  void  assignRoleToUser(AssignRoleToUserDto assignRoleToUserDto);
    public  void  resetPassword(ResetPasswordDto resetPasswordDto);
    public  void  logoutUser(LogoutUserDto logoutUserDto);
    public  String generateConfirmationMail(String email );
    public ResponseValidationCodeDto codeValidation(RequestValidationCodeDto requestValidationCodeDto );

}
