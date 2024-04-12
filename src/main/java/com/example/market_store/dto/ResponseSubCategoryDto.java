package com.example.market_store.dto;

import com.example.market_store.entitie.Seller;
import com.example.market_store.entitie.SubCategory;
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
