package com.example.market_store.dto;

import com.example.market_store.entitie.ProductVariant;
import com.example.market_store.entitie.Seller;
import com.example.market_store.entitie.SubCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestProductDto {
    private Long id;
    private String productCode;
    private String name;
    private String description;
    private List<String> imagesUrl;
    private int quantity;
    private double unitPrice;
    private boolean available;
    private RequestSellerDto requestSellerDto;
    private RequestSubCategoryDto requestSubCategoryDto;
}
