package com.example.market_store.dto.requestDto;

import jakarta.persistence.Transient;
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
    private Long sellerId;
    private Long subCategoryId;

}
