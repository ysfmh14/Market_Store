package com.example.market_store.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestUsersDto {
    private Long id;
    private String userCode;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String city;
    private String country;
    private String address;
}
