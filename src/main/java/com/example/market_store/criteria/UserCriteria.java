package com.example.market_store.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCriteria {
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

