package com.example.market_store.dto;

import com.example.market_store.entitie.ProductVariant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseProductDto {
    private String productCode;
    private String name;
    private String description;
    private List<String> imagesUrl;
    private int quantity;
    private double unitPrice;
    private boolean available;
    private ResponseSellerDto responseSellerDto;
    private ResponseSubCategoryDto responseSubCategoryDto;
    private List<ProductVariant> productVariants;
}
