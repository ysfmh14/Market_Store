package com.example.market_store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseErrorDto {
    private int statusCode;
    private String message;

    public ResponseErrorDto(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
