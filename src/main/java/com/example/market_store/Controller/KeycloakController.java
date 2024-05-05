package com.example.market_store.Controller;

import com.example.market_store.dto.AssignRoleToUserDto;
import com.example.market_store.dto.ConfirmationMailDto;
import com.example.market_store.dto.LogoutUserDto;
import com.example.market_store.dto.ResetPasswordDto;
import com.example.market_store.dto.requestDto.RequestOrderDto;
import com.example.market_store.dto.requestDto.RequestValidationCodeDto;
import com.example.market_store.dto.responseDto.ResponseOrderDto;
import com.example.market_store.dto.responseDto.ResponseValidationCodeDto;
import com.example.market_store.service.KeycloakService;
import com.sun.mail.iap.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/security")
public class KeycloakController {
    private KeycloakService keycloakService;
    @PostMapping("/resetPassword")
    public void resetPassword(@RequestBody ResetPasswordDto resetPasswordDto){
       keycloakService.resetPassword(resetPasswordDto);
    }
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response, @RequestBody LogoutUserDto logoutUserDto){
        keycloakService.logoutUser(logoutUserDto);

    }
    @PostMapping("/assignRole")
    public void assignRole(@RequestBody AssignRoleToUserDto assignRoleToUserDto){
        keycloakService.assignRoleToUser(assignRoleToUserDto);
    }
    @PostMapping("/confirmationMail")
    public void ConfirmationMail(@RequestBody ConfirmationMailDto confirmationMailDto){
        keycloakService.generateConfirmationMail(confirmationMailDto.getEmail());
    }
        @PostMapping("/codeValidation")
    public ResponseValidationCodeDto codeValidation(@RequestBody RequestValidationCodeDto requestValidationCodeDto){
       return keycloakService.codeValidation(requestValidationCodeDto);
    }
}
