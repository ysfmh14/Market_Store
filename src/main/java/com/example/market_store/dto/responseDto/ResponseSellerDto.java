package com.example.market_store.dto.responseDto;

import com.example.market_store.entitie.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseSellerDto {
    private String sellerCode;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String city;
    private String country;
    private String address;
    private String rib;
    private boolean isActive;
//    private List<Product> productList;
}

