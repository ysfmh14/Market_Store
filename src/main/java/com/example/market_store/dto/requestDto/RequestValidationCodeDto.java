package com.example.market_store.dto.requestDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestValidationCodeDto {
    private String email;
    private String code;
}
