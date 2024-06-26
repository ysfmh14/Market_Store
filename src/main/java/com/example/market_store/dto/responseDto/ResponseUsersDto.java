package com.example.market_store.dto.responseDto;

import com.example.market_store.entitie.Order;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseUsersDto {
    private String userCode;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String city;
    private String country;
    private String address;
    private List<ResponseOrderDto> responseOrderDtoList;

}
