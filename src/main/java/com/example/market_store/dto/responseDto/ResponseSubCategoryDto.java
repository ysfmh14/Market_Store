package com.example.market_store.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseSubCategoryDto {
    private String subCategoryCode;
    private String name;
    private String description;
    private ResponseCategoryDto responseCategoryDto;
}
