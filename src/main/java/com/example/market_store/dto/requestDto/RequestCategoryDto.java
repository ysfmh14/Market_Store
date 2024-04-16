package com.example.market_store.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestCategoryDto {

    private Long id;
    private String categoryCode;
    private String name;
    private String description;

}
