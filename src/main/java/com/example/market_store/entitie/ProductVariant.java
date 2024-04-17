package com.example.market_store.entitie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "productVariantCode",unique = true)
    private String productVariantCode;
    @Column(name = "color")
    private String color;
    @Column(name = "size")
    private String size;
    @Column(name = "quantity")
    private String quantity;
    @Column(name = "unitPrice")
    private String unitPrice;
    @Column(name = "available")
    private boolean available;
    @Transient
    private Long productId;
    @ManyToOne
    private Product product;


}
