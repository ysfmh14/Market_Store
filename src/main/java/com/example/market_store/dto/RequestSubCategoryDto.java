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
public class RequestSubCategoryDto {
    private Long id;
    private String subCategoryCode;
    private String name;
    private String description;
    private RequestCategoryDto requestCategoryDto;
}
