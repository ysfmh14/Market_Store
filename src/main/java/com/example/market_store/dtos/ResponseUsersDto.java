package com.example.market_store.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUsersDto {
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
